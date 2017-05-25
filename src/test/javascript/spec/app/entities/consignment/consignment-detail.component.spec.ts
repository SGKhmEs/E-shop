import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ConsignmentDetailComponent } from '../../../../../../main/webapp/app/entities/consignment/consignment-detail.component';
import { ConsignmentService } from '../../../../../../main/webapp/app/entities/consignment/consignment.service';
import { Consignment } from '../../../../../../main/webapp/app/entities/consignment/consignment.model';

describe('Component Tests', () => {

    describe('Consignment Management Detail Component', () => {
        let comp: ConsignmentDetailComponent;
        let fixture: ComponentFixture<ConsignmentDetailComponent>;
        let service: ConsignmentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ConsignmentDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ConsignmentService,
                    EventManager
                ]
            }).overrideComponent(ConsignmentDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConsignmentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConsignmentService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Consignment(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.consignment).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
