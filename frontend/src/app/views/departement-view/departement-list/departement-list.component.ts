import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
import { CreateDepartementComponent } from '../create-departement/create-departement.component';
import { UpdateDepartementComponent } from '../update-departement/update-departement.component';
import { Departement } from 'src/app/shared/models/departement.module';
import { DepartementService } from '../departement.service';
@Component({
  selector: 'app-departement-list',
  templateUrl: './departement-list.component.html',
  styleUrls: ['./departement-list.component.css']
})
export class DepartementListComponent {
  departements!: Observable<Departement[]>;
  constructor(private departementService: DepartementService,
    private router: Router,private modal: NgbModal) {}

  ngOnInit() {
    this.reloadData();
  }
  

  reloadData() {
    this.departements = this.departementService.getDepartementsList();
  }

  deleteDepartement(id: number) {
    this.departementService.deleteDepartement(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  openCreateEventModal(): void {
    const modalRef = this.modal.open(CreateDepartementComponent);
    modalRef.closed.subscribe(result => {
      this.reloadData(); // Recharge les données après la fermeture du popup
    });

  }
  openUpdateEventModal(id: number): void {
    
    const modalRef = this.modal.open(UpdateDepartementComponent);
    modalRef.componentInstance.id = id; // Pass the id to the modal component
  
    modalRef.closed.subscribe(result => {
      this.reloadData();
    });


  }
}
