import {Component, Input} from '@angular/core';
import {Products} from "../../../../entities/products/products.model";
import {ActivatedRoute} from "@angular/router";

@Component({
    selector: 'jhi-product-item',
    templateUrl: './product-item.component.html',
    styleUrls: [
        'product-item.component.css'
    ]
})
export class ProductItemComponent {
    @Input() item: Products;
    constructor(private route: ActivatedRoute) {

    }
}
