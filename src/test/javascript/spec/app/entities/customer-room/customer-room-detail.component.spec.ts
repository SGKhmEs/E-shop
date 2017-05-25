import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CustomerRoomDetailComponent } from '../../../../../../main/webapp/app/entities/customer-room/customer-room-detail.component';
import { CustomerRoomService } from '../../../../../../main/webapp/app/entities/customer-room/customer-room.service';
import { CustomerRoom } from '../../../../../../main/webapp/app/entities/customer-room/customer-room.model';

describe('Component Tests', () => {

    describe('CustomerRoom Management Detail Component', () => {
        let comp: CustomerRoomDetailComponent;
        let fixture: ComponentFixture<CustomerRoomDetailComponent>;
        let service: CustomerRoomService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [CustomerRoomDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CustomerRoomService,
                    EventManager
                ]
            }).overrideComponent(CustomerRoomDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerRoomDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerRoomService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CustomerRoom(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.customerRoom).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
