import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProductInBucketDetailComponent } from '../../../../../../main/webapp/app/entities/product-in-bucket/product-in-bucket-detail.component';
import { ProductInBucketService } from '../../../../../../main/webapp/app/entities/product-in-bucket/product-in-bucket.service';
import { ProductInBucket } from '../../../../../../main/webapp/app/entities/product-in-bucket/product-in-bucket.model';

describe('Component Tests', () => {

    describe('ProductInBucket Management Detail Component', () => {
        let comp: ProductInBucketDetailComponent;
        let fixture: ComponentFixture<ProductInBucketDetailComponent>;
        let service: ProductInBucketService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ProductInBucketDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProductInBucketService,
                    JhiEventManager
                ]
            }).overrideTemplate(ProductInBucketDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProductInBucketDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductInBucketService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ProductInBucket(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.productInBucket).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
