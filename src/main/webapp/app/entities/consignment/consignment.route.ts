import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConsignmentComponent } from './consignment.component';
import { ConsignmentDetailComponent } from './consignment-detail.component';
import { ConsignmentPopupComponent } from './consignment-dialog.component';
import { ConsignmentDeletePopupComponent } from './consignment-delete-dialog.component';

import { Principal } from '../../shared';

@Injectable()
export class ConsignmentResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: PaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const consignmentRoute: Routes = [
    {
        path: 'consignment',
        component: ConsignmentComponent,
        resolve: {
            'pagingParams': ConsignmentResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.consignment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'consignment/:id',
        component: ConsignmentDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.consignment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consignmentPopupRoute: Routes = [
    {
        path: 'consignment-new',
        component: ConsignmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.consignment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consignment/:id/edit',
        component: ConsignmentPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.consignment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consignment/:id/delete',
        component: ConsignmentDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.consignment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
