import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private readonly apiUrl = `${environment.authApi}`;

    private readonly baseUrl = `${environment.apiUrl}`;

    constructor(private http: HttpClient) {}

    login(loginRequest: LoginRequest): Observable<any> {
        return this.http.put(`${this.apiUrl}`, loginRequest, httpOptions);
    }

    register(signUpRequest: SignUpRequest): Observable<any> {
        return this.http.post(`${this.baseUrl}/register`, signUpRequest, httpOptions);
    }
}

export interface LoginRequest {
    login: string;
    password: string;
}

export interface SignUpRequest {
    fullName: string;
    login: string;
    email: string;
    password: string;
    telephone: string;
    birthDate: string;
}
