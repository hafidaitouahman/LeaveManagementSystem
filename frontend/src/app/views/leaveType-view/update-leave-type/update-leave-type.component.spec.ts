import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLeaveTypeComponent } from './update-leave-type.component';

describe('UpdateLeaveTypeComponent', () => {
  let component: UpdateLeaveTypeComponent;
  let fixture: ComponentFixture<UpdateLeaveTypeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateLeaveTypeComponent]
    });
    fixture = TestBed.createComponent(UpdateLeaveTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
