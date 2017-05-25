import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProductsDetailComponent } from '../../../../../../main/webapp/app/entities/products/products-detail.component';
import { ProductsService } from '../../../../../../main/webapp/app/entities/products/products.service';
import { Products } from '../../../../../../main/webapp/app/entities/products/products.model';

describe('Component Tests', () => {

    describe('Products Management Detail Component', () => {
        let comp: ProductsDetailComponent;
        let fixture: ComponentFixture<ProductsDetailComponent>;
        let service: ProductsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ProductsDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProductsService,
                    EventManager
                ]
            }).overrideComponent(ProductsDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProductsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductsService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Products(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.products).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
