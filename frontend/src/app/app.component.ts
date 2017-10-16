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

}
