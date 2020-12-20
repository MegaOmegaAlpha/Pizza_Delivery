import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../core/auth/token-storage.service';
import {getDefaultDialogConfig} from '../constants';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {BucketService} from '../services/bucket.service';
import {BucketComponent} from '../pages/bucket/bucket.component';
import {AddProductModalComponent} from '../components/add-product-modal/add-product-modal.component';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    isLoggedIn = false;
    username?: string;
    roles: string[] = [];

    constructor(
        private tokenStorageService: TokenStorageService,
        private bucketService: BucketService,
        public dialog: MatDialog,
        private toasterService: ToastrService,
    ) { }

    ngOnInit(): void {
        this.isLoggedIn = !!this.tokenStorageService.getToken();
        if (this.isLoggedIn) {
            const user = this.tokenStorageService.getUser();
            this.roles = user.roleList;

            this.username = user.username;
        }
    }

    isCourier(): boolean {
        return !!this.roles.find(x => x === 'courier'.toLocaleUpperCase());
    }

    isAdmin(): boolean  {
        return !!this.roles.find(x => x === 'admin'.toLocaleUpperCase());
    }

    isUser(): boolean  {
        return !!this.roles.find(x => x === 'CUSTOMER'.toLocaleUpperCase());
    }

    openBucket(): void {
        const bucket = this.bucketService.getBucket();
        if (bucket.length === 0) {
            this.toasterService.warning('В корзине пока пусто', 'Предупреждение');
            return;
        }
        const dialogConfig = getDefaultDialogConfig(bucket, '500px', '450px');
        const dialogRef: MatDialogRef<BucketComponent> = this.dialog.open(BucketComponent, dialogConfig);
    }

    openAddProduct(): void {
        const dialogConfig = getDefaultDialogConfig(this.bucketService.getBucket(), '600px', '550px');
        const dialogRef: MatDialogRef<AddProductModalComponent> = this.dialog.open(AddProductModalComponent, dialogConfig);
    }

    logout(): void {
        this.tokenStorageService.signOut();
        window.location.reload();
    }
}
