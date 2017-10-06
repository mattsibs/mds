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

   constructor(private particleService: ParticleService) { }

   ngOnInit() {
    let eventSource = new EventSourcePolyfill('http://localhost:8080/particles/3');
    eventSource.onmessage = (data => {
        console.log(data);
    });

   }
}
