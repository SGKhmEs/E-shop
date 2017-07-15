/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AddressDetailComponent } from '../../../../../../main/webapp/app/entities/address/address-detail.component';
import { AddressService } from '../../../../../../main/webapp/app/entities/address/address.service';
import { Address } from '../../../../../../main/webapp/app/entities/address/address.model';

describe('Component Tests', () => {

    describe('Address Management Detail Component', () => {
        let comp: AddressDetailComponent;
        let fixture: ComponentFixture<AddressDetailComponent>;
        let service: AddressService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [AddressDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AddressService,
                    JhiEventManager
                ]
            }).overrideTemplate(AddressDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AddressDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Address(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.address).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
