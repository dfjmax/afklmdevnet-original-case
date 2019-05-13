import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

import { Airport } from '../../model/airport'

@Injectable({
  providedIn: 'root'
})
export class AirportService {

	constructor(private http: Http) {}

	search(term: string, lang: string) : Observable<Airport[]>{
		let url = environment.apiUrl + '/airports?term=' + term + '&lang=' + lang;
		return this.http.get(url).pipe(map((result:any)=>{
			return JSON.parse(result._body)._embedded.airportList;
        }));
  	}

}
