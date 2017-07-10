import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
//import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

//import { ProfileService } from '../profiles/profile.service';
//import { Principal, LoginModalService, LoginService } from '../../shared';

//import { VERSION, DEBUG_INFO_ENABLED } from '../../app.constants';

@Component({
    selector: 'jhi-brand',
    templateUrl: './brand.component.html',
    styleUrls: [
        './brand.css'
    ]
})
export class HeaderComponent implements OnInit {



    ngOnInit() {
        // this.profileService.getProfileInfo().subscribe((profileInfo) => {
        //     this.inProduction = profileInfo.inProduction;
        //     this.swaggerEnabled = profileInfo.swaggerEnabled;
        // });
    }

}
