import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuotaListComponent } from './quota-list.component';

describe('QuotaListComponent', () => {
  let component: QuotaListComponent;
  let fixture: ComponentFixture<QuotaListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [QuotaListComponent]
    });
    fixture = TestBed.createComponent(QuotaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
