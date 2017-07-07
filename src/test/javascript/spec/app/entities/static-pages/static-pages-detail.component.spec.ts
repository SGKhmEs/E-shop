import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { StaticPagesDetailComponent } from '../../../../../../main/webapp/app/entities/static-pages/static-pages-detail.component';
import { StaticPagesService } from '../../../../../../main/webapp/app/entities/static-pages/static-pages.service';
import { StaticPages } from '../../../../../../main/webapp/app/entities/static-pages/static-pages.model';

describe('Component Tests', () => {

    describe('StaticPages Management Detail Component', () => {
        let comp: StaticPagesDetailComponent;
        let fixture: ComponentFixture<StaticPagesDetailComponent>;
        let service: StaticPagesService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [StaticPagesDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    StaticPagesService,
                    JhiEventManager
                ]
            }).overrideTemplate(StaticPagesDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StaticPagesDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StaticPagesService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new StaticPages(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.staticPages).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
