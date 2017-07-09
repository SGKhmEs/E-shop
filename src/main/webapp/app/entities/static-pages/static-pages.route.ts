import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { StaticPagesComponent } from './static-pages.component';
import { StaticPagesDetailComponent } from './static-pages-detail.component';
import { StaticPagesPopupComponent } from './static-pages-dialog.component';
import { StaticPagesDeletePopupComponent } from './static-pages-delete-dialog.component';

import { Principal } from '../../shared';

export const staticPagesRoute: Routes = [
    {
        path: 'static-pages',
        component: StaticPagesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StaticPages'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'static-pages/:id',
        component: StaticPagesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StaticPages'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const staticPagesPopupRoute: Routes = [
    {
        path: 'static-pages-new',
        component: StaticPagesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StaticPages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'static-pages/:id/edit',
        component: StaticPagesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StaticPages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'static-pages/:id/delete',
        component: StaticPagesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StaticPages'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
