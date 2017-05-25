import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { TypeComponent } from './type.component';
import { TypeDetailComponent } from './type-detail.component';
import { TypePopupComponent } from './type-dialog.component';
import { TypeDeletePopupComponent } from './type-delete-dialog.component';

import { Principal } from '../../shared';

export const typeRoute: Routes = [
    {
        path: 'type',
        component: TypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.type.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'type/:id',
        component: TypeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.type.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typePopupRoute: Routes = [
    {
        path: 'type-new',
        component: TypePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.type.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type/:id/edit',
        component: TypePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.type.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'type/:id/delete',
        component: TypeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.type.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
