import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ConfirmComponent } from './confirm.component';
import { ConfirmDetailComponent } from './confirm-detail.component';
import { ConfirmPopupComponent } from './confirm-dialog.component';
import { ConfirmDeletePopupComponent } from './confirm-delete-dialog.component';

import { Principal } from '../../shared';

export const confirmRoute: Routes = [
    {
        path: 'confirm',
        component: ConfirmComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.confirm.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'confirm/:id',
        component: ConfirmDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.confirm.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const confirmPopupRoute: Routes = [
    {
        path: 'confirm-new',
        component: ConfirmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.confirm.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'confirm/:id/edit',
        component: ConfirmPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.confirm.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'confirm/:id/delete',
        component: ConfirmDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.confirm.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
