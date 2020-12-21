import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {GridColumn} from "../../components/grid/grid.component";
import {OrderDTO} from "../../services/customer-order.service";
import {CourierOrderService} from "../../services/courier-order.service";
import {TokenStorageService} from "../../core/auth/token-storage.service";

@Component({
    selector: 'app-courier-orders',
    templateUrl: './courier-orders.component.html',
    styleUrls: ['./courier-orders.component.scss']
})
export class CourierOrdersComponent implements OnInit {
    form: FormGroup;
    data: any[];

    courierId: number;

    gridColumns: GridColumn<any>[] = [
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
            key: 'orderDelivery',
            header: 'Время доставки',
            cell: row => row.orderDelivery ? `${row.orderDelivery.timeFinish}` : '',
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
        private ordersService: CourierOrderService,
        private token: TokenStorageService,
    ) {
    }

    ngOnInit(): void {
        this.courierId = this.token.getUser().id;
        this.loadOrders();
    }

    loadOrders(): void {
        this.ordersService.getOrders(this.courierId).subscribe((result) => {
            this.data = result;
        });
    }
}
