import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ProducersComponent } from './producers.component';
import { ProducersDetailComponent } from './producers-detail.component';
import { ProducersPopupComponent } from './producers-dialog.component';
import { ProducersDeletePopupComponent } from './producers-delete-dialog.component';

import { Principal } from '../../shared';

export const producersRoute: Routes = [
    {
        path: 'producers',
        component: ProducersComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.producers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'producers/:id',
        component: ProducersDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.producers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const producersPopupRoute: Routes = [
    {
        path: 'producers-new',
        component: ProducersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.producers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'producers/:id/edit',
        component: ProducersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.producers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'producers/:id/delete',
        component: ProducersDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.producers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
