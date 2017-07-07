import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { BucketDetailComponent } from '../../../../../../main/webapp/app/entities/bucket/bucket-detail.component';
import { BucketService } from '../../../../../../main/webapp/app/entities/bucket/bucket.service';
import { Bucket } from '../../../../../../main/webapp/app/entities/bucket/bucket.model';

describe('Component Tests', () => {

    describe('Bucket Management Detail Component', () => {
        let comp: BucketDetailComponent;
        let fixture: ComponentFixture<BucketDetailComponent>;
        let service: BucketService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [BucketDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    BucketService,
                    JhiEventManager
                ]
<<<<<<< HEAD
            }).overrideTemplate(BucketDetailComponent, '')
            .compileComponents();
=======
            }).overrideComponent(BucketDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
>>>>>>> with_entities
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(BucketDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BucketService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Bucket(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.bucket).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
