import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { ContentModeratorModel } from 'src/app/model/content-moderator.model';

@Injectable({
  providedIn: 'root'
})
export class ContentModeratorService {

  constructor(private http: HttpClient) {}

  private url: string = environment.contentModerator_be;
  contentModeratorSelected: ContentModeratorModel | undefined;

  listAll() {
    return this.http.get(this.url);
  }

  listById(id: number | undefined) {
    return this.http.get(this.url + '/id/' + id);
  }

  listByActive(active: boolean) {
    return this.http.get(this.url + '/active/' + active);
  }

  save(contentModerator: ContentModeratorModel) {
    return this.http.post(this.url + '/save', contentModerator);
  }

  enviar(url: string) {
    const contentModeratorSelected = {
      url: url,
        // No proporcionamos valores para las otras propiedades
    };
    return this.http.post(this.url + '/save', contentModeratorSelected);
  }

  delete(id: number | undefined) {
    return this.http.post(this.url + '/delete/'+ id,'');
  }

  restore(id: number | undefined) {
    return this.http.post(this.url + '/restore/'+ id,'');
  }

}
