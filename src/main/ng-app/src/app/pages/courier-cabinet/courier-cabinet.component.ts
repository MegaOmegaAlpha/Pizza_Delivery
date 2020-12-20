import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../core/auth/token-storage.service";

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
    ) {}

    ngOnInit(): void {
        this.courierId = this.token.getUser().id;
        this.initForm();
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
}
