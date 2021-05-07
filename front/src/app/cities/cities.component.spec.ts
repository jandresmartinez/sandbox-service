import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { CitiesComponent } from './cities.component';

describe('CitiesComponent', () => {
  let component: CitiesComponent;
  let fixture: ComponentFixture<CitiesComponent>;
  

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      declarations: [ CitiesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
