import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { EshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { StorageDetailComponent } from '../../../../../../main/webapp/app/entities/storage/storage-detail.component';
import { StorageService } from '../../../../../../main/webapp/app/entities/storage/storage.service';
import { Storage } from '../../../../../../main/webapp/app/entities/storage/storage.model';

describe('Component Tests', () => {

    describe('Storage Management Detail Component', () => {
        let comp: StorageDetailComponent;
        let fixture: ComponentFixture<StorageDetailComponent>;
        let service: StorageService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [EshopTestModule],
                declarations: [StorageDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    StorageService,
                    EventManager
                ]
            }).overrideComponent(StorageDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StorageDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StorageService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Storage(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.storage).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
