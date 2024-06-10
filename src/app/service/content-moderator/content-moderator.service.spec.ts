import { TestBed } from '@angular/core/testing';

import { ContentModeratorService } from './content-moderator.service';

describe('ContentModeratorService', () => {
  let service: ContentModeratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContentModeratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
