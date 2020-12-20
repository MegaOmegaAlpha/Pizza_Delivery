import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class AdminService {
    readonly apiUrl = `${environment.apiUrl}admin`;

    constructor(private httpClient: HttpClient) {
    }

    savePizza(pizza: any, file: any): Observable<any> {
        const fd = new FormData();
        fd.append('name', pizza.name);
        fd.append('composition', pizza.desc);
        fd.append('price', pizza.price);
        fd.append('multipartFile',  file, file.name);
        return this.httpClient.post(`${this.apiUrl}/pizzas`, fd);
    }
}
