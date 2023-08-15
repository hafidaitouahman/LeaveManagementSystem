import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router } from '@angular/router';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
import { CreateSiteComponent } from '../create-site/create-site.component';
import { UpdateSiteComponent } from '../update-site/update-site.component';
import { Site } from 'src/app/shared/models/site.module';
import { SiteService } from '../site.service';
@Component({
  selector: 'app-site-list',
  templateUrl: './site-list.component.html',
  styleUrls: ['./site-list.component.css']
})
export class SiteListComponent {
  sites!: Observable<Site[]>;
  constructor(private siteService: SiteService,
    private router: Router,private modal: NgbModal) {}

  ngOnInit() {
    this.reloadData();
  }
  

  reloadData() {
    this.sites = this.siteService.getSitesList();
  }

  deleteSite(id: number) {
    this.siteService.deleteSite(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  openCreateEventModal(): void {
    const modalRef = this.modal.open(CreateSiteComponent);
    modalRef.closed.subscribe(result => {
      this.reloadData(); // Recharge les données après la fermeture du popup
    });

  }
  openUpdateEventModal(id: number): void {
    
    const modalRef = this.modal.open(UpdateSiteComponent);
    modalRef.componentInstance.id = id; // Pass the id to the modal component
  
    modalRef.closed.subscribe(result => {
      this.reloadData();
    });


  }
}
