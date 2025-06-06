import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Holiday } from '../../models/holiday.model';
import { DateAdapter } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatSpinner, MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-calendar',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatIconModule
  ],
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent implements OnInit {
  selectedYear = new Date().getFullYear();
  years: number[] = [];
  holidays: Holiday[] = [];
  loading = false;

  displayedColumns: string[] = ['fecha', 'nombre', 'dia'];

  constructor(
    private api: ApiService,
    private dateAdapter: DateAdapter<Date>
  ) {
    this.dateAdapter.setLocale('es-CO');
  }

  ngOnInit(): void {
    this.years = Array.from({ length: 31 }, (_, i) => 2000 + i);
    this.loadHolidays();
  }

  loadHolidays(): void {
    this.loading = true;
    this.holidays = [];
    this.api.getHolidays(this.selectedYear).subscribe({
      next: (res) => {
        this.holidays = res.festivos;
        this.loading = false;
      },
      error: () => {
        this.holidays = [];
        this.loading = false;
      }
    });
  }

  yearChanged(): void {
    this.loadHolidays();
  }
}