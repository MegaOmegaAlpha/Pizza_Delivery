import {Component, OnInit} from '@angular/core';

import {AuthService, LoginRequest} from '../../core/auth/auth.service';
import {TokenStorageService} from '../../core/auth/token-storage.service';
import {UserService} from '../../services/user.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    form: LoginRequest = {
        login: '',
        password: ''
    };
    isLoggedIn = false;
    isLoginFailed = false;
    errorMessage = '';
    roles: string[] = [];

    constructor(private authService: AuthService, private tokenStorage: TokenStorageService, public userService: UserService) {}

    ngOnInit(): void {
        if (this.tokenStorage.getToken()) {
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getUser().roleList;
        }
    }

    onSubmit(): void {
        this.authService.login(this.form).subscribe(
            data => {
                this.tokenStorage.saveToken(data.accessToken);
                this.tokenStorage.saveUser(data.userDTO);
                this.userService.currentUser = data.userDTO;

                this.roles = this.tokenStorage.getUser().roleList;
                this.reloadPage();
            },
            err => {
                this.errorMessage = err.error.message;
                this.isLoginFailed = true;
            }
        );
    }

    reloadPage(): void {
        window.location.reload();
    }
}
