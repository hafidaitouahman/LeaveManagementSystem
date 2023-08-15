import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartementService } from '../departement.service';
import { Departement } from 'src/app/shared/models/departement.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-update-departement',
  templateUrl: './update-departement.component.html',
  styleUrls: ['./update-departement.component.css']
})
export class UpdateDepartementComponent {
  id!: number;
  departement: Departement = new Departement();

  constructor(private route: ActivatedRoute,private router: Router,
    private departementService: DepartementService,public modal: NgbActiveModal) { }

  ngOnInit() {
    if (this.id) {
      this.departementService.getDepartement(this.id)
        .subscribe(data => {
          this.departement = data;
        });
    }
  }

  

  updateDepartement() {
    this.departementService.updateDepartement(this.id, this.departement)
      .subscribe(data => {
        console.log(data);
        this.departement = new Departement();
        this.modal.close(this.departement); // Ferme la boîte de dialogue
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateDepartement();    
  }

  gotoList() {
    this.router.navigate(['/departement']);
  }
}
