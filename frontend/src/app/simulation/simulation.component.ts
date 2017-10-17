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

    constructor(private particleService: ParticleService) { }

    private createScene() {
      this.scene = new THREE.Scene();
      var geometry = new THREE.BoxGeometry( 1, 1, 1 );
      var material = new THREE.MeshBasicMaterial( { color: 0x00ff00 } );
      this.cube = new THREE.Mesh( geometry, material );
      this.scene.add( this.cube );

      var ambientLight = new THREE.AmbientLight(0x383838);
      this.scene.add(ambientLight);

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


    private createControls() {
      this.controls = new TrackballControls(this.camera, this.canvas);
      this.controls.rotateSpeed = 1.0;
      this.controls.zoomSpeed = 1.0;
      this.controls.panSpeed = 1.0;
    }

    ngAfterViewInit() {
      this.createScene();
      this.createCamera();
      this.createControls();
      this.startRendering();
    }


}
