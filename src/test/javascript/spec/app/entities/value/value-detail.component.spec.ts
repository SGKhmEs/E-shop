import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ValueDetailComponent } from '../../../../../../main/webapp/app/entities/value/value-detail.component';
import { ValueService } from '../../../../../../main/webapp/app/entities/value/value.service';
import { Value } from '../../../../../../main/webapp/app/entities/value/value.model';

describe('Component Tests', () => {

    describe('Value Management Detail Component', () => {
        let comp: ValueDetailComponent;
        let fixture: ComponentFixture<ValueDetailComponent>;
        let service: ValueService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [ValueDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ValueService,
                    EventManager
                ]
            }).overrideComponent(ValueDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ValueDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ValueService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Value(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.value).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
