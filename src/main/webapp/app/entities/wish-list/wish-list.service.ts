import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils } from 'ng-jhipster';

import { WishList } from './wish-list.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class WishListService {

    private resourceUrl = 'api/wish-lists';
    private resourceSearchUrl = 'api/_search/wish-lists';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(wishList: WishList): Observable<WishList> {
        const copy = this.convert(wishList);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(wishList: WishList): Observable<WishList> {
        const copy = this.convert(wishList);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<WishList> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
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
        for (let i = 0; i < jsonResponse.length; i++) {
            this.convertItemFromServer(jsonResponse[i]);
        }
<<<<<<< HEAD
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
=======
        return new ResponseWrapper(res.headers, jsonResponse);
>>>>>>> with_entities
    }

    private convertItemFromServer(entity: any) {
        entity.date = this.dateUtils
            .convertLocalDateFromServer(entity.date);
    }

    private convert(wishList: WishList): WishList {
        const copy: WishList = Object.assign({}, wishList);
        copy.date = this.dateUtils
            .convertLocalDateToServer(wishList.date);
        return copy;
    }
}
