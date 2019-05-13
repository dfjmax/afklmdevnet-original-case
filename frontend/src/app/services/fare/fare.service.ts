import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

import { Fare } from '../../model/fare'

@Injectable({
  providedIn: 'root'
})
export class FareService {

	constructor(private http: Http) {}

	findFare(from: string, to: string, lang: string) : Observable<Fare>{
		let url = environment.apiUrl + '/fares/' + from + '/' + to + '?currency=EUR&lang=' + lang;
		return this.http.get(url).pipe(map((result:any)=>{
			return JSON.parse(result._body);
        }));
  	}

}
