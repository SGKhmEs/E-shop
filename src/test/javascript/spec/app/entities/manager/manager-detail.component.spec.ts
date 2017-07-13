/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ManagerDetailComponent } from '../../../../../../main/webapp/app/entities/manager/manager-detail.component';
import { ManagerService } from '../../../../../../main/webapp/app/entities/manager/manager.service';
import { Manager } from '../../../../../../main/webapp/app/entities/manager/manager.model';

describe('Component Tests', () => {

    describe('Manager Management Detail Component', () => {
        let comp: ManagerDetailComponent;
        let fixture: ComponentFixture<ManagerDetailComponent>;
        let service: ManagerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ManagerDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ManagerService,
                    JhiEventManager
                ]
            }).overrideTemplate(ManagerDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ManagerDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ManagerService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Manager(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.manager).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
