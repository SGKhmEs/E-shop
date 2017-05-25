import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AddressShippingDetailComponent } from '../../../../../../main/webapp/app/entities/address-shipping/address-shipping-detail.component';
import { AddressShippingService } from '../../../../../../main/webapp/app/entities/address-shipping/address-shipping.service';
import { AddressShipping } from '../../../../../../main/webapp/app/entities/address-shipping/address-shipping.model';

describe('Component Tests', () => {

    describe('AddressShipping Management Detail Component', () => {
        let comp: AddressShippingDetailComponent;
        let fixture: ComponentFixture<AddressShippingDetailComponent>;
        let service: AddressShippingService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [AddressShippingDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AddressShippingService,
                    EventManager
                ]
            }).overrideComponent(AddressShippingDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AddressShippingDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressShippingService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AddressShipping(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.addressShipping).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
