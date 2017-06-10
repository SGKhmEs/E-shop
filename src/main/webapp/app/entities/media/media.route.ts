import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { MediaComponent } from './media.component';
import { MediaDetailComponent } from './media-detail.component';
import { MediaPopupComponent } from './media-dialog.component';
import { MediaDeletePopupComponent } from './media-delete-dialog.component';

import { Principal } from '../../shared';

export const mediaRoute: Routes = [
    {
        path: 'media',
        component: MediaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.media.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'media/:id',
        component: MediaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.media.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mediaPopupRoute: Routes = [
    {
        path: 'media-new',
        component: MediaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.media.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'media/:id/edit',
        component: MediaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.media.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'media/:id/delete',
        component: MediaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.media.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
