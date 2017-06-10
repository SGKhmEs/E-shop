import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { WishListComponent } from './wish-list.component';
import { WishListDetailComponent } from './wish-list-detail.component';
import { WishListPopupComponent } from './wish-list-dialog.component';
import { WishListDeletePopupComponent } from './wish-list-delete-dialog.component';

import { Principal } from '../../shared';

export const wishListRoute: Routes = [
    {
        path: 'wish-list',
        component: WishListComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.wishList.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'wish-list/:id',
        component: WishListDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.wishList.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const wishListPopupRoute: Routes = [
    {
        path: 'wish-list-new',
        component: WishListPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.wishList.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'wish-list/:id/edit',
        component: WishListPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.wishList.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'wish-list/:id/delete',
        component: WishListDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.wishList.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
