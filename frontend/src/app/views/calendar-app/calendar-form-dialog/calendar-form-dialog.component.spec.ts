import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalendarFormDialogComponent } from './calendar-form-dialog.component';

describe('CalendarFormDialogComponent', () => {
  let component: CalendarFormDialogComponent;
  let fixture: ComponentFixture<CalendarFormDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CalendarFormDialogComponent]
    });
    fixture = TestBed.createComponent(CalendarFormDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
