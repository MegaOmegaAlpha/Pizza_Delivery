import { MatDialogConfig } from '@angular/material/dialog';

const DIALOG_CONFIG = new MatDialogConfig();

export function getDefaultDialogConfig(data: any, width?: string, height?: string): MatDialogConfig {
    const dialogConfig = DIALOG_CONFIG;
    dialogConfig.width = width ? width : '650px';
    dialogConfig.height = height ? height : '480px';
    dialogConfig.data = data ? data : null;
    return dialogConfig;
}

export const PIZZA_SIZES: { label: string, value: string }[] = [
    {
        label: 'Маленькая (25см)',
        value: 'small'
    },
    {
        label: 'Средняя (30см)',
        value: 'average'
    },
    {
        label: 'Большая (35см)',
        value: 'big'
    },
    {
        label: 'Огромная (40см)',
        value: 'huge'
    }
];

export const PIZZA_CRUSTS: { label: string, value: string }[] = [
    {
        label: 'Тонкое',
        value: 'thin'
    },
    {
        label: 'Толстое',
        value: 'thiсk'
    }
];

export const TITLE_MAP: StringMap<string> = {
    thin: 'Тонкое',
    thiсk: 'Толстое',
    small: 'Маленькая (25см)',
    average: 'Средняя (30см)',
    big: 'Большая (35см)',
    huge: 'Огромная (40см)'
};

export function getBadgeStatus(state: string): string {
    if (state === 'Доставлено') {
        return 'arrived-badge';
    } else if (state === 'Передан курьеру'){
        return 'courier-badge';
    } else if (state === 'Заказ оформлен') {
        return 'oformlen-badge';
    } else if (state === 'Готовится') {
        return 'not-ready-badge';
    }
}


