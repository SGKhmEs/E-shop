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
    private itemProduct: Products[] = [];
    private totalItems: number;

    constructor(private productsService: ProductsService, private route: ActivatedRoute) {

    }
    loadAll() {
        this.productsService.query().subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }


    ngOnInit(): void {
        this.loadAll();
        // this.productsService.query().subscribe(
        //     (res: ResponseWrapper) => this.productLoadSuccess,
        //     (res: ResponseWrapper) => this.productLoadFail)
    }

    ngOnDestroy(): void {
        this.itemProduct = [];
    }

    private onSuccess(data, headers) {
        // this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        for (let i = 0; i < data.length; i++) {
            this.itemProduct.push(data[i]);
        }
    }

    private onError(error) {
       console.log("error");
    }

}
