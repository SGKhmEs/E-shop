import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TagsDetailComponent } from '../../../../../../main/webapp/app/entities/tags/tags-detail.component';
import { TagsService } from '../../../../../../main/webapp/app/entities/tags/tags.service';
import { Tags } from '../../../../../../main/webapp/app/entities/tags/tags.model';

describe('Component Tests', () => {

    describe('Tags Management Detail Component', () => {
        let comp: TagsDetailComponent;
        let fixture: ComponentFixture<TagsDetailComponent>;
        let service: TagsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [TagsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TagsService,
                    JhiEventManager
                ]
<<<<<<< HEAD
            }).overrideTemplate(TagsDetailComponent, '')
            .compileComponents();
=======
            }).overrideComponent(TagsDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
>>>>>>> with_entities
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TagsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TagsService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Tags(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.tags).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
