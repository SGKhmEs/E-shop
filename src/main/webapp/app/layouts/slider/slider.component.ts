import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { ProfileService } from '../profiles/profile.service';
import { Principal, LoginModalService, LoginService } from '../../shared';

// import { VERSION, DEBUG_INFO_ENABLED } from '../../app.constants';
// import {require} from "../../../../../test/javascript/spec/entry";
// var $ = require("jquery");

@Component({
    selector: 'jhi-slider',
    templateUrl: './slider.html',
    styleUrls: [
        './slider.css', './../../../content/css/nivo-slider.css'
    ]
})
export class SliderComponent {
}
