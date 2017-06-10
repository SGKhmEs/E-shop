import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AddressComponent } from './address.component';
import { AddressDetailComponent } from './address-detail.component';
import { AddressPopupComponent } from './address-dialog.component';
import { AddressDeletePopupComponent } from './address-delete-dialog.component';

import { Principal } from '../../shared';

export const addressRoute: Routes = [
    {
        path: 'address',
        component: AddressComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'address/:id',
        component: AddressDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.address.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const addressPopupRoute: Routes = [
    {
        path: 'address-new',
        component: AddressPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'address/:id/edit',
        component: AddressPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'address/:id/delete',
        component: AddressDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.address.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
