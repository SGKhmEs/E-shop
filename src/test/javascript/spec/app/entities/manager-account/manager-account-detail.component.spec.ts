/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ManagerAccountDetailComponent } from '../../../../../../main/webapp/app/entities/manager-account/manager-account-detail.component';
import { ManagerAccountService } from '../../../../../../main/webapp/app/entities/manager-account/manager-account.service';
import { ManagerAccount } from '../../../../../../main/webapp/app/entities/manager-account/manager-account.model';

describe('Component Tests', () => {

    describe('ManagerAccount Management Detail Component', () => {
        let comp: ManagerAccountDetailComponent;
        let fixture: ComponentFixture<ManagerAccountDetailComponent>;
        let service: ManagerAccountService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ManagerAccountDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ManagerAccountService,
                    JhiEventManager
                ]
            }).overrideTemplate(ManagerAccountDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ManagerAccountDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ManagerAccountService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ManagerAccount(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.managerAccount).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
