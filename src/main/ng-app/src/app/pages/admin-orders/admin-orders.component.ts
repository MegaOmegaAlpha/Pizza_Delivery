import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";

import {GridColumn} from "../../components/grid/grid.component";
import {OrderDTO} from "../../services/customer-order.service";
import {TokenStorageService} from "../../core/auth/token-storage.service";
import {AdminService} from "../../services/admin.service";

@Component({
    selector: 'app-admin-orders',
    templateUrl: './admin-orders.component.html',
    styleUrls: ['./admin-orders.component.scss']
})
export class AdminOrdersComponent implements OnInit {
    statuses: any[];
    form: FormGroup;
    data: any[];

    customerId: number;

    gridColumns: GridColumn<OrderDTO>[] = [
        {
            key: 'id',
            header: 'Номер заказа',
            cell: row => `${row.id}`,
            sort: false
        },
        {
            key: 'orderDate',
            header: 'Дата заказа',
            cell: row => `${row.orderDate}`,
            sort: false
        },
        {
            key: 'orderTime',
            header: 'Время заказа',
            cell: row => `${row.orderTime}`,
            sort: false
        },
        {
            key: 'totalPrice',
            header: 'Стоимость',
            cell: row => `${row.totalPrice}`,
            sort: false
        },
        {
            key: 'orderStatus',
            header: 'Статус',
            cell: row => `${row.orderStatus.name}`,
            sort: false
        },
        {
            key: 'user',
            header: 'Покупатель',
            cell: row => `${row.user.fullName}`,
            sort: false
        },
        {
            key: 'address',
            header: 'Адрес (Улица, дом, квартира)',
            cell: row => `${row.address.street + ', ' + row.address.house + ', кв.' + row.address.flatNumber}`,
            sort: false
        },
    ];

    constructor(
        private ordersService: AdminService,
        private token: TokenStorageService,
        private toasterService: ToastrService,
        private dialog: MatDialog
    ) {}

    ngOnInit(): void {
        this.customerId = this.token.getUser().id;
        this.loadOrders();
    }

    loadOrders(): void {
        this.ordersService.getAllOrders(this.customerId).subscribe((result) => {
            this.data = result;
        });
    }
}
