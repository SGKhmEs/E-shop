import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ManagerAccountComponent } from './manager-account.component';
import { ManagerAccountDetailComponent } from './manager-account-detail.component';
import { ManagerAccountPopupComponent } from './manager-account-dialog.component';
import { ManagerAccountDeletePopupComponent } from './manager-account-delete-dialog.component';

import { Principal } from '../../shared';

export const managerAccountRoute: Routes = [
    {
        path: 'manager-account',
        component: ManagerAccountComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ManagerAccounts'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'manager-account/:id',
        component: ManagerAccountDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ManagerAccounts'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const managerAccountPopupRoute: Routes = [
    {
        path: 'manager-account-new',
        component: ManagerAccountPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ManagerAccounts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'manager-account/:id/edit',
        component: ManagerAccountPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ManagerAccounts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'manager-account/:id/delete',
        component: ManagerAccountDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ManagerAccounts'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
