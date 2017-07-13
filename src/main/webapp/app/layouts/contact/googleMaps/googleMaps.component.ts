import { Component } from '@angular/core';

@Component({
    selector: 'jhi-googleMaps',
    templateUrl: './googleMaps.component.html',
    styleUrls: ['./googleMaps.component.css'],
})
export class GoogleMapComponent {
    title: string = 'Google Maps ';
    lat: number = 49.42629169;
    lng: number = 26.97973275;
}
