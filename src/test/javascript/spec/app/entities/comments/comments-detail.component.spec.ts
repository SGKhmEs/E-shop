import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { CommentsDetailComponent } from '../../../../../../main/webapp/app/entities/comments/comments-detail.component';
import { CommentsService } from '../../../../../../main/webapp/app/entities/comments/comments.service';
import { Comments } from '../../../../../../main/webapp/app/entities/comments/comments.model';

describe('Component Tests', () => {

    describe('Comments Management Detail Component', () => {
        let comp: CommentsDetailComponent;
        let fixture: ComponentFixture<CommentsDetailComponent>;
        let service: CommentsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [CommentsDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    CommentsService,
                    EventManager
                ]
            }).overrideComponent(CommentsDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CommentsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CommentsService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Comments(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.comments).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
