import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {ToastrModule} from 'ngx-toastr';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule} from '@angular/material/form-field';
import {CommonModule} from '@angular/common';
import {MatSortModule} from '@angular/material/sort';
import {MatInputModule} from '@angular/material/input';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';

import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {AppRoutingModule} from './app-routing.module';
import {CatalogMenuComponent} from './pages/catalog-menu/catalog-menu.component';
import {ConfirmDialogComponent} from './components/confirm-dialog/confirm-dialog.component';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {ProfileComponent} from './pages/profile/profile.component';
import { OrdersComponent } from './pages/orders/orders.component';
import {CoreModule} from './core/core.module';
import { BucketComponent } from './pages/bucket/bucket.component';
import {GridComponent} from './components/grid/grid.component';
import { AddItemModalComponent } from './components/add-item-modal/add-item-modal.component';
import {MatSelectModule} from '@angular/material/select';
import { OrderComponent } from './pages/order/order.component';
import { AddProductModalComponent } from './components/add-product-modal/add-product-modal.component';
import { CourierCabinetComponent } from './pages/courier-cabinet/courier-cabinet.component';
import { CourierOrdersComponent } from './pages/courier-orders/courier-orders.component';
import {NgxMatFileInputModule} from '@angular-material-components/file-input';
import { AdminOrdersComponent } from './pages/admin-orders/admin-orders.component';
import { EditOrderModalComponent } from './components/edit-order-modal/edit-order-modal.component';


@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        CatalogMenuComponent,
        ConfirmDialogComponent,
        RegisterComponent,
        LoginComponent,
        ProfileComponent,
        OrdersComponent,
        BucketComponent,
        GridComponent,
        AddItemModalComponent,
        OrderComponent,
        AddProductModalComponent,
        CourierCabinetComponent,
        CourierOrdersComponent,
        AdminOrdersComponent,
        EditOrderModalComponent
    ],
    imports: [
        CoreModule,
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        ToastrModule.forRoot(),
        HttpClientModule,
        MatTableModule,
        MatFormFieldModule,
        CommonModule,
        MatSortModule,
        MatInputModule,
        FontAwesomeModule,
        MatButtonModule,
        MatDialogModule,
        FormsModule,
        ReactiveFormsModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatSelectModule,
        MatCheckboxModule,
        NgxMatFileInputModule
    ],
    providers: [ConfirmDialogComponent, AddItemModalComponent, BucketComponent, AddProductModalComponent, EditOrderModalComponent],
    bootstrap: [AppComponent]
})
export class AppModule {}
