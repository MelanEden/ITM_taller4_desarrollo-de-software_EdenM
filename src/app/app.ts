import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { DateCheckerComponent } from './components/date-checker/date-checker.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HeaderComponent, DateCheckerComponent, CalendarComponent, MatDividerModule],
  templateUrl: './app.html',
  styleUrls: ['./app.scss']
})
export class App {
  title = 'Festivos en Colombia';
}