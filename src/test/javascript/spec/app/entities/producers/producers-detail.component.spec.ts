import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProducersDetailComponent } from '../../../../../../main/webapp/app/entities/producers/producers-detail.component';
import { ProducersService } from '../../../../../../main/webapp/app/entities/producers/producers.service';
import { Producers } from '../../../../../../main/webapp/app/entities/producers/producers.model';

describe('Component Tests', () => {

    describe('Producers Management Detail Component', () => {
        let comp: ProducersDetailComponent;
        let fixture: ComponentFixture<ProducersDetailComponent>;
        let service: ProducersService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ProducersDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProducersService,
                    EventManager
                ]
            }).overrideComponent(ProducersDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProducersDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProducersService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Producers(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.producers).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
