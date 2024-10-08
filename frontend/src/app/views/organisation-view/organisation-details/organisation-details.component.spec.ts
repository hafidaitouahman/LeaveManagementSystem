import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrganisationDetailsComponent } from './organisation-details.component';

describe('OrganisationDetailsComponent', () => {
  let component: OrganisationDetailsComponent;
  let fixture: ComponentFixture<OrganisationDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrganisationDetailsComponent]
    });
    fixture = TestBed.createComponent(OrganisationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
