import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { BucketComponent } from './bucket.component';
import { BucketDetailComponent } from './bucket-detail.component';
import { BucketPopupComponent } from './bucket-dialog.component';
import { BucketDeletePopupComponent } from './bucket-delete-dialog.component';

import { Principal } from '../../shared';

export const bucketRoute: Routes = [
    {
        path: 'bucket',
        component: BucketComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Buckets'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'bucket/:id',
        component: BucketDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Buckets'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bucketPopupRoute: Routes = [
    {
        path: 'bucket-new',
        component: BucketPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Buckets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bucket/:id/edit',
        component: BucketPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Buckets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bucket/:id/delete',
        component: BucketDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Buckets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
