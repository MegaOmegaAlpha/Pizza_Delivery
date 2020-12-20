import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AdminService {
    readonly apiUrl = `${environment.apiUrl}/admin`;

    constructor(private httpClient: HttpClient) {
    }

    getOrders(adminId: number): Observable<any> {
        return this.httpClient.get(`${this.apiUrl}/${adminId}/orders`);
    }

    getAllOrders(): Observable<any> {
        return this.httpClient.get(`${this.apiUrl}/orders`);
    }

    savePizza(pizza: any, file: File): Observable<any> {
        let fd = new FormData();
        fd.append('image',  file);
        return this.httpClient.post(`${this.apiUrl}/pizzas`, fd);
    }

    getAllStatuses(): Observable<any[]> {
        return this.httpClient.get<any[]>(`${this.apiUrl}/order-statuses`);
    }

    updateOrderStatus(orderId: number, statusId: number) {
        return this.httpClient.put(`${environment.apiUrl}/orders/${orderId}/status/${statusId}`, {});
    }
}
