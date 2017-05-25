import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { PersonalInformationComponent } from './personal-information.component';
import { PersonalInformationDetailComponent } from './personal-information-detail.component';
import { PersonalInformationPopupComponent } from './personal-information-dialog.component';
import { PersonalInformationDeletePopupComponent } from './personal-information-delete-dialog.component';

import { Principal } from '../../shared';

export const personalInformationRoute: Routes = [
    {
        path: 'personal-information',
        component: PersonalInformationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.personalInformation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'personal-information/:id',
        component: PersonalInformationDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.personalInformation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const personalInformationPopupRoute: Routes = [
    {
        path: 'personal-information-new',
        component: PersonalInformationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.personalInformation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'personal-information/:id/edit',
        component: PersonalInformationPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.personalInformation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'personal-information/:id/delete',
        component: PersonalInformationDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'eshopApp.personalInformation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
