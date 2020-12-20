import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    set isLoginFailed(value: boolean) {
        this._isLoginFailed = value;
    }

    get isLoginFailed(): boolean {
        return this._isLoginFailed;
    }

    get isLoggedIn(): boolean {
        return this._isLoggedIn;
    }

    set isLoggedIn(value: boolean) {
        this._isLoggedIn = value;
    }

    get currentUser(): any {
        return this._currentUser;
    }

    set currentUser(value: any) {
        this._currentUser = value;
    }

    private _isLoggedIn = false;
    private _isLoginFailed = false;
    private _currentUser: any;

    userIds: number[] = [5, 6, 7];

    constructor() {
        this._currentUser = {
            id: this.userIds[0]
        };
    }
}
