import {Component, OnInit} from '@angular/core'
import { Particle } from './_models/index';
import { ParticleService } from './_services/index';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map';

import {EventSourcePolyfill} from 'ng-event-source';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
   particles: Particle[] = [];

   particle = {};
   particleString = "";

   constructor(private particleService: ParticleService) { }

   ngOnInit() {
    console.log("onInit");
    let eventSource = new EventSourcePolyfill('http://localhost:8080/particles/10');
    eventSource.onmessage = (data) => this.setParticle(data, eventSource);
   }

   setParticle(data, eventSource) : void {
       var responseObject = JSON.parse(data.data);
       var isComplete = responseObject['isComplete'];

       console.log(isComplete);
       if (isComplete) {
          eventSource.close();
          return;
       }

       var id = responseObject['particle']['id'];
       this.particle[id] = responseObject;

       this.particleString = "";
       this.particleString = JSON.stringify(this.particle);



   }
}
