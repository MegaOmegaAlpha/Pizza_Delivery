import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CourierOrderService {
    readonly apiUrl = `${environment.apiUrl}/courier`;

    constructor(private http: HttpClient) {}

    getOrders(courierId: number): Observable<any> {
        return this.http.get<any>(`${this.apiUrl}/${courierId}/orders`)
    }
}
