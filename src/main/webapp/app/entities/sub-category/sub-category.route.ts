import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { SubCategoryComponent } from './sub-category.component';
import { SubCategoryDetailComponent } from './sub-category-detail.component';
import { SubCategoryPopupComponent } from './sub-category-dialog.component';
import { SubCategoryDeletePopupComponent } from './sub-category-delete-dialog.component';

import { Principal } from '../../shared';

export const subCategoryRoute: Routes = [
    {
        path: 'sub-category',
        component: SubCategoryComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SubCategories'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sub-category/:id',
        component: SubCategoryDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SubCategories'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const subCategoryPopupRoute: Routes = [
    {
        path: 'sub-category-new',
        component: SubCategoryPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SubCategories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-category/:id/edit',
        component: SubCategoryPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SubCategories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-category/:id/delete',
        component: SubCategoryDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SubCategories'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
