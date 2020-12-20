import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {takeUntil} from 'rxjs/operators';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Subject} from 'rxjs';

import {TokenStorageService} from '../../core/auth/token-storage.service';
import {CustomerService} from '../../services/customer.service';
import {CustomerAddressService} from '../../services/customer.address.service';
import {BucketService} from '../../services/bucket.service';
import {TITLE_MAP} from '../../constants';
import {CustomerOrderService} from '../../services/customer-order.service';


@Component({
    selector: 'app-order',
    templateUrl: './order.component.html',
    styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit, OnDestroy {
    form: FormGroup;
    addresses: any[];
    orders: any[];
    titleMap = TITLE_MAP;

    customerId: number;

    private destroy$ = new Subject();

    constructor(
        private fb: FormBuilder,
        private token: TokenStorageService,
        private customerService: CustomerService,
        private customerAddressesService: CustomerAddressService,
        private toasterService: ToastrService,
        private bucketService: BucketService,
        private customerOrderService: CustomerOrderService,
        private router: Router,
    ) {}

    ngOnInit(): void {
        this.customerId = this.token.getUser().id;

        this.initForm();
        this.loadCustomer();
        this.loadAddresses();
        this.orders = this.getOrderData();
    }

    changeCheckbox(event: any, type: 'nal' | 'cardCourier' | 'cardOnline'): void {
        if (event.checked) {
            switch (type) {
                case 'cardCourier':
                    this.form.get('nal').patchValue(false);
                    this.form.get('cardOnline').patchValue(false);
                    break;
                case 'cardOnline':
                    this.form.get('nal').patchValue(false);
                    this.form.get('cardCourier').patchValue(false);
                    break;
                case 'nal':
                    this.form.get('cardOnline').patchValue(false);
                    this.form.get('cardCourier').patchValue(false);
                    break;
            }
        }
    }

    private getOrderData(): any {
        return this.bucketService.getBucket();
    }

    getOrderPrice(): string {
        const initValue = 0;
        const totalPrice = this.orders.reduce((acc, value) => {
            return acc + parseInt(value.price) * value.count;
        }, initValue);
        return totalPrice;
    }

    packOrder(): void {
        const customer = {
            birthDate: this.form.get('birthDate').value,
            currentNumberOfOrders: this.form.get('currentNumberOfOrders').value,
            email: this.form.get('email').value,
            fullName: this.form.get('fullName').value,
            id: this.form.get('id').value,
            login: this.form.get('login').value,
            telephone: this.form.get('telephone').value,
        };

        const chosenPaymentMethod = this.form.get('cardOnline').value === true ?
            {
                paymentMethod: {
                    name: 'Картой онлайн',
                    id: 14
                },
                cardNumber: this.form.get('cardNumber').value,
                cvc: this.form.get('cvc').value,
                customer
            }
            : {
                paymentMethod: {
                    id: this.form.get('nal').value === true ? 12 : this.form.get('cardCourier').value === true ? 13 : 14,
                    name: this.form.get('nal').value === true ? 'Наличными курьеру' : this.form.get('cardCourier').value === true ? 'Картой' : 'Наличными курьеру'
                },
                cardNumber: this.form.get('cardNumber').value,
                cvc: this.form.get('cvc').value,
                customer
            };

        const address = this.addresses.find(x => this.form.get('address').value);

        const pizzaOrderList = this.getOrderData().map(x => Object.assign({}, {
            amount: x.count,
            doughType: x.crust,
            pizzaSize: x.size,
            pizza: x.pizza
        }));

        const order = {
            orderDate: Date.now(),
            orderTime: Date.now(),
            lastStatusUpdateTime: null,
            address,
            totalPrice: parseInt(this.getOrderPrice()),
            commentary: this.form.get('comment').value,
            chosenPaymentMethod,
            pizzaOrderList
        };


        // @ts-ignore
        this.customerOrderService.createOrderForCustomer(this.customerId, order).subscribe(() => {
            this.toasterService.success('Заказ успешно создан', 'Успешно');
            const emptyBucket = [];
            sessionStorage.setItem('bucket', JSON.stringify(emptyBucket));
            this.router.navigate(['orders']);
        });
    }

    private loadCustomer(): void {
        this.customerService.getCustomer(this.customerId).pipe(takeUntil(this.destroy$))
            .subscribe((result) => {
                this.form.patchValue(result);
            });
    }

    private loadAddresses(): void {
        this.customerAddressesService.getCustomerAddresses(this.customerId).pipe(takeUntil(this.destroy$))
            .subscribe((result) => {
                this.addresses = result;
            });
    }

    private initForm(): void {
        this.form = this.fb.group({
            id: [null, []],
            fullName: [{value: '', disabled: true}],
            telephone: [{value: '', disabled: true}],
            email: [{value: '', disabled: true}],
            birthDate: [{value: '', disabled: true}],
            login: [{value: '', disabled: true}],
            currentNumberOfOrders: [{value: '', disabled: true}],
            address: ['', []],
            nal: [true, []],
            cardCourier: [false, []],
            cardOnline: [false, []],
            cardNumber: ['', []],
            dateExpire: ['', []],
            cvc: ['', []],
            comment: ['', []],
        });
    }

    ngOnDestroy(): void {
        this.destroy$.next();
    }
}
