import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DepartementService } from '../departement.service';
import { Departement } from 'src/app/shared/models/departement.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-create-departement',
  templateUrl: './create-departement.component.html',
  styleUrls: ['./create-departement.component.css']
})
export class CreateDepartementComponent {
  departement: Departement = new Departement();
  submitted = false;

  constructor(private departementService: DepartementService,
    private router: Router,public modal: NgbActiveModal) { }

  ngOnInit() {
  }

  newDepartement(): void {
    this.submitted = false;
    this.departement = new Departement();
  }

  save() {
    this.departementService
    .createDepartement(this.departement).subscribe(data => {
      console.log(data)
      this.departement = new Departement();
      this.modal.close(this.departement); // Ferme la boîte de dialogue
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
   

  }
 
  gotoList() {
    this.router.navigate(['/departement']);
  }
}
