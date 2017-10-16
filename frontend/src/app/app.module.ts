import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing }  from './app.routing';

import { AppComponent } from './app.component';
import { ParticleService } from './_services/index';
import { SimulationComponent } from './simulation/simulation.component';


@NgModule({
  declarations: [
    AppComponent,
    SimulationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    ParticleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
