import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ValueComponent } from './value.component';
import { ValueDetailComponent } from './value-detail.component';
import { ValuePopupComponent } from './value-dialog.component';
import { ValueDeletePopupComponent } from './value-delete-dialog.component';

import { Principal } from '../../shared';

export const valueRoute: Routes = [
    {
        path: 'value',
        component: ValueComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.value.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'value/:id',
        component: ValueDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.value.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const valuePopupRoute: Routes = [
    {
        path: 'value-new',
        component: ValuePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.value.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'value/:id/edit',
        component: ValuePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.value.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'value/:id/delete',
        component: ValueDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.value.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
