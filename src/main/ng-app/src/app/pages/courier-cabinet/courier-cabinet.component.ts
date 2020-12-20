import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../core/auth/token-storage.service";
import {CourierService} from "../../services/courier.service";

@Component({
    selector: 'app-courier-cabinet',
    templateUrl: './courier-cabinet.component.html',
    styleUrls: ['./courier-cabinet.component.scss']
})
export class CourierCabinetComponent implements OnInit {
    form: FormGroup;

    courierId: number;

    constructor(
        private fb: FormBuilder,
        private token: TokenStorageService,
        private courierService: CourierService
    ) {}

    ngOnInit(): void {
        this.courierId = this.token.getUser().id;
        this.initForm();
        this.loadData();
    }

    private loadData() {
        this.courierService.getCourier(this.courierId).subscribe((resp) => {
            this.form.patchValue(resp);
        })
    }

    private initForm(): void {
        this.form = this.fb.group({
            fullName: [{ value: '', disabled: true }, []],
            telephone: [{ value: '', disabled: true }, []],
            birthDate: [{ value: '', disabled: true }, []],
            driverLicense: [{ value: '', disabled: true }, []],
            identityNumber: [{ value: '', disabled: true }, []],
        });
    }
}
