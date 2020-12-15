import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { throwError } from "rxjs";
import {catchError,retry,tap} from 'rxjs/operators'
import { EmpData } from "../app.component";

@Injectable({ providedIn: "root" })
export class ApiService {
  constructor(private http: HttpClient) {}
  //empListUrl = '../assets/response.json';
  empListUrl = "http://localhost:8080/api/employee/all";
  updateEmpUrl = "http://localhost:8080/api/employee/";

  getConfig() {
    return this.http.get<EmpData[]>(this.empListUrl);
  }
  updateEmp(data: EmpData) {
    return this.http.put<EmpData>(this.updateEmpUrl, data).pipe(catchError(this.handleError));
  }
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error("An error occurred:", error.error.message);

    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // Return an observable with a user-facing error message.
    return throwError(error.error.details);
  }
}
