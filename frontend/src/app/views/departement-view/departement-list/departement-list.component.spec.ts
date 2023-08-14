import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartementListComponent } from './departement-list.component';

describe('DepartementListComponent', () => {
  let component: DepartementListComponent;
  let fixture: ComponentFixture<DepartementListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartementListComponent]
    });
    fixture = TestBed.createComponent(DepartementListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
