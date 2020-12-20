import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {TITLE_MAP} from '../../constants';
import {BucketService} from '../../services/bucket.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-bucket',
    templateUrl: './bucket.component.html',
    styleUrls: ['./bucket.component.scss']
})
export class BucketComponent implements OnInit{
    titleMap = TITLE_MAP;

    constructor(
        public dialogRef: MatDialogRef<BucketComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any[],
        public bucketService: BucketService,
        public router: Router,
    ) {}

    removeFromBucket(item: any): void {
        const index = this.data.indexOf(item);
        if (index > -1) {
            this.data.splice(index, 1);
        }

        this.bucketService.setBucketFull(this.data);
    }

    getOrderPrice(): string {
        const initValue = 0;
        const totalPrice = this.data.reduce((acc, value) => {
           return acc + parseInt(value.price) * value.count;
        }, initValue);
        return totalPrice;
    }

    navigateToOrders(): void {
        this.dialogRef.close('Navigated to orders');
        this.router.navigate(['order']);
    }

    closeDialog(reason: any): void {
        this.dialogRef.close(reason);
    }

    ngOnInit(): void {
    }
}
