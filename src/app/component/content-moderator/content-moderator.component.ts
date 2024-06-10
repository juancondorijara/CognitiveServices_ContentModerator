import { Component, OnInit } from '@angular/core';

import { ContentModeratorModel } from 'src/app/model/content-moderator.model';
import { ContentModeratorService } from 'src/app/service/content-moderator/content-moderator.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-content-moderator',
  templateUrl: './content-moderator.component.html',
  styleUrls: ['./content-moderator.component.css']
})
export class ContentModeratorComponent implements OnInit {

  constructor(
    private contentModeratorService: ContentModeratorService
  ) { }

  contentModerator_type_list: number | undefined;
  active_contentModerator_list: boolean = true;

  contentModerators: ContentModeratorModel [] = [];
  public contentModeratorForm: FormGroup = new FormGroup({});
  contentModerator: ContentModeratorModel = new ContentModeratorModel();

  ngOnInit(): void {
    this.contentModerator_type_list=1;
    this.listContentModerator();
  }

  listAll(): void {
    this.contentModeratorService.listAll().subscribe(
      (rest: any) => {
        this.contentModerators = rest;
        console.log("Mostrando a todas las consultas: ", rest);
      }
    );
  }

  listByActive(): void {
    this.contentModeratorService.listByActive(this.active_contentModerator_list).subscribe((rest: any) => {
      this.contentModerators = rest;
      console.log(rest);
    },
      error => {
        console.log(error)
      }
    );
  }

  listContentModerator(): void {
    if(this.contentModerator_type_list == 1){
      this.active_contentModerator_list = true;
      this.listByActive();
    }
    if(this.contentModerator_type_list == 2){
      this.active_contentModerator_list = false;
      this.listByActive();
    }
    if(this.contentModerator_type_list == 3){
      this.listAll();
    }
  }


  save() {
    Swal.fire({
      title: '¿Deseas Consultar y Guardar?',
      showDenyButton: true,
      confirmButtonText: 'Si',
      denyButtonText: 'No',
      icon: 'info',
    }).then((result) => {
      if (result.isConfirmed) {
        //this.contentModerator = { ...this.contentModeratorForm.value };
        this.contentModerator.url = "";
        this.contentModeratorService.save(this.contentModerator).subscribe(
          resp => {
            //this.clean();
            console.log(resp);
          },
          error => {
            console.log(error);
          }
        );
      }
    })
  }

  clean(): void {
    this.contentModerator.url = '';
    console.log("Limpiando");
  }

  delete(id: number | undefined) {
    Swal.fire({
      title: '¿Deseas Eliminar?',
      showDenyButton: true,
      confirmButtonText: 'Si',
      denyButtonText: 'No',
      icon: 'error',
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire('Eliminando...', '', 'success')
        this.contentModeratorService.delete(id).subscribe(data => {
          console.log(data);
          this.listContentModerator();
        }
        );
      }
    })
  }

  restore(id: number | undefined) {
    Swal.fire({
      title: '¿Deseas Restaurar?',
      showDenyButton: true,
      confirmButtonText: 'Si',
      denyButtonText: 'No',
      icon: 'warning',
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire('Restaurando...', '', 'success')
        this.contentModeratorService.restore(id).subscribe(data => {
          console.log(data);
          this.listContentModerator();
        }
        );
      }
    })
  }

}
