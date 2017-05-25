import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ProductsComponent } from './products.component';
import { ProductsDetailComponent } from './products-detail.component';
import { ProductsPopupComponent } from './products-dialog.component';
import { ProductsDeletePopupComponent } from './products-delete-dialog.component';

import { Principal } from '../../shared';

export const productsRoute: Routes = [
    {
        path: 'products',
        component: ProductsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.products.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'products/:id',
        component: ProductsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.products.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productsPopupRoute: Routes = [
    {
        path: 'products-new',
        component: ProductsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.products.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'products/:id/edit',
        component: ProductsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.products.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'products/:id/delete',
        component: ProductsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.products.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
