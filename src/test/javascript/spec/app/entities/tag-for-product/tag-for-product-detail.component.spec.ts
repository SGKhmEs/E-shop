import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TagForProductDetailComponent } from '../../../../../../main/webapp/app/entities/tag-for-product/tag-for-product-detail.component';
import { TagForProductService } from '../../../../../../main/webapp/app/entities/tag-for-product/tag-for-product.service';
import { TagForProduct } from '../../../../../../main/webapp/app/entities/tag-for-product/tag-for-product.model';

describe('Component Tests', () => {

    describe('TagForProduct Management Detail Component', () => {
        let comp: TagForProductDetailComponent;
        let fixture: ComponentFixture<TagForProductDetailComponent>;
        let service: TagForProductService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [TagForProductDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TagForProductService,
                    EventManager
                ]
            }).overrideTemplate(TagForProductDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TagForProductDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TagForProductService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TagForProduct(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.tagForProduct).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
