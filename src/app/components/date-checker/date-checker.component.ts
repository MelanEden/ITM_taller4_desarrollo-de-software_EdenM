import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { DateAdapter } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-date-checker',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatIconModule
  ],
  templateUrl: './date-checker.component.html',
  styleUrls: ['./date-checker.component.scss']
})
export class DateCheckerComponent {
  dateForm: FormGroup;
  result: { mensaje: string } | null = null;
  loading = false;
  celebration = false;

  constructor(
    private fb: FormBuilder,
    private apiService: ApiService,
    private snackBar: MatSnackBar,
    private dateAdapter: DateAdapter<Date>
  ) {
    this.dateForm = this.fb.group({
      year: [
        new Date().getFullYear(),
        [Validators.required, Validators.min(1900), Validators.max(2100)]
      ],
      month: [
        new Date().getMonth() + 1,
        [Validators.required, Validators.min(1), Validators.max(12)]
      ],
      day: [
        new Date().getDate(),
        [Validators.required, Validators.min(1), Validators.max(31)]
      ]
    });
    this.dateAdapter.setLocale('es-CO');
  }

  getMonthName(m: number) {
    const ms = [
      'Enero', 'Febrero', 'Marzo',
      'Abril', 'Mayo', 'Junio',
      'Julio', 'Agosto', 'Septiembre',
      'Octubre', 'Noviembre', 'Diciembre'
    ];
    return ms[m - 1];
  }

  checkDate(): void {
    if (this.dateForm.invalid) {
      return;
    }
    this.loading = true;
    this.result = null;
    this.celebration = false;

    const { year, month, day } = this.dateForm.value;
    this.apiService.checkDate(year, month, day).subscribe({
      next: ({ mensaje }) => {
        this.result = { mensaje };

        // Validación: si el mensaje es exactamente "Es festivo", celebramos
        if (mensaje.trim().toLowerCase() === 'es festivo') {
          this.celebration = true;
        } else {
          // En cualquier otro caso (por ejemplo "No es festivo"), no celebramos
          this.celebration = false;
        }

        this.loading = false;
      },
      error: () => {
        this.snackBar.open('Error al verificar la fecha', 'Cerrar', { duration: 3000 });
        this.loading = false;
      }
    });
  }
}
