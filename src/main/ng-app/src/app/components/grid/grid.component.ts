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
import {getBadgeStatus} from '../../constants';

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
    @Output() editEmit =  new EventEmitter();
    @Output() removeEmit = new EventEmitter();

    displayedColumns: string[];

    faEdit: any = faEdit;
    faTrash: any = faTrash;
    faPlus: any = faPlus;

    getBadgeStatus = getBadgeStatus;

    constructor() {}

    ngOnInit(): void {
        this.displayedColumns = this.gridColumns ? this.gridColumns.map(x => x.key) : [];
        if (this.displayedColumns && this.actionColumn) {
            this.displayedColumns.push('actionsColumn');
        }
    }

    createNew(): void {
        this.addEmit.emit();
    }

    edit(row: any): void {
        this.editEmit.emit(row);
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
