import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TypeDetailComponent } from '../../../../../../main/webapp/app/entities/type/type-detail.component';
import { TypeService } from '../../../../../../main/webapp/app/entities/type/type.service';
import { Type } from '../../../../../../main/webapp/app/entities/type/type.model';

describe('Component Tests', () => {

    describe('Type Management Detail Component', () => {
        let comp: TypeDetailComponent;
        let fixture: ComponentFixture<TypeDetailComponent>;
        let service: TypeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [TypeDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TypeService,
                    EventManager
                ]
            }).overrideComponent(TypeDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TypeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Type(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.type).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
