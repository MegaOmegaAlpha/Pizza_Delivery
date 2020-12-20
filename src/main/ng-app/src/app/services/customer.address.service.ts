import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CustomerAddressService {
    readonly apiUrl = `${environment.apiUrl}`;

    constructor(private http: HttpClient) {}

    getCustomerAddresses(customerId: number): Observable<AddressDTO[]> {
        return this.http.get<AddressDTO[]>(`${this.apiUrl}/customer/${customerId}/addresses`);
    }

    createCustomerAddress(customerId: number, addressDTO: Partial<AddressDTO>): Observable<AddressDTO> {
        return this.http.post<AddressDTO>(`${this.apiUrl}/customer/${customerId}/addresses`, addressDTO);
    }

    removeCustomerAddress(customerId: number, addressDTO: AddressDTO, addressId: number): Observable<any> {
        return this.http.delete(`${this.apiUrl}/customer/${customerId}/addresses/${addressId}`);
    }
}

export interface AddressDTO {
    id: number;
    street: string;
    house: string;
    flatNumber: string;
}
