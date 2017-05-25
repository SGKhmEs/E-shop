import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CustomerDetailComponent } from '../../../../../../main/webapp/app/entities/customer/customer-detail.component';
import { CustomerService } from '../../../../../../main/webapp/app/entities/customer/customer.service';
import { Customer } from '../../../../../../main/webapp/app/entities/customer/customer.model';

describe('Component Tests', () => {

    describe('Customer Management Detail Component', () => {
        let comp: CustomerDetailComponent;
        let fixture: ComponentFixture<CustomerDetailComponent>;
        let service: CustomerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [CustomerDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CustomerService,
                    EventManager
                ]
            }).overrideComponent(CustomerDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CustomerDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CustomerService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Customer(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.customer).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
