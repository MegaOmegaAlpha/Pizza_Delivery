import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

import {CatalogMenuComponent} from './pages/catalog-menu/catalog-menu.component';
import {LoginComponent} from './components/login/login.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {RegisterComponent} from './components/register/register.component';
import {OrdersComponent} from './pages/orders/orders.component';
import {OrderComponent} from './pages/order/order.component';
import {CourierOrdersComponent} from "./pages/courier-orders/courier-orders.component";
import {AdminOrdersComponent} from "./pages/admin-orders/admin-orders.component";
import {CourierCabinetComponent} from "./pages/courier-cabinet/courier-cabinet.component";

const routes: Routes = [
    { path: '', component: CatalogMenuComponent},
    { path: 'catalog', component: CatalogMenuComponent},
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'orders', component: OrdersComponent },
    { path: 'order', component: OrderComponent },
    { path: 'courier-orders', component: CourierOrdersComponent },
    { path: 'admin-orders', component: AdminOrdersComponent },
    { path: 'courier-cabinet', component: CourierCabinetComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
