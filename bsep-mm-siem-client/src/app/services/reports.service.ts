import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  constructor(private http: HttpClient) {}

  getTotalLogs(): Observable<any> {
    return this.http.get('/api/reports/logs/total');
  }

  getLogsLastMonth(): Observable<any> {
    return this.http.get('/api/reports/logs/last-month');
  }

  getMonthlyReport(): Observable<any> {
    return this.http.get('api/reports/logs/last-month-by-day');
  }

}
