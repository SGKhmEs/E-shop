import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { WishListDetailComponent } from '../../../../../../main/webapp/app/entities/wish-list/wish-list-detail.component';
import { WishListService } from '../../../../../../main/webapp/app/entities/wish-list/wish-list.service';
import { WishList } from '../../../../../../main/webapp/app/entities/wish-list/wish-list.model';

describe('Component Tests', () => {

    describe('WishList Management Detail Component', () => {
        let comp: WishListDetailComponent;
        let fixture: ComponentFixture<WishListDetailComponent>;
        let service: WishListService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [WishListDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    WishListService,
                    EventManager
                ]
            }).overrideComponent(WishListDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(WishListDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WishListService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new WishList(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.wishList).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
