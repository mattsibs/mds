import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

import { Particle } from '../_models/index';
import {EventSourcePolyfill} from 'ng-event-source';

@Injectable()
export class ParticleService {

    constructor(
        private http: Http) {

    }

    getParticles(particleConsumer): void {
         let eventSource = new EventSourcePolyfill('http://localhost:8080/particles');
         eventSource.onmessage = (data) => this.setParticle(data, eventSource, particleConsumer);
    }

    iterateParticles(particleConsumer, iterations): void {
         let eventSource = new EventSourcePolyfill('http://localhost:8080/particles/' + iterations);
         eventSource.onmessage = (data) => this.setParticle(data, eventSource, particleConsumer);
    }

    setParticle(data, eventSource, particleConsumer) : void {
           var responseObject = JSON.parse(data.data);
           var isComplete = responseObject['isComplete'];

           if (isComplete) {
              eventSource.close();
              return;
           }

           particleConsumer(responseObject['particle']);
       }
}
