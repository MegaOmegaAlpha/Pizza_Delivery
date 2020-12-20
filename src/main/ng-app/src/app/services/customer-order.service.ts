import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {environment} from '../../environments/environment';
import {CustomerDTO} from './customer.service';
import {AddressDTO} from './customer.address.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerOrderService {
    readonly apiUrl = `${environment.apiUrl}/customer`;

    constructor(private http: HttpClient) {}

    getOrdersForCustomer(customerId: number): Observable<OrderDTO[]> {
        return this.http.get<OrderDTO[]>(`${this.apiUrl}/${customerId}/orders`);
    }

    getOrderForCustomer(customerId: number, orderId: number): Observable<OrderDTO> {
        return this.http.get<OrderDTO>(`${this.apiUrl}/${customerId}/orders/${orderId}`);
    }

    createOrderForCustomer(customerId: number, order: Partial<OrderDTO>): Observable<any> {
        return this.http.post(`${this.apiUrl}/${customerId}/orders`, order);
    }
}


export interface OrderDTO {
    user?: CustomerDTO;
    orderStatus?: OrderStatusDTO;
    id: number;
    orderDate: string;
    orderTime: string;
    lastStatusUpdateTime: string;
    totalPrice: number;
    commentary: string;
    chosenPaymentMethod: ChosenPaymentMethodDTO;
    address: AddressDTO;
}

export interface OrderStatusDTO {
    id: number;
    name: string;
}

export interface ChosenPaymentMethodDTO {
    id: number;
    cardNumber: string;
    cvc: string;
    paymentMethod: PaymentMethodDTO;
    customer: CustomerDTO;
}

export interface PaymentMethodDTO {
    id: number;
    name: string;
}
