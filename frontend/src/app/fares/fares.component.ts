import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { AirportService } from '../services/airport/airport.service'
import { FareService } from '../services/fare/fare.service'
import { Airport } from '../model/airport'
import { Fare } from '../model/fare'

@Component({
  selector: 'app-fares',
  templateUrl: './fares.component.html',
  styleUrls: ['./fares.component.scss']
})
export class FaresComponent implements OnInit {

	airportFrom = new FormControl();
	airportTo = new FormControl();
  	filteredAirportsFrom: Airport[];
  	filteredAirportsTo: Airport[];
  	fareDetail: Fare;
  	isLoading = false;

  	constructor(private airportService: AirportService, private fareService: FareService) { }

	ngOnInit() {
		this.airportFrom.valueChanges
			.subscribe(queryField =>this.airportService.search(queryField, 'EN')
				.subscribe(response => this.filteredAirportsFrom = response));
		this.airportTo.valueChanges
			.subscribe(queryField =>this.airportService.search(queryField, 'EN')
				.subscribe(response => this.filteredAirportsTo = response));
  	}

  	findFare(form) {
  		if (!this.airportFrom.valid || !this.airportTo.valid) {
  			return;
  		}
  		this.isLoading = true;
  		this.fareService.findFare(this.airportFrom.value, this.airportTo.value, 'EN').subscribe(result =>  {
  			this.isLoading = false;
  			this.fareDetail = result;
  		})
	}

}
