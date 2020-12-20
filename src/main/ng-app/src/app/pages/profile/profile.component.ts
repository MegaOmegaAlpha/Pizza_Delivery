import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {Subject} from 'rxjs';
import {takeUntil} from 'rxjs/operators';
import {faPlus, faTrash} from '@fortawesome/free-solid-svg-icons';
import {ToastrService} from 'ngx-toastr';

import {TokenStorageService} from '../../core/auth/token-storage.service';
import {CustomerDTO, CustomerService} from '../../services/customer.service';
import {CustomerAddressService} from '../../services/customer.address.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
    form: FormGroup;

    customerId: number;

    facPlus = faPlus;
    facTrash = faTrash;

    get addresses(): FormArray {
        return (this.form.get('addresses') || {}) as FormArray;
    }

    oldAddresses: any[] = [];

    private destroy$ = new Subject();

    constructor(
        private fb: FormBuilder,
        private token: TokenStorageService,
        private customerService: CustomerService,
        private customerAddressesService: CustomerAddressService,
        private toasterService: ToastrService,
    ) { }

    ngOnInit(): void {
        this.customerId = this.token.getUser().id;
        this.initForm();
        this.customerAddressesService.getCustomerAddresses(this.customerId).pipe(takeUntil(this.destroy$))
            .subscribe((result) => {
                if (result && result.length > 0) {
                    result.forEach(x => {
                        this.addresses.push(this.fb.group({
                            street: { value: x.street,  disabled: true },
                            house: { value: x.house,  disabled: true},
                            flatNumber: { value: x.flatNumber,  disabled: true},
                            id: x.id
                        }));
                        this.oldAddresses.push({
                            street: x.street,
                            house: x.house,
                            flatNumber: x.flatNumber,
                            id: x.id,
                        });
                    });
                }
        });

        this.customerService.getCustomer(this.customerId).pipe(takeUntil(this.destroy$))
            .subscribe((result) => {
            this.form.patchValue(result);
        });
    }

    newAddress(): FormGroup {
        return this.fb.group({
            street: '',
            house: '',
            flatNumber: '',
            id: null,
        });
    }

    addNewAddress(): void {
        this.addresses.push(this.newAddress());
    }

    deleteAddress(address: any, i: any): void {
        if (this.oldAddresses.find(x => x.id === address.value.id)) {
            this.customerAddressesService.removeCustomerAddress(this.customerId, address.value, address.value.id).subscribe(() => {
                this.addresses.removeAt(i);
                this.toasterService.success('Адрес успешно удалён', 'Успешно');
            });
        } else {
            this.addresses.removeAt(i);
        }
    }

    saveScreen(): void {
        const formValue = this.form.value;
        this.customerService.updateCustomer({
            id: this.customerId,
            fullName: formValue.fullName,
            telephone: formValue.telephone,
            email: formValue.email,
            birthDate: formValue.birthDate,
            login: formValue.login,
            currentNumberOfOrders: formValue.currentNumberOfOrders
        } as CustomerDTO).subscribe((result) => {
            this.toasterService.success('Личная информация сохранена', 'Успешно');
        });

        if (this.oldAddresses && this.addresses.value.length && this.oldAddresses.length !== this.addresses.value.length) {
            const addressesToCreate = this.addresses.value.filter(x => x.id === null);
            if (addressesToCreate && addressesToCreate.length > 0) {
                addressesToCreate.forEach(x => {
                    const pidor = this.addresses.controls.find(y => y.value === {
                        flatNumber: x.flatNumber,
                        street: x.street,
                        house: x.house,
                        id: null
                    });
                    console.log(pidor);
                    this.customerAddressesService.createCustomerAddress(this.customerId, {
                        flatNumber: x.flatNumber,
                        street: x.street,
                        house: x.house,
                        id: this.getRandomNum(10, 10000000000)
                    }).subscribe(() => {});
                });
            }
        }

        this.form.markAsPristine();
        this.form.markAllAsTouched();
    }

    private getRandomNum(min: number, max: number): number {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    private initForm(): void {
        this.form = this.fb.group({
            fullName: [''],
            telephone: [''],
            email: [''],
            birthDate: [new Date()],
            login: [''],
            currentNumberOfOrders: [null],
            addresses: this.fb.array([])
        });
    }

    ngOnDestroy(): void {
        this.destroy$.next();
    }
}
