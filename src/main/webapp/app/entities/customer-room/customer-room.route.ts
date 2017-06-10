import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { CustomerRoomComponent } from './customer-room.component';
import { CustomerRoomDetailComponent } from './customer-room-detail.component';
import { CustomerRoomPopupComponent } from './customer-room-dialog.component';
import { CustomerRoomDeletePopupComponent } from './customer-room-delete-dialog.component';

import { Principal } from '../../shared';

export const customerRoomRoute: Routes = [
    {
        path: 'customer-room',
        component: CustomerRoomComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.customerRoom.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'customer-room/:id',
        component: CustomerRoomDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.customerRoom.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customerRoomPopupRoute: Routes = [
    {
        path: 'customer-room-new',
        component: CustomerRoomPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.customerRoom.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-room/:id/edit',
        component: CustomerRoomPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.customerRoom.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-room/:id/delete',
        component: CustomerRoomDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.customerRoom.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
