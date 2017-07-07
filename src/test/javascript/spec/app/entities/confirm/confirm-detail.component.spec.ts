import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ConfirmDetailComponent } from '../../../../../../main/webapp/app/entities/confirm/confirm-detail.component';
import { ConfirmService } from '../../../../../../main/webapp/app/entities/confirm/confirm.service';
import { Confirm } from '../../../../../../main/webapp/app/entities/confirm/confirm.model';

describe('Component Tests', () => {

    describe('Confirm Management Detail Component', () => {
        let comp: ConfirmDetailComponent;
        let fixture: ComponentFixture<ConfirmDetailComponent>;
        let service: ConfirmService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ConfirmDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ConfirmService,
                    EventManager
                ]
            }).overrideComponent(ConfirmDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfirmDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfirmService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Confirm(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.confirm).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
