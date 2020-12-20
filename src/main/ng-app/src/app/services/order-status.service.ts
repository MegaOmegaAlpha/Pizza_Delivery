import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class OrderStatusService {
    readonly apiUrl = `${environment.apiUrl}`;

    constructor(private httpClient: HttpClient) {}

    getAllStatuses(): Observable<any[]> {
        return this.httpClient.get<any[]>(`${this.apiUrl}/order-statuses`);
    }
}
