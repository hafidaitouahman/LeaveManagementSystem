import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaverequestListComponent } from './leaverequest-list.component';

describe('LeaverequestListComponent', () => {
  let component: LeaverequestListComponent;
  let fixture: ComponentFixture<LeaverequestListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LeaverequestListComponent]
    });
    fixture = TestBed.createComponent(LeaverequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
