import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AdminService} from '../../services/admin.service';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-add-product-modal',
    templateUrl: './add-product-modal.component.html',
    styleUrls: ['./add-product-modal.component.scss']
})
export class AddProductModalComponent implements OnInit {
    form: FormGroup;

    constructor(
        public dialogRef: MatDialogRef<AddProductModalComponent>,
        private adminService: AdminService,
        private toasterService: ToastrService,
    ) {}

    ngOnInit(): void {
        this.initForm();
    }

    addItem(): void {
        const pizza = this.form.value;
        const file = this.form.value.file;
        debugger
        this.adminService.savePizza(pizza, file).subscribe(() => {
            this.toasterService.success('Товар успешно сохранен', 'Успех');
            this.closeDialog('');
        });
    }

    private initForm(): void {
        this.form = new FormGroup({
            name: new FormControl('', [Validators.required]),
            desc: new FormControl('', [Validators.required]),
            price: new FormControl('', [Validators.required]),
            file: new FormControl(null, [Validators.required])
        });
    }

    closeDialog(reason: any): void {
        this.dialogRef.close(reason);
    }
}
