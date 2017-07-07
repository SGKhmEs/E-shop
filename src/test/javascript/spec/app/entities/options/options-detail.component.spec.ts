import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { OptionsDetailComponent } from '../../../../../../main/webapp/app/entities/options/options-detail.component';
import { OptionsService } from '../../../../../../main/webapp/app/entities/options/options.service';
import { Options } from '../../../../../../main/webapp/app/entities/options/options.model';

describe('Component Tests', () => {

    describe('Options Management Detail Component', () => {
        let comp: OptionsDetailComponent;
        let fixture: ComponentFixture<OptionsDetailComponent>;
        let service: OptionsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [OptionsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    OptionsService,
                    JhiEventManager
                ]
<<<<<<< HEAD
            }).overrideTemplate(OptionsDetailComponent, '')
            .compileComponents();
=======
            }).overrideComponent(OptionsDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
>>>>>>> with_entities
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(OptionsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OptionsService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Options(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.options).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
