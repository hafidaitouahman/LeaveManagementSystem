import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SiteService } from '../site.service';
import { Site } from 'src/app/shared/models/site.module';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-create-site',
  templateUrl: './create-site.component.html',
  styleUrls: ['./create-site.component.css']
})
export class CreateSiteComponent {
  site: Site = new Site();
  submitted = false;

  constructor(private siteService: SiteService,
    private router: Router,public modal: NgbActiveModal) { }

  ngOnInit() {
  }

  newSite(): void {
    this.submitted = false;
    this.site = new Site();
  }

  save() {
    this.siteService
    .createSite(this.site).subscribe(data => {
      console.log(data)
      this.site = new Site();
      this.modal.close(this.site); // Ferme la boîte de dialogue
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
   

  }
 
  gotoList() {
    this.router.navigate(['/site']);
  }
}
