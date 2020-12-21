import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';

import {PIZZA_CRUSTS, PIZZA_SIZES} from '../../constants';
import {BucketService} from '../../services/bucket.service';
import {DomSanitizer} from "@angular/platform-browser";


@Component({
    selector: 'app-add-item-modal',
    templateUrl: './add-item-modal.component.html',
    styleUrls: ['./add-item-modal.component.scss']
})
export class AddItemModalComponent implements OnInit {
    form: FormGroup;

    pizzaSizes = PIZZA_SIZES;
    pizzaCrusts = PIZZA_CRUSTS;

    constructor(
        public dialogRef: MatDialogRef<AddItemModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: { pizza: any },
        private bucketService: BucketService,
        private toasterService: ToastrService,
        public sanitizer: DomSanitizer
    ) {}

    ngOnInit(): void {
        this.form = new FormGroup({
            crust: new FormControl(''),
            size: new FormControl(''),
        });
    }

    getImage(base64: any) {
        return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpg;base64,'
            + base64);
    }

    addItem(): void {
        this.bucketService.setBucket(Object.assign({}, this.data, {
            crust: this.form.get('crust').value,
            size: this.form.get('size').value,
            count: 1,
            price: this.getPrice()
        }));

        this.toasterService.success('Успешно добавлен в корзину', 'Успех');
        this.closeDialog('Added');
    }

    getPrice(): string {
        const crustCoef: number = this.getCrustCoef();
        const sizeCoef: number = this.getSizeCoef();
        return ((+this.data.pizza.price) * crustCoef * sizeCoef).toString() + '';
    }

    getCrustCoef(): number {
        return this.form.get('crust').value ? (this.form.get('crust').value === 'thin' ? 1 : 1.1) : 1;
    }

    getSizeCoef(): number {
        const size = this.form.get('size').value;
        if (size) {
            switch (size) {
                case 'small':
                    return 1;
                case 'average':
                    return 1.3;
                case 'big':
                    return 1.5;
                case 'huge':
                    return 1.6;
                default:
                    return 1;
            }
        }
    }

    closeDialog(reason: any): void {
        this.dialogRef.close(reason);
    }
}
