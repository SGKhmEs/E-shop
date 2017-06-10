import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { SeenComponent } from './seen.component';
import { SeenDetailComponent } from './seen-detail.component';
import { SeenPopupComponent } from './seen-dialog.component';
import { SeenDeletePopupComponent } from './seen-delete-dialog.component';

import { Principal } from '../../shared';

export const seenRoute: Routes = [
    {
        path: 'seen',
        component: SeenComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.seen.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'seen/:id',
        component: SeenDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.seen.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const seenPopupRoute: Routes = [
    {
        path: 'seen-new',
        component: SeenPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.seen.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'seen/:id/edit',
        component: SeenPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.seen.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'seen/:id/delete',
        component: SeenDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.seen.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
