import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentModeratorComponent } from './content-moderator.component';

describe('ContentModeratorComponent', () => {
  let component: ContentModeratorComponent;
  let fixture: ComponentFixture<ContentModeratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContentModeratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContentModeratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
