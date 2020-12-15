import { Component } from "@angular/core";
import { DataSource } from "@angular/cdk/collections";
import { Observable } from "rxjs";
import { of } from "rxjs";
import { ApiService } from "../app/services/api.service";
import { EditDialogComponent } from "./edit-dialog/edit-dialog.component";
import { MatDialog } from "@angular/material/dialog";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  constructor(public apiService: ApiService, public dialog: MatDialog) {
    apiService.getConfig().subscribe((dataRes) => {
      this.data = dataRes;
      this.dataSource = new EmpDataSource(this.data);
      //this.dataSource.connect()
    });
    //this.displayedColumns.push("Actions")
  }

  columns = [
    {
      columnDef: "empId",
      header: "ID.",
      cell: (element: EmpData) => `${element.empId}`,
    },
    {
      columnDef: "name",
      header: "Name",
      cell: (element: EmpData) => `${element.name}`,
    },
    {
      columnDef: "workDays",
      header: "Working Days",
      cell: (element: EmpData) => `${element.workDays}`,
    },
    {
      columnDef: "vacationDays",
      header: "Vacation Days Remaining",
      cell: (element: EmpData) => `${element.vacationDays}`,
    },
    {
      columnDef: "employeeType",
      header: "Type",
      cell: (element: EmpData) => `${element.employeeType}`,
    },
    {
      columnDef: "action",
      header: "Actions",
      cell: (element: EmpData) => `${element}`,
    },
  ];
  data: EmpData[] = [
    // {
    //   workDays:0,
    //   employeeType:"SALARIED",
    //   vacationDays:0.0,
    //   name:"Nancy",
    //   empId:1004
    // }
  ];

  displayedColumns = this.columns.map((c) => c.columnDef);

  dataSource = new EmpDataSource(this.data);

  openDialog(action: string, obj: EmpData) {
    console.log(obj);
    console.log(action);
    obj.action = action;

    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: "250px",
      data: obj,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result.event == "updateWork") {
        this.updateRowData(result.data);
      }
    });
  }

  updateRowData(empdata: EmpData) {
    console.log("updated", empdata);
   
    this.dataSource.data.filter((value, key) => {
      if (value.empId == empdata.empId) {
        value.vacationDays = empdata.vacationDays;
        value.workDays = empdata.workDays;
      }
      return true;
    });
    
  }
}

export interface EmpData {
  workDays: number;
  employeeType: string;
  vacationDays: number;
  name: string;
  empId: number;
  action: string;
}

export class EmpDataSource extends DataSource<any> {
  /** Connect function called by the table to retrieve one stream containing the data to render. */
  data;
  constructor(data1: EmpData[]) {
    super();
    this.data = data1;
  }
  connect(): Observable<EmpData[]> {
    return of(this.data);
  }

  disconnect() {}
}
