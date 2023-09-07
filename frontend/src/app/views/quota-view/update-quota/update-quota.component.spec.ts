import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateQuotaComponent } from './update-quota.component';

describe('UpdateQuotaComponent', () => {
  let component: UpdateQuotaComponent;
  let fixture: ComponentFixture<UpdateQuotaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateQuotaComponent]
    });
    fixture = TestBed.createComponent(UpdateQuotaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
