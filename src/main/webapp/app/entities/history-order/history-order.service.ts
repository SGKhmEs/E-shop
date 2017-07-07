import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { DateUtils } from 'ng-jhipster';

import { HistoryOrder } from './history-order.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class HistoryOrderService {

    private resourceUrl = 'api/history-orders';
    private resourceSearchUrl = 'api/_search/history-orders';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    create(historyOrder: HistoryOrder): Observable<HistoryOrder> {
        const copy = this.convert(historyOrder);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    update(historyOrder: HistoryOrder): Observable<HistoryOrder> {
        const copy = this.convert(historyOrder);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            this.convertItemFromServer(jsonResponse);
            return jsonResponse;
        });
    }

    find(id: number): Observable<HistoryOrder> {
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
        return new ResponseWrapper(res.headers, jsonResponse);
    }

    private convertItemFromServer(entity: any) {
        entity.date = this.dateUtils
            .convertDateTimeFromServer(entity.date);
    }

    private convert(historyOrder: HistoryOrder): HistoryOrder {
        const copy: HistoryOrder = Object.assign({}, historyOrder);

        copy.date = this.dateUtils.toDate(historyOrder.date);
        return copy;
    }
}
