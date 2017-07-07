import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { HistoryOrderDetailComponent } from '../../../../../../main/webapp/app/entities/history-order/history-order-detail.component';
import { HistoryOrderService } from '../../../../../../main/webapp/app/entities/history-order/history-order.service';
import { HistoryOrder } from '../../../../../../main/webapp/app/entities/history-order/history-order.model';

describe('Component Tests', () => {

    describe('HistoryOrder Management Detail Component', () => {
        let comp: HistoryOrderDetailComponent;
        let fixture: ComponentFixture<HistoryOrderDetailComponent>;
        let service: HistoryOrderService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [HistoryOrderDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    HistoryOrderService,
                    EventManager
                ]
            }).overrideComponent(HistoryOrderDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(HistoryOrderDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HistoryOrderService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new HistoryOrder(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.historyOrder).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
