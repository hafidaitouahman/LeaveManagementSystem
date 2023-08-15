import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
import { CreateTeamComponent } from '../create-team/create-team.component';
import { UpdateTeamComponent } from '../update-team/update-team.component';
import { Team } from 'src/app/shared/models/team.module';
import { TeamService } from '../team.service';
@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent {
  teams!: Observable<Team[]>;
  constructor(private teamService: TeamService,
    private router: Router,private modal: NgbModal) {}

  ngOnInit() {
    this.reloadData();
  }
  

  reloadData() {
    this.teams = this.teamService.getTeamsList();
  }

  deleteTeam(id: number) {
    this.teamService.deleteTeam(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  openCreateEventModal(): void {
    const modalRef = this.modal.open(CreateTeamComponent);
    modalRef.closed.subscribe(result => {
      this.reloadData(); // Recharge les données après la fermeture du popup
    });

  }
  openUpdateEventModal(id: number): void {
    
    const modalRef = this.modal.open(UpdateTeamComponent);
    modalRef.componentInstance.id = id; // Pass the id to the modal component
  
    modalRef.closed.subscribe(result => {
      this.reloadData();
    });


  }
}
