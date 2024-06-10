import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from 'src/app/app.component';
import { ContentModeratorComponent } from 'src/app/component/content-moderator/content-moderator.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'content-moderator'
  },
  {
    path: 'content-moderator',
    component: ContentModeratorComponent
  },
  {
    path: 'app',
    component: AppComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
