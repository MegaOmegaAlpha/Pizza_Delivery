import {Component, EventEmitter, Inject, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
    selector: 'app-confirm-dialog',
    templateUrl: './confirm-dialog.component.html',
    styleUrls: ['./confirm-dialog.component.css']
})
export class ConfirmDialogComponent  {
    @Output() yesClicked = new EventEmitter();

    constructor(
        public dialogRef: MatDialogRef<ConfirmDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public message: string) {
    }

    onNoClick(): void {
        this.dialogRef.close('No');
    }

    onYesClick(): void {
        this.yesClicked.next();
        this.dialogRef.close('Yes');
    }
}
