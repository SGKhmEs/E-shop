import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProductInBucketComponent } from './product-in-bucket.component';
import { ProductInBucketDetailComponent } from './product-in-bucket-detail.component';
import { ProductInBucketPopupComponent } from './product-in-bucket-dialog.component';
import { ProductInBucketDeletePopupComponent } from './product-in-bucket-delete-dialog.component';

import { Principal } from '../../shared';

export const productInBucketRoute: Routes = [
    {
        path: 'product-in-bucket',
        component: ProductInBucketComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductInBuckets'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'product-in-bucket/:id',
        component: ProductInBucketDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductInBuckets'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productInBucketPopupRoute: Routes = [
    {
        path: 'product-in-bucket-new',
        component: ProductInBucketPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductInBuckets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-in-bucket/:id/edit',
        component: ProductInBucketPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductInBuckets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-in-bucket/:id/delete',
        component: ProductInBucketDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductInBuckets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
