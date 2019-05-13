import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
 
import { MatButtonModule, MatFormFieldModule, MatInputModule, MatRippleModule, MatProgressSpinnerModule, MatIconModule } from '@angular/material';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FaresComponent } from './fares/fares.component';

import { AirportService } from './services/airport/airport.service'
import { FareService } from './services/fare/fare.service'

@NgModule({
  declarations: [
    AppComponent,
    FaresComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpModule,
    BrowserAnimationsModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    MatAutocompleteModule,
    MatProgressSpinnerModule,
    MatIconModule
  ],
  providers: [AirportService, FareService],
  bootstrap: [AppComponent]
})
export class AppModule { }
