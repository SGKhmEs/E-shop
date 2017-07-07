import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { HistoryOrderComponent } from './history-order.component';
import { HistoryOrderDetailComponent } from './history-order-detail.component';
import { HistoryOrderPopupComponent } from './history-order-dialog.component';
import { HistoryOrderDeletePopupComponent } from './history-order-delete-dialog.component';

import { Principal } from '../../shared';

export const historyOrderRoute: Routes = [
    {
        path: 'history-order',
        component: HistoryOrderComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.historyOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'history-order/:id',
        component: HistoryOrderDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.historyOrder.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const historyOrderPopupRoute: Routes = [
    {
        path: 'history-order-new',
        component: HistoryOrderPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.historyOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'history-order/:id/edit',
        component: HistoryOrderPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.historyOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'history-order/:id/delete',
        component: HistoryOrderDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.historyOrder.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
