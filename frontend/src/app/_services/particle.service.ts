import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

import { Particle } from '../_models/index';

@Injectable()
export class ParticleService {

    constructor(
        private http: Http) {

    }

    getParticles(): Observable<Particle[]> {
        let options = new RequestOptions();

        this.http.get('http://localhost:8080/particles/2', options)
          .subscribe((data: Response) => console.log(data), console.error);

        return this.http.get('http://localhost:8080/particles/2', options)
            .map((response: Response) => response.json());
    }
}
