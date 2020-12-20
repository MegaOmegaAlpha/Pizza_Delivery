import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class BucketService {
    readonly storageKey: string = 'bucket';

    constructor() {
        this.initBucket();
    }

    public getBucket(): any {
        const bucket = sessionStorage.getItem(this.storageKey);
        if (bucket) {
            return JSON.parse(bucket);
        }
        return {};
    }

    setBucket(pizza: any): void {
        const bucket = sessionStorage.getItem(this.storageKey);
        if (bucket !== '[]') {
            const bucketList = JSON.parse(bucket);

            const idx = bucketList.findIndex(x => x.pizza.id === pizza.pizza.id && x.size === pizza.size && pizza.crust === x.crust);
            if (idx !== -1) {
                bucketList[idx].count += 1;
                sessionStorage.removeItem(this.storageKey);
                sessionStorage.setItem(this.storageKey, JSON.stringify(bucketList));
            } else {
                bucketList.push(pizza);
                sessionStorage.removeItem(this.storageKey);
                sessionStorage.setItem(this.storageKey, JSON.stringify(bucketList));
            }
        } else {
            const bucketList = [pizza];
            sessionStorage.setItem(this.storageKey, JSON.stringify(bucketList));
        }
    }

    setBucketFull(pizzas: any[]): void {
        sessionStorage.removeItem(this.storageKey);
        sessionStorage.setItem(this.storageKey, JSON.stringify(pizzas));
    }

    initBucket(): void {
        const emptyBucket = [];
        if (!sessionStorage.getItem(this.storageKey)) {
            sessionStorage.setItem(this.storageKey, JSON.stringify(emptyBucket));
        }
    }
}
