import {Component, OnInit} from '@angular/core';

import {PizzaCatalogItem, PizzaService} from '../../services/pizza.service';
import {ToastrService} from 'ngx-toastr';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {AddItemModalComponent} from '../../components/add-item-modal/add-item-modal.component';
import {getDefaultDialogConfig} from '../../constants';
import {TokenStorageService} from '../../core/auth/token-storage.service';
import {RoleService} from "../../services/role.service";

@Component({
    selector: 'app-catalog-menu',
    templateUrl: './catalog-menu.component.html',
    styleUrls: ['./catalog-menu.component.css']
})
export class CatalogMenuComponent implements OnInit {
    pizzaItems: PizzaCatalogItem[] = [];
    roles: string[] = [];
    isLoaded = false;

    pizzaImageMap: { [key: string]: any } = {
        1: 'assets/img/carbonara.jpeg',
        2: 'assets/img/pepperoni.jpg',
        3: 'assets/img/cheese_chicken.jpg',
        4: 'assets/img/meat.jpg',
    };

    constructor(
        private apiService: PizzaService,
        private toasterService: ToastrService,
        public dialogService: MatDialog,
        private token: TokenStorageService,
        public roleService: RoleService,
    ) {}

    ngOnInit(): void {
        this.getPizzasFromServer();
    }

    onAddToBucketClicked = (pizza: PizzaCatalogItem) => {
        const user = this.token.getUser();
        const count = Object.keys(user).length;
        if (count !== 0) {
            const dialogConfig = getDefaultDialogConfig({
                pizza,
            }, '500px', '450px');

            const dialogRef: MatDialogRef<AddItemModalComponent> = this.dialogService.open(AddItemModalComponent, dialogConfig);
        } else {
            this.toasterService.warning('Для добавления в корзину сначала зайдите в свой аккаунт', 'Предупреждение');
        }
    }

    getPizzasFromServer(): void {
        this.apiService.getPizzas().subscribe((result: PizzaCatalogItem[]) => {
            this.pizzaItems = this.getPizzaItems(result);
            this.isLoaded = true;
        });
    }

    getPizzaItems(result: PizzaCatalogItem[]): any[] {
        return result.map((item, idx) => {
            return Object.assign({}, {
                id: item.id,
                fullName: item.name,
                composition: item.composition,
                displayedPrice: 'от ' + item.price + ' ₽',
                price: item.price,
                available: item.available ? item.available : true,
                imgUrl: this.pizzaImageMap[item.id]
            });
        });
    }
}
