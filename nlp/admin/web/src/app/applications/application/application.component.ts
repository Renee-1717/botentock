/*
 * Copyright (C) 2017 VSCT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {Component, OnInit} from "@angular/core";
import {MdSnackBar, MdDialog} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {StateService} from "../../core/state.service";
import {ApplicationsService} from "../applications.service";
import {Application} from "../../model/application";
import {ConfirmDialogComponent} from "../../shared/confirm-dialog/confirm-dialog.component";

@Component({
  selector: 'tock-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  applications: Application[];
  application: Application;
  newApplication: boolean;
  newLocale: string;

  constructor(private route: ActivatedRoute,
    private snackBar: MdSnackBar,
    private dialog: MdDialog,
    private state: StateService,
    private applicationService: ApplicationsService,
    private router: Router) {
  }

  ngOnInit() {
    this.applications = this.state.applications;
    this.route.params.subscribe(params => {
        const id = params['id'];
        if (id && id.length !== 0) {
          this.application = this.applications.find(a => a._id === id);
          if (this.application) {
            this.application = this.application.clone();
          }
        } else {
          this.newApplication = true;
          this.application = new Application("", this.state.user.organization, [], [this.state.currentLocale])
        }
      }
    );
  }

  saveApplication() {
    this.applicationService.saveApplication(this.application)
      .subscribe(app => {
        this.state.applications = this.state.applications.filter(a => a._id !== app._id);
        this.state.applications.push(app);
        this.state.currentApplication = app;
        this.state.sortApplications();
        this.snackBar.open(`Application ${app.name} saved`, "Save Application", {duration: 1000});
        this.router.navigateByUrl("/applications")
      }, error => {
        this.snackBar.open(error, "Error", {});
      });
  }

  deleteApplication() {
    let dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: "Delete the Application",
        subtitle: "Are you sure?",
        action: "Delete"
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === "delete") {
        this.applicationService.deleteApplication(this.application).subscribe(
          result => {
            this.snackBar.open(`Application ${this.application.name} deleted`, "Delete Application", {duration: 1000});

            this.router.navigateByUrl("/applications");
          });
      }
    });
  }

  removeLocale(locale: string) {
    this.application.supportedLocales.splice(this.application.supportedLocales.indexOf(locale), 1);
    this.snackBar.open(`${this.state.localeName(locale)} removed`, "Locale", {duration: 1000});
  }

  addLocale(newLocale: string) {
    this.application.supportedLocales.push(newLocale);
    this.snackBar.open(`${this.state.localeName(newLocale)} added`, "Locale", {duration: 1000});
  }

}
