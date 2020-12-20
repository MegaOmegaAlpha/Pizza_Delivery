import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';

import { TokenStorageService } from '../auth/token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private token: TokenStorageService) {}

    private static cloneHeaders(req: HttpRequest<any>): StringMap<string> {
        const httpHeaders: StringMap<string> = {};
        if (req.headers) {
            req.headers.keys().forEach(k => {
                httpHeaders[k] = req.headers.get(k);
            });
        }
        return httpHeaders;
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        const token = this.token.getToken();
        const httpHeaders = AuthInterceptor.cloneHeaders(request);

        httpHeaders.Authorization = 'Bearer ' + token;

        if (token) {
            const req = request.clone({ setHeaders: httpHeaders });
            return next.handle(req);
        } else {
            return next.handle(request);
        }
    }
}
