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
import {RestService} from "../core/rest/rest.service";
import {StateService} from "../core/state.service";
import {ParseQuery, Sentence, EntityType, SearchQuery, SentencesResult, EntityDefinition} from "../model/nlp";
import {Observable} from "rxjs";
import {Intent, Application} from "../model/application";

@Injectable()
export class NlpService {

  constructor(private rest: RestService,
              private state: StateService) {
    this.getEntityTypes().subscribe(types => state.entityTypes = types);
  }

  parse(parseQuery: ParseQuery): Observable<Sentence> {
    return this.rest.post("/parse", parseQuery, Sentence.fromJSON);
  }

  saveIntent(intent: Intent): Observable<Intent> {
    return this.rest.post("/intent", intent, Intent.fromJSON);
  }

  removeIntent(application:Application, intent: Intent): Observable<Intent> {
    return this.rest.delete(`/application/${application._id}/intent/${intent._id}`);
  }

  removeEntity(application:Application, intent: Intent, entity:EntityDefinition): Observable<Intent> {
    return this.rest.delete(`/application/${application._id}/intent/${intent._id}/entity/${entity.entityTypeName}/${entity.role}`);
  }

  getEntityTypes(): Observable<EntityType[]> {
    return this.rest.get("/entities", EntityType.fromJSONArray);
  }

  createEntityType(type: string): Observable<EntityType> {
    return this.rest.post("/entity/create", {type: type}, EntityType.fromJSON);
  }

  updateSentence(sentence: Sentence): Observable<Sentence> {
    return this.rest.post("/sentence", sentence)
  }

  searchSentences(query: SearchQuery): Observable<SentencesResult> {
    return this.rest.post("/sentences/search", query, SentencesResult.fromJSON)
  }

}
