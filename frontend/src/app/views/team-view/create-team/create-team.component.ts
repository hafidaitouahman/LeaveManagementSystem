import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TeamService } from '../team.service';
import { Team } from 'src/app/shared/models/team.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-create-team',
  templateUrl: './create-team.component.html',
  styleUrls: ['./create-team.component.css']
})
export class CreateTeamComponent {
  team: Team = new Team();
  submitted = false;

  constructor(private teamService: TeamService,
    private router: Router,public modal: NgbActiveModal) { }

  ngOnInit() {
  }

  newTeam(): void {
    this.submitted = false;
    this.team = new Team();
  }

  save() {
    this.teamService
    .createTeam(this.team).subscribe(data => {
      console.log(data)
      this.team = new Team();
      this.modal.close(this.team); // Ferme la boîte de dialogue
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
   

  }
 
  gotoList() {
    this.router.navigate(['/team']);
  }
}
