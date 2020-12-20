import {Injectable} from '@angular/core';
import {TokenStorageService} from "../core/auth/token-storage.service";

@Injectable({
    providedIn: 'root'
})
export class RoleService {
    roles: string[] = [];
    rolesLoaded = false;

    constructor(private tokenStorageService: TokenStorageService) {
        this.initService();
    }

    isCourier(): boolean {
        return this.roles && this.roles.length > 0 ? !!this.roles.find(x => x === 'courier'.toLocaleUpperCase()) : false;
    }

    isAdmin(): boolean  {
        return this.roles && this.roles.length > 0 ? !!this.roles.find(x => x === 'admin'.toLocaleUpperCase()) : false;
    }

    isUser(): boolean  {
        return this.roles && this.roles.length > 0 ? !!this.roles.find(x => x === 'CUSTOMER') : false;
    }

    initService() {
        if (!this.rolesLoaded) {
            const user = this.tokenStorageService.getUser();
            this.roles = user.roleList;
            this.rolesLoaded = true;
        }
    }
}
