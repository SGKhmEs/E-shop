import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { CommentsComponent } from './comments.component';
import { CommentsDetailComponent } from './comments-detail.component';
import { CommentsPopupComponent } from './comments-dialog.component';
import { CommentsDeletePopupComponent } from './comments-delete-dialog.component';

import { Principal } from '../../shared';

export const commentsRoute: Routes = [
    {
        path: 'comments',
        component: CommentsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.comments.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'comments/:id',
        component: CommentsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.comments.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const commentsPopupRoute: Routes = [
    {
        path: 'comments-new',
        component: CommentsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.comments.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'comments/:id/edit',
        component: CommentsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.comments.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'comments/:id/delete',
        component: CommentsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.comments.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
