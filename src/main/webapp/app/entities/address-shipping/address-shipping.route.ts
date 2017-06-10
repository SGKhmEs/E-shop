import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { AddressShippingComponent } from './address-shipping.component';
import { AddressShippingDetailComponent } from './address-shipping-detail.component';
import { AddressShippingPopupComponent } from './address-shipping-dialog.component';
import { AddressShippingDeletePopupComponent } from './address-shipping-delete-dialog.component';

import { Principal } from '../../shared';

export const addressShippingRoute: Routes = [
    {
        path: 'address-shipping',
        component: AddressShippingComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.addressShipping.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'address-shipping/:id',
        component: AddressShippingDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.addressShipping.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const addressShippingPopupRoute: Routes = [
    {
        path: 'address-shipping-new',
        component: AddressShippingPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.addressShipping.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'address-shipping/:id/edit',
        component: AddressShippingPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.addressShipping.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'address-shipping/:id/delete',
        component: AddressShippingDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.addressShipping.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
