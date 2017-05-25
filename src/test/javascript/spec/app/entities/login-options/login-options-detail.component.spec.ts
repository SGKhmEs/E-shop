import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { LoginOptionsDetailComponent } from '../../../../../../main/webapp/app/entities/login-options/login-options-detail.component';
import { LoginOptionsService } from '../../../../../../main/webapp/app/entities/login-options/login-options.service';
import { LoginOptions } from '../../../../../../main/webapp/app/entities/login-options/login-options.model';

describe('Component Tests', () => {

    describe('LoginOptions Management Detail Component', () => {
        let comp: LoginOptionsDetailComponent;
        let fixture: ComponentFixture<LoginOptionsDetailComponent>;
        let service: LoginOptionsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [LoginOptionsDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    LoginOptionsService,
                    EventManager
                ]
            }).overrideComponent(LoginOptionsDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LoginOptionsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LoginOptionsService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new LoginOptions(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.loginOptions).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
