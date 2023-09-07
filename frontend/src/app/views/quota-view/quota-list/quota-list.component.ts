import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
import { CreateQuotaComponent } from '../create-quota/create-quota.component';
import { UpdateQuotaComponent } from '../update-quota/update-quota.component';
import { Quota } from 'src/app/shared/models/quota.module';
import { QuotaService } from '../quota.service';
@Component({
  selector: 'app-quota-list',
  templateUrl: './quota-list.component.html',
  styleUrls: ['./quota-list.component.css']
})
export class QuotaListComponent {
  quotas!: Observable<Quota[]>;
  selectedQuotaId: number | null = null; // To track the selected quota ID.
  quotaUsernames: string[] = []; // To store the usernames for the selected quota.

  constructor(private quotaService: QuotaService,
    private router: Router,private modal: NgbModal) {}

  ngOnInit() {
    this.reloadData();
  }
  

  reloadData() {
    this.quotas = this.quotaService.getQuotasList();
  }

  deleteQuota(id: number) {
    this.quotaService.deleteQuota(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  getQuota(id:number){
    this.quotaService.getQuota(id)
    .subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));

  }

  openCreateEventModal(): void {
    const modalRef = this.modal.open(CreateQuotaComponent);
    modalRef.closed.subscribe(result => {
      this.reloadData(); // Recharge les données après la fermeture du popup
    });

  }
  openUpdateEventModal(id: number): void {
    
    const modalRef = this.modal.open(UpdateQuotaComponent);
    modalRef.componentInstance.id = id; // Pass the id to the modal component
  
    modalRef.closed.subscribe(result => {
      this.reloadData();
    });


  }


  showUsernames(quotaId: number): void {
    if (this.selectedQuotaId === quotaId) {
      // If the same button is clicked again, hide the usernames.
      this.selectedQuotaId = null;
      this.quotaUsernames = [];
    } else {
      // Fetch and display usernames for the selected quota.
      this.quotaService.getQuota(quotaId).subscribe(usernames => {
        this.selectedQuotaId = quotaId;
        this.quotaUsernames = usernames;
      });
    }
  }
}
