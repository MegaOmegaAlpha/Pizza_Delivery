import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AuthService } from './auth/auth.service';
import { HTTP_INTERCEPTOR_PROVIDERS } from './interceptors';


@NgModule({
    declarations: [],
    imports: [
        HttpClientModule,
        CommonModule
    ],
    providers: [AuthService, ...HTTP_INTERCEPTOR_PROVIDERS]
})
export class CoreModule {}
