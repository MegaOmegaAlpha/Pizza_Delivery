import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CustomerDTO} from "./customer.service";

@Injectable({
    providedIn: 'root'
})
export class CourierService {
    readonly apiUrl = `${environment.apiUrl}`;

    constructor(private http: HttpClient) {
    }

    getCourier(courierId: number): Observable<CustomerDTO> {
        return this.http.get<CustomerDTO>(`${this.apiUrl}/courier/${courierId}`);
    }
}
