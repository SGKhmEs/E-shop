import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CustomerAccountComponent } from './customer-account.component';
import { CustomerAccountDetailComponent } from './customer-account-detail.component';
import { CustomerAccountPopupComponent } from './customer-account-dialog.component';
import { CustomerAccountDeletePopupComponent } from './customer-account-delete-dialog.component';

import { Principal } from '../../shared';

export const customerAccountRoute: Routes = [
    {
        path: 'customer-account',
        component: CustomerAccountComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerAccounts'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'customer-account/:id',
        component: CustomerAccountDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerAccounts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customerAccountPopupRoute: Routes = [
    {
        path: 'customer-account-new',
        component: CustomerAccountPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerAccounts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-account/:id/edit',
        component: CustomerAccountPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerAccounts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-account/:id/delete',
        component: CustomerAccountDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerAccounts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
