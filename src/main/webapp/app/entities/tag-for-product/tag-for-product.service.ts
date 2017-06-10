import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { TagForProduct } from './tag-for-product.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TagForProductService {

    private resourceUrl = 'api/tag-for-products';
    private resourceSearchUrl = 'api/_search/tag-for-products';

    constructor(private http: Http) { }

    create(tagForProduct: TagForProduct): Observable<TagForProduct> {
        const copy = this.convert(tagForProduct);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(tagForProduct: TagForProduct): Observable<TagForProduct> {
        const copy = this.convert(tagForProduct);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<TagForProduct> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    search(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceSearchUrl, options)
            .map((res: any) => this.convertResponse(res));
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(tagForProduct: TagForProduct): TagForProduct {
        const copy: TagForProduct = Object.assign({}, tagForProduct);
        return copy;
    }
}
