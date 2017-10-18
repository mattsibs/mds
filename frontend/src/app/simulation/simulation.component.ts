import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  ViewChild
} from '@angular/core';

import * as THREE from "three";
import { ParticleService } from '../_services/index';

var TrackballControls = require('three-trackballcontrols');

@Component({
  selector: 'app-simulation',
  templateUrl: './simulation.component.html',
  styleUrls: ['./simulation.component.css']
})
export class SimulationComponent implements AfterViewInit {
    private camera: THREE.PerspectiveCamera;

    private cameraTarget: THREE.Vector3;

    private scene: THREE.Scene;
    private cube;

    private particleMap = {};

    @Input()
    public fieldOfView: number = 75;

    @Input('nearClipping')
    public nearClippingPane: number = 1;

    @Input('farClipping')
    public farClippingPane: number = 1100;

    private material: THREE.MeshBasicMaterial;

    private get canvas(): HTMLCanvasElement {
        return this.canvasRef.nativeElement;
      }

    @ViewChild('canvas')
    private canvasRef: ElementRef;

    private renderer: THREE.WebGLRenderer;

    private controls;
    private sphereGeometry;
    private sphereMaterial;
    constructor(private particleService: ParticleService) { }

    private createScene() {
      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color( 0xffffff );

      this.sphereGeometry = new THREE.SphereBufferGeometry( 0.01, 8, 6, 0, 6.3, 0, 3.1 );
      var parameters = {
        vertexColors: THREE.FaceColors,
        color: 0x027F10
      };

      this.sphereMaterial = new THREE.MeshPhongMaterial( parameters );

      var ambientLight = new THREE.AmbientLight(0x383838);
      this.scene.add(ambientLight);

      var plane = new THREE.GridHelper(100, 10);
      this.scene.add(plane);

      var cubeAxis = new THREE.AxisHelper(20);
      this.scene.add(cubeAxis);
    }

    private createCamera() {
      let aspectRatio = this.getAspectRatio();
      this.camera = new THREE.PerspectiveCamera(
        this.fieldOfView,
        aspectRatio,
        this.nearClippingPane,
        this.farClippingPane
      );

      this.camera.position.z = 5;
      this.cameraTarget = new THREE.Vector3(0, 0, 0);
    }

    private getAspectRatio(): number {
      let height = this.canvas.clientHeight;

      if (height === 0) {
        return 0;
      }

      return this.canvas.clientWidth / this.canvas.clientHeight;
    }

    private startRendering() {
      this.renderer = new THREE.WebGLRenderer({ canvas: this.canvas });
      this.renderer.setPixelRatio(devicePixelRatio);
      this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

      let component: SimulationComponent = this;

      (function render() {
        requestAnimationFrame(render);
        component.renderer.render(component.scene, component.camera);
        component.controls.update();
      }());

    }

    public onResize(event: Event) {
      this.camera.aspect = this.getAspectRatio();
      this.camera.updateProjectionMatrix();

      this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);
    }

    public runSimulation(iterations) {
      this.particleService.iterateParticles(this.consumeParticle.bind(this), iterations);
    }

    public createParticles() {
      this.particleService.getParticles(this.addParticleToScene.bind(this));
    }


    public addParticleToScene(data) {
      var id = data['id'];

      var sphere = new THREE.Mesh( this.sphereGeometry, this.sphereMaterial );
      this.updatePosition(sphere, data);

      this.particleMap[id] = sphere;
      this.scene.add( this.particleMap[id] );


    }

    public consumeParticle(data) {
      var id = data['id'];
      var particle = this.particleMap[id];

      if (!particle) {
        console.log("Id not found " + id);
        return;
      }

      this.updatePosition(particle, data);

    }

    private updatePosition(particle, data) {
     particle.position.x = 0.1 * data['currentIteration']['position']['x'];
          particle.position.y = 0.1 * data['currentIteration']['position']['y'];
          particle.position.z = 0.1 * data['currentIteration']['position']['z'];
    }

    private createControls() {
      this.controls = new TrackballControls(this.camera, this.canvas);
      this.controls.rotateSpeed = 1.0;
      this.controls.zoomSpeed = 1.0;
      this.controls.panSpeed = 1.0;
    }

    ngAfterViewInit() {
      this.createScene();
      this.createParticles();
      console.log(this.particleMap);
      this.createCamera();
      this.createControls();
      this.startRendering();
    }

}
