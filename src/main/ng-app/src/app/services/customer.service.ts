import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class CustomerService {
    readonly apiUrl = `${environment.apiUrl}/`;

    constructor(private http: HttpClient) {
    }

    getCustomer(customerId: number): Observable<CustomerDTO> {
        return this.http.get<CustomerDTO>(`${this.apiUrl}customer/${customerId}`);
    }

    updateCustomer(customer: CustomerDTO): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}customer`,
            customer
        );
    }

    register(customer: CustomerDTO): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/customer`, {
            customerDTO: customer
        });
    }
}

export interface CustomerDTO {
    birthDate: string;
    currentNumberOfOrders: number;
    email: string;
    fullName: string;
    id: number;
    login: string;
    telephone: string;
}
