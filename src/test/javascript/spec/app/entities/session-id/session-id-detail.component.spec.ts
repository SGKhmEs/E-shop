import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SessionIdDetailComponent } from '../../../../../../main/webapp/app/entities/session-id/session-id-detail.component';
import { SessionIdService } from '../../../../../../main/webapp/app/entities/session-id/session-id.service';
import { SessionId } from '../../../../../../main/webapp/app/entities/session-id/session-id.model';

describe('Component Tests', () => {

    describe('SessionId Management Detail Component', () => {
        let comp: SessionIdDetailComponent;
        let fixture: ComponentFixture<SessionIdDetailComponent>;
        let service: SessionIdService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [SessionIdDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SessionIdService,
                    EventManager
                ]
            }).overrideComponent(SessionIdDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SessionIdDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SessionIdService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new SessionId(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.sessionId).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
