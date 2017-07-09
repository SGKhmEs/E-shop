import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Manager } from './manager.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class ManagerService {

    private resourceUrl = 'api/managers';
    private resourceSearchUrl = 'api/_search/managers';

    constructor(private http: Http) { }

    create(manager: Manager): Observable<Manager> {
        const copy = this.convert(manager);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(manager: Manager): Observable<Manager> {
        const copy = this.convert(manager);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Manager> {
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

    private convert(manager: Manager): Manager {
        const copy: Manager = Object.assign({}, manager);
        return copy;
    }
}
