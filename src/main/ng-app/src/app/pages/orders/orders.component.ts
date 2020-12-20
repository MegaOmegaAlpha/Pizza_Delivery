import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';

import {CustomerOrderService, OrderDTO} from '../../services/customer-order.service';
import {TokenStorageService} from '../../core/auth/token-storage.service';
import {GridColumn} from '../../components/grid/grid.component';

@Component({
    selector: 'app-orders',
    templateUrl: './orders.component.html',
    styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
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
            key: 'address',
            header: 'Адрес (Улица, дом, квартира)',
            cell: row => `${row.address.street + ', ' + row.address.house + ', кв.' + row.address.flatNumber}`,
            sort: false
        },
    ];

    constructor(
        private ordersService: CustomerOrderService,
        private token: TokenStorageService,
        private toasterService: ToastrService,
    ) {
    }

    ngOnInit(): void {
        this.customerId = this.token.getUser().id;
        this.loadOrders();
    }

    loadOrders(): void {
        this.ordersService.getOrdersForCustomer(this.customerId).subscribe((result) => {
            this.data = result;
        });
    }
}
