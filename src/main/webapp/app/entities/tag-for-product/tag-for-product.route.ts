import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { TagForProductComponent } from './tag-for-product.component';
import { TagForProductDetailComponent } from './tag-for-product-detail.component';
import { TagForProductPopupComponent } from './tag-for-product-dialog.component';
import { TagForProductDeletePopupComponent } from './tag-for-product-delete-dialog.component';

import { Principal } from '../../shared';

export const tagForProductRoute: Routes = [
    {
        path: 'tag-for-product',
        component: TagForProductComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.tagForProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tag-for-product/:id',
        component: TagForProductDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.tagForProduct.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tagForProductPopupRoute: Routes = [
    {
        path: 'tag-for-product-new',
        component: TagForProductPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.tagForProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tag-for-product/:id/edit',
        component: TagForProductPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.tagForProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tag-for-product/:id/delete',
        component: TagForProductDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.tagForProduct.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
