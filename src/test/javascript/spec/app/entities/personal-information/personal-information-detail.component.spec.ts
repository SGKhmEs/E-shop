import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { PersonalInformationDetailComponent } from '../../../../../../main/webapp/app/entities/personal-information/personal-information-detail.component';
import { PersonalInformationService } from '../../../../../../main/webapp/app/entities/personal-information/personal-information.service';
import { PersonalInformation } from '../../../../../../main/webapp/app/entities/personal-information/personal-information.model';

describe('Component Tests', () => {

    describe('PersonalInformation Management Detail Component', () => {
        let comp: PersonalInformationDetailComponent;
        let fixture: ComponentFixture<PersonalInformationDetailComponent>;
        let service: PersonalInformationService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [PersonalInformationDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    PersonalInformationService,
                    EventManager
                ]
            }).overrideComponent(PersonalInformationDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PersonalInformationDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PersonalInformationService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new PersonalInformation(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.personalInformation).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
