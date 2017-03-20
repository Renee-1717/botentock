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

import {Injectable} from "@angular/core";
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Application} from "../model/application";
import {ApplicationsService} from "./applications.service";
import {Observable} from "rxjs";
import {StateService} from "../core/state.service";
@Injectable()
export class ApplicationsResolver implements Resolve<Application[]> {

  constructor(private appService: ApplicationsService, private state: StateService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Application[]> {
    return this.appService.retrieveCurrentApplication().map(_ => this.state.applications);
  }
}
