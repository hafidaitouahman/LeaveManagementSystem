import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Quota } from 'src/app/shared/models/quota.module';
import { QuotaService } from '../quota.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-quota',
  templateUrl: './update-quota.component.html',
  styleUrls: ['./update-quota.component.css']
})
export class UpdateQuotaComponent {

  id!: number;
  quota: Quota = new Quota();

  constructor(private route: ActivatedRoute,private router: Router,
    private leaveTypeService: QuotaService,public modal: NgbActiveModal) { }

  ngOnInit() {
    if (this.id) {
      this.leaveTypeService.getLeaveQuotaById(this.id)
        .subscribe(data => {
          this.quota = data;
        });
    }
  }

  

  updateQuota() {
    this.leaveTypeService.updateLeaveQuota(this.id, this.quota)
      .subscribe(data => {
        console.log(data);
        this.quota = new Quota();
        this.modal.close(this.quota); // Ferme la boîte de dialogue
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateQuota();    
  }

  gotoList() {
    this.router.navigate(['/leavequota']);
  }
}
