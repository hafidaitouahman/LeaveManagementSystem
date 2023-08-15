import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SiteService } from '../site.service';
import { Site } from 'src/app/shared/models/site.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-update-site',
  templateUrl: './update-site.component.html',
  styleUrls: ['./update-site.component.css']
})
export class UpdateSiteComponent {
  id!: number;
  site: Site = new Site();

  constructor(private route: ActivatedRoute,private router: Router,
    private siteService: SiteService,public modal: NgbActiveModal) { }

  ngOnInit() {
    if (this.id) {
      this.siteService.getSite(this.id)
        .subscribe(data => {
          this.site = data;
        });
    }
  }

  

  updateSite() {
    this.siteService.updateSite(this.id, this.site)
      .subscribe(data => {
        console.log(data);
        this.site = new Site();
        this.modal.close(this.site); // Ferme la boîte de dialogue
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateSite();    
  }

  gotoList() {
    this.router.navigate(['/site']);
  }
}
