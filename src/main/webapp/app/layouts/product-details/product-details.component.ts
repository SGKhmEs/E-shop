import {Component, OnDestroy, OnInit} from '@angular/core';
import {Products} from "../../entities/products/products.model";
import {ProductsService} from "./../shop-list/products/products.service";
import {ActivatedRoute} from "@angular/router";
import {ResponseWrapper} from "../../shared/model/response-wrapper.model";
import {Subscription} from "rxjs/Subscription";

@Component({
    selector: 'jhi-product-details',
    templateUrl: './product-details.html',
    styleUrls: [
        './product-details.css'
    ]
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
    private product: Products = [];
    private subscription: Subscription;
    constructor(private productsService: ProductsService, private route: ActivatedRoute) { }

    ngOnInit(): void {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
    // this.productsService.query().subscribe(
    //     (res: ResponseWrapper) => this.productLoadSuccess,
    //     (res: ResponseWrapper) => this.productLoadFail)
    }

    ngOnDestroy(): void {
        this.product = [];
    }
    load(id) {
         this.productsService.find(id).subscribe((p) => {
             this.product = p;
         });
    }
}
