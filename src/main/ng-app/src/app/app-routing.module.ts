import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

import {CatalogMenuComponent} from './pages/catalog-menu/catalog-menu.component';
import {LoginComponent} from './components/login/login.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {RegisterComponent} from './components/register/register.component';
import {OrdersComponent} from './pages/orders/orders.component';
import {OrderComponent} from './pages/order/order.component';

const routes: Routes = [
    { path: '', component: CatalogMenuComponent},
    { path: 'catalog', component: CatalogMenuComponent},
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'orders', component: OrdersComponent },
    { path: 'order', component: OrderComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
