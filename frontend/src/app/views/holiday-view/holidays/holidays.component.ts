import { Component, OnInit } from '@angular/core';
import { HolidaysService } from '../holidays.service';

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styleUrls: ['./holidays.component.css']
})
export class HolidaysComponent implements OnInit {
  holidays: any[] = [];

  constructor(private holidaysService: HolidaysService) {}

  ngOnInit(): void {
    this.holidaysService.getHolidays().subscribe(data => {
      this.holidays = data;
    });
  }
}
