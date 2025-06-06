import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Holiday } from '../models/holiday.model';
import { environment } from '../environments/environment';  // Asegúrate de que la ruta sea correcta.

@Injectable({ providedIn: 'root' })
export class ApiService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getHolidays(year: number): Observable<{ festivos: Holiday[] }> {
    return this.http.get<{ festivos: Holiday[] }>(`${this.apiUrl}/api/festivos/obtener/${year}`);
  }

  checkDate(year: number, month: number, day: number): Observable<{ mensaje: string }> {
    return this.http.get<{ mensaje: string }>(`${this.apiUrl}/api/festivos/verificar/${year}/${month}/${day}`);
  }
}