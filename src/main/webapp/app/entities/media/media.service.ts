import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { Media } from './media.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class MediaService {

    private resourceUrl = 'api/media';
    private resourceSearchUrl = 'api/_search/media';

    constructor(private http: Http) { }

    create(media: Media): Observable<Media> {
        const copy = this.convert(media);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(media: Media): Observable<Media> {
        const copy = this.convert(media);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<Media> {
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
<<<<<<< HEAD
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
=======
        return new ResponseWrapper(res.headers, jsonResponse);
>>>>>>> with_entities
    }

    private convert(media: Media): Media {
        const copy: Media = Object.assign({}, media);
        return copy;
    }
}
