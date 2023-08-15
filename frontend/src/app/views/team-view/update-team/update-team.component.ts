import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TeamService } from '../team.service';
import { Team } from 'src/app/shared/models/team.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-update-team',
  templateUrl: './update-team.component.html',
  styleUrls: ['./update-team.component.css']
})
export class UpdateTeamComponent {
  id!: number;
  team: Team = new Team();

  constructor(private route: ActivatedRoute,private router: Router,
    private teamService: TeamService,public modal: NgbActiveModal) { }

  ngOnInit() {
    if (this.id) {
      this.teamService.getTeam(this.id)
        .subscribe(data => {
          this.team = data;
        });
    }
  }

  

  updateTeam() {
    this.teamService.updateTeam(this.id, this.team)
      .subscribe(data => {
        console.log(data);
        this.team = new Team();
        this.modal.close(this.team); // Ferme la boîte de dialogue
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateTeam();    
  }

  gotoList() {
    this.router.navigate(['/team']);
  }
}
