import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AvatarDetailComponent } from '../../../../../../main/webapp/app/entities/avatar/avatar-detail.component';
import { AvatarService } from '../../../../../../main/webapp/app/entities/avatar/avatar.service';
import { Avatar } from '../../../../../../main/webapp/app/entities/avatar/avatar.model';

describe('Component Tests', () => {

    describe('Avatar Management Detail Component', () => {
        let comp: AvatarDetailComponent;
        let fixture: ComponentFixture<AvatarDetailComponent>;
        let service: AvatarService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [AvatarDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AvatarService,
                    EventManager
                ]
            }).overrideComponent(AvatarDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AvatarDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AvatarService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Avatar(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.avatar).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
