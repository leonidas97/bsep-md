import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CertificateRequestService {

  constructor(private http: HttpClient) { }

  getPendingSignedRequests(): Observable<any> {
    return this.http.get('api/certificate-request');
  }

  approveCertificateRequest(id: number): Observable<any> {
    return this.http.put(`api/certificate-request/approve/${id}`, null);
  }

  declineCertificateRequest(id: number): Observable<any> {
    return this.http.put(`api/certificate-request/decline/${id}`, null);
  }
  
}
