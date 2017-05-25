import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { OptionsComponent } from './options.component';
import { OptionsDetailComponent } from './options-detail.component';
import { OptionsPopupComponent } from './options-dialog.component';
import { OptionsDeletePopupComponent } from './options-delete-dialog.component';

import { Principal } from '../../shared';

export const optionsRoute: Routes = [
    {
        path: 'options',
        component: OptionsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.options.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'options/:id',
        component: OptionsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.options.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const optionsPopupRoute: Routes = [
    {
        path: 'options-new',
        component: OptionsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.options.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'options/:id/edit',
        component: OptionsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.options.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'options/:id/delete',
        component: OptionsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.options.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
