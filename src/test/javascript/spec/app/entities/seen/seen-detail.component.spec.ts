import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { SeenDetailComponent } from '../../../../../../main/webapp/app/entities/seen/seen-detail.component';
import { SeenService } from '../../../../../../main/webapp/app/entities/seen/seen.service';
import { Seen } from '../../../../../../main/webapp/app/entities/seen/seen.model';

describe('Component Tests', () => {

    describe('Seen Management Detail Component', () => {
        let comp: SeenDetailComponent;
        let fixture: ComponentFixture<SeenDetailComponent>;
        let service: SeenService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [SeenDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    SeenService,
                    EventManager
                ]
            }).overrideComponent(SeenDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SeenDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeenService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Seen(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.seen).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
