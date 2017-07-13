import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { AddressShipping } from './address-shipping.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AddressShippingService {

    private resourceUrl = 'api/address-shippings';
    private resourceSearchUrl = 'api/_search/address-shippings';

    constructor(private http: Http) { }

    create(addressShipping: AddressShipping): Observable<AddressShipping> {
        const copy = this.convert(addressShipping);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(addressShipping: AddressShipping): Observable<AddressShipping> {
        const copy = this.convert(addressShipping);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AddressShipping> {
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

    private convert(addressShipping: AddressShipping): AddressShipping {
        const copy: AddressShipping = Object.assign({}, addressShipping);
        return copy;
    }
}
