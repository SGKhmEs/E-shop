import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { CustomerRoom } from './customer-room.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class CustomerRoomService {

    private resourceUrl = 'api/customer-rooms';
    private resourceSearchUrl = 'api/_search/customer-rooms';

    constructor(private http: Http) { }

    create(customerRoom: CustomerRoom): Observable<CustomerRoom> {
        const copy = this.convert(customerRoom);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(customerRoom: CustomerRoom): Observable<CustomerRoom> {
        const copy = this.convert(customerRoom);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<CustomerRoom> {
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
        return new ResponseWrapper(res.headers, jsonResponse);
    }

    private convert(customerRoom: CustomerRoom): CustomerRoom {
        const copy: CustomerRoom = Object.assign({}, customerRoom);
        return copy;
    }
}
