import { Component} from '@angular/core';
import {ProductsService} from "../shop-list/products/products.service";

import {Component, OnDestroy, OnInit} from '@angular/core';
import {Products} from "../../entities/products/products.model";
import {ProductsService} from "./../shop-list/products/products.service";
import {ActivatedRoute} from "@angular/router";
import {ResponseWrapper} from "../../shared/model/response-wrapper.model";
import {ResponseWrapper} from "../../shared/model/response-wrapper.model";

@Component({
    selector: 'jhi-product-details',
    templateUrl: './product-details.html',
    styleUrls: [
        './product-details.css'
    ]
})
export class ProductDetailsComponent implements OnInit, OnDestroy {


constructor(private productsService: ProductsService, private route: ActivatedRoute) { }

    ngOnInit(): void {
        this.loadAll();
    // this.productsService.query().subscribe(
    //     (res: ResponseWrapper) => this.productLoadSuccess,
    //     (res: ResponseWrapper) => this.productLoadFail)
}

    ngOnDestroy(): void {
        this.itemProduct = [];
}
    loadAll() {
        this.productsService.find().subscribe(
            (res: ResponseWrapper) => this.onSuccess(res.json, res.headers),
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
}
