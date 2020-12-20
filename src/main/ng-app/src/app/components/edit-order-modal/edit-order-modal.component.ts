import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AdminService} from "../../services/admin.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ToastrService} from "ngx-toastr";

@Component({
    selector: 'app-edit-order-modal',
    templateUrl: './edit-order-modal.component.html',
    styleUrls: ['./edit-order-modal.component.scss']
})
export class EditOrderModalComponent implements OnInit {
    form: FormGroup;

    constructor(
        public dialogRef: MatDialogRef<EditOrderModalComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private adminService: AdminService,
        private toasterService: ToastrService,
    ) {}

    ngOnInit(): void {
        this.form = new FormGroup({
            status: new FormControl('')
        });
        this.form.get('status').setValue(this.data.row.orderStatus.id);
    }

    apply() {
        this.adminService.updateOrderStatus(this.data.row.id, this.form.get('status').value).subscribe(() => {
            this.toasterService.success('Статус успешно обновлен', 'Успех')
            this.closeDialog('');
        })
    }

    closeDialog(reason: any): void {
        this.dialogRef.close(reason);
    }
}
