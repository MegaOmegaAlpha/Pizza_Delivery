import {
    Component, EventEmitter,
    Input,
    OnInit, Output,
    QueryList,
    ViewChild, ViewChildren
} from '@angular/core';
import { MatTable} from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { faEdit, faTrash, faPlus} from '@fortawesome/free-solid-svg-icons';
import {getBadgeStatus, getDefaultDialogConfig} from '../../constants';
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {EditOrderModalComponent} from "../edit-order-modal/edit-order-modal.component";
import {AdminService} from "../../services/admin.service";
import {TokenStorageService} from "../../core/auth/token-storage.service";

@Component({
    selector: 'app-grid',
    templateUrl: './grid.component.html',
    styleUrls: ['./grid.component.scss']
})
export class GridComponent<T> implements OnInit {
    @ViewChild(MatTable, { static: true }) table: MatTable<T>;
    @ViewChildren(MatSort) sort = new QueryList<MatSort>();

    @Input() data: T[];
    @Input() gridColumns: GridColumn<T>[];
    @Input() actionColumn = false;

    @Output() addEmit =  new EventEmitter();
    @Output() editEmit =  new EventEmitter<number>();
    @Output() removeEmit = new EventEmitter();

    displayedColumns: string[];
    statuses: any[];
    roles: any[] = [];

    faEdit: any = faEdit;
    faTrash: any = faTrash;
    faPlus: any = faPlus;

    getBadgeStatus = getBadgeStatus;

    constructor(
        private dialog: MatDialog,
        private ordersService: AdminService,
        private token: TokenStorageService,
    ) {}

    ngOnInit(): void {
        this.displayedColumns = this.gridColumns ? this.gridColumns.map(x => x.key) : [];
        if (this.displayedColumns && this.actionColumn) {
            this.displayedColumns.push('actionsColumn');
        }

        this.roles = this.getRoles()
        if (this.actionColumn && this.isAdmin()) {
            this.loadStatuses();
        }
    }

    getRoles() {
        const user = this.token.getUser();
        return user.roleList;
    }

    isAdmin(): boolean {
        return !!this.roles.find(x => x === 'ADMIN');
    }


    createNew(): void {
        this.addEmit.emit();
    }

    loadStatuses() {
        this.ordersService.getAllStatuses().subscribe((response) => {
            this.statuses = response;
        });
    }

    edit(row: any): void {
        this.editEmit.next(row.id);
        const dialogConfig = getDefaultDialogConfig({ row, statuses: this.statuses}, '500px', '300px');
        const dialogRef: MatDialogRef<EditOrderModalComponent> = this.dialog.open(EditOrderModalComponent, dialogConfig);
    }

    delete(row: any): void {
        this.removeEmit.emit(row);
    }
}

export interface GridColumn<T> {
    key: string;
    header: string;
    cell: (row: T) => string;
    sort: boolean;
    class?: string
}
