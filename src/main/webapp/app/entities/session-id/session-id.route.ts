import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { SessionIdComponent } from './session-id.component';
import { SessionIdDetailComponent } from './session-id-detail.component';
import { SessionIdPopupComponent } from './session-id-dialog.component';
import { SessionIdDeletePopupComponent } from './session-id-delete-dialog.component';

import { Principal } from '../../shared';

export const sessionIdRoute: Routes = [
    {
        path: 'session-id',
        component: SessionIdComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.sessionId.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'session-id/:id',
        component: SessionIdDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.sessionId.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sessionIdPopupRoute: Routes = [
    {
        path: 'session-id-new',
        component: SessionIdPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.sessionId.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'session-id/:id/edit',
        component: SessionIdPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.sessionId.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'session-id/:id/delete',
        component: SessionIdDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.sessionId.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
