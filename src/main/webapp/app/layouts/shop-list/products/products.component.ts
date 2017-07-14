import {Component, OnDestroy, OnInit} from '@angular/core';
import {Products} from "../../../entities/products/products.model";
import {ProductsService} from "./products.service";
import {ActivatedRoute} from "@angular/router";
import {ResponseWrapper} from "../../../shared/model/response-wrapper.model";

@Component({
    selector: 'jhi-product',
    templateUrl: './products.component.html',
    styleUrls: [
        'products-component.css'
    ]
})
export class ProductsComponent implements OnInit, OnDestroy {
    private itemProduct: Products[];

    constructor(private productsService: ProductsService, private route: ActivatedRoute) {

    }


    ngOnInit(): void {
        this.productsService.query().subscribe(
            (res: ResponseWrapper) => this.productLoadSuccess,
            (res: ResponseWrapper) => this.productLoadFail)
    }

    ngOnDestroy(): void {
        this.itemProduct = [];
    }

    productLoadSuccess = (list: Products[]) => {
        this.itemProduct = list;
        // const years = new Set(list.map(i => i.readYear)); // for unique
        // this.bookGroups = Array.from(years).sort().reverse()
        //     .map(y => {
        //         return {
        //             year: y,
        //             count: this.userBooks.filter(b => b.readYear === y).length
        //         }
        //     });
    }

    productLoadFail = (err: string) => {
        this.itemProduct = [];
        // this.error = err;
    }
}
