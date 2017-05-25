import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { LoginOptionsComponent } from './login-options.component';
import { LoginOptionsDetailComponent } from './login-options-detail.component';
import { LoginOptionsPopupComponent } from './login-options-dialog.component';
import { LoginOptionsDeletePopupComponent } from './login-options-delete-dialog.component';

import { Principal } from '../../shared';

export const loginOptionsRoute: Routes = [
    {
        path: 'login-options',
        component: LoginOptionsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.loginOptions.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'login-options/:id',
        component: LoginOptionsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.loginOptions.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const loginOptionsPopupRoute: Routes = [
    {
        path: 'login-options-new',
        component: LoginOptionsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.loginOptions.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'login-options/:id/edit',
        component: LoginOptionsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.loginOptions.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'login-options/:id/delete',
        component: LoginOptionsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.loginOptions.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
