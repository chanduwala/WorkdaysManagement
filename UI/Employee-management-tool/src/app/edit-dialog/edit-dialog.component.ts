import { Component, OnInit, Inject, Optional } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { EmpData } from "../app.component";
import { ApiService } from "../services/api.service";

@Component({
  selector: "app-edit-dialog",
  templateUrl: "./edit-dialog.component.html",
  styleUrls: ["./edit-dialog.component.css"],
})
export class EditDialogComponent implements OnInit {
  local_data: EmpData;
  error = "";
  constructor(public apiService: ApiService,
    public dialogRef: MatDialogRef<EditDialogComponent>,
    //@Optional() is used to prevent error if no data is passed
    @Optional() @Inject(MAT_DIALOG_DATA) public data: EmpData
  ) {
    console.log(data);
    this.local_data = { ...data };
  }

  ngOnInit(): void {}
  doAction(workDays: string, vacationDays: string) {
    if (this.local_data.action == "work") {
      if (!workDays || (workDays && +workDays <= 0)) {
        this.error = "Enter Valid WorkDays";
        return;
      }
      if (+workDays > 0) {
        this.local_data.workDays = +workDays;
        //this.local_data.vacationDays = +0;
      }
    } else {
      if (!vacationDays || (vacationDays && +vacationDays <= 0)) {
        this.error = "Enter Valid Vacation Days";
        return;
      }

      if (+vacationDays > 0) {
        // this.local_data.workDays = this.local_data.workDays;
        this.local_data.vacationDays = +vacationDays;
      }
    }
    let data: any = this.local_data;
    if (this.local_data.action === "work") {
      delete data.vacationDays;
    } else {
      delete data.workDays;
    }
    delete data.action;
    this.apiService.updateEmp(data).subscribe(
      (dataResult) => {
        console.log(dataResult);
        this.dialogRef.close({ event: "updateWork", data: dataResult });
      },
      (error) => {
        console.log("--erroe", error);
        this.error = error
        
      }
    );

    
  }

  closeDialog() {
    this.dialogRef.close({ event: "Cancel" });
  }
}
