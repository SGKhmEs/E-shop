import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { StorageComponent } from './storage.component';
import { StorageDetailComponent } from './storage-detail.component';
import { StoragePopupComponent } from './storage-dialog.component';
import { StorageDeletePopupComponent } from './storage-delete-dialog.component';

import { Principal } from '../../shared';

export const storageRoute: Routes = [
    {
        path: 'storage',
        component: StorageComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.storage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'storage/:id',
        component: StorageDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.storage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const storagePopupRoute: Routes = [
    {
        path: 'storage-new',
        component: StoragePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.storage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'storage/:id/edit',
        component: StoragePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.storage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'storage/:id/delete',
        component: StorageDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.storage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
