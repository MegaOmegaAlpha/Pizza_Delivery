import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Injectable({
    providedIn: 'root',
})
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private toaster: ToastrService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.headers.has('skip-global-error')) {
            req.headers.delete('skip-global-error');
            return next.handle(req);
        }
        return next.handle(req).pipe(
            catchError((err: HttpErrorResponse) => {
                this.parseError(err);
                return throwError(err);
            }),
        );
    }

    private async parseError(err: HttpErrorResponse): Promise<any> {
        if (err.error instanceof Blob) {
            const parsedError = JSON.parse(await err.error.text());
            err = {...err, message: parsedError.message};
        }
        if (err.message) {
            this.handleSpringError(err);
        } else if (err.error.error) {
            this.handleBaseHttpServiceError(err);
        } else {
            this.toaster.error('Handling unknown error format');
        }
    }

    private handleSpringError(err: HttpErrorResponse): void {
        switch (err.status) {
            case 401:
            case 403:
            case 404:
                this.toaster.error(err.message, `${err.status} ${err.statusText}`);
                break;

            case 400:
                this.parse400Exception(err);
                break;

            case 500:
                this.parse500Exception(err);
                break;

            case 503:
                this.toaster.error(err.statusText, `${err.status}`);
                break;

            case 504:
                this.toaster.error(err.message, err.error);
                break;

            default:
                this.toaster.error(err.error.message || err.message, err.error.error);
        }
    }

    private parse400Exception(errorResp: HttpErrorResponse): void {
        const error = errorResp.error;
        if (error.errors) {
            const body = `<ul>${error.errors.map((err: { defaultMessage: string }) => `<li>${err.defaultMessage}</li>`).join('')}</ul>`;
            this.toaster.warning(body, error.error, { enableHtml: true });
        } else {
            this.toaster.warning(error.message || errorResp.message, error.error);
        }
    }

    private parse500Exception(errorResp: HttpErrorResponse): void {
        if (errorResp.error) {
            if (errorResp.error.error) {
                this.toaster.error(errorResp.error.message || errorResp.message, errorResp.error.error);
            } else {
                this.toaster.error(`${errorResp.statusText}`, `Check your Network connection`);
            }
        } else {
            this.toaster.error(errorResp.message, 'Something went wrong');
        }
    }

    private handleBaseHttpServiceError(errorResp: HttpErrorResponse): void {
        const err = errorResp.error;
        this.toaster.error(`${err.status},  Message: ${err.error}`, `${err.message}`);
    }
}
