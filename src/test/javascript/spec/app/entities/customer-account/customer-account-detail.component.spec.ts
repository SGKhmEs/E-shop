import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CustomerAccountDetailComponent } from '../../../../../../main/webapp/app/entities/customer-account/customer-account-detail.component';
import { CustomerAccountService } from '../../../../../../main/webapp/app/entities/customer-account/customer-account.service';
import { CustomerAccount } from '../../../../../../main/webapp/app/entities/customer-account/customer-account.model';

describe('Component Tests', () => {

    describe('CustomerAccount Management Detail Component', () => {
        let comp: CustomerAccountDetailComponent;
        let fixture: ComponentFixture<CustomerAccountDetailComponent>;
        let service: CustomerAccountService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [CustomerAccountDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CustomerAccountService,
                    JhiEventManager
                ]
            }).overrideTemplate(CustomerAccountDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerAccountDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerAccountService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new CustomerAccount(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.customerAccount).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
