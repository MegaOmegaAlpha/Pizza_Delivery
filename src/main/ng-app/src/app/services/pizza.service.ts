import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class PizzaService {
    readonly apiUrl = `${environment.apiUrl}/pizzas`;

    constructor(private http: HttpClient) {
    }

    getPizzas(): Observable<PizzaCatalogItem[]> {
        return this.http.get<PizzaCatalogItem[]>(`${this.apiUrl}`);
    }

    getPizza(id: number): Observable<PizzaCatalogItem> {
        return this.http.get<PizzaCatalogItem>(`${this.apiUrl}/${id}`);
    }

    savePizza(pizza: PizzaCatalogItem): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}`, pizza);
    }

    updatePizza(pizza: PizzaCatalogItem): Observable<any> {
        return this.http.put<any>(`${this.apiUrl}`, pizza);
    }
}


export interface PizzaCatalogItem {
    id: number;
    imgUrl?: string;
    name?: string;
    fullName?: string;
    composition: string;
    price: string;
    available?: boolean;
    multipartFile?: any;
    bytes?: any;
}
