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

export class Entry<F,S> {
  constructor(public first: F, public second: S) {
  }

  static fromJSON<F,S>(json: any): Entry<F,S> {
    const value = Object.create(Entry.prototype);
    const result = Object.assign(value, json, {});

    return result;
  }

  static fromJSONArray<F,S>(json?: Array<any>): Entry<F,S>[] {
    return json ? json.map(m => Entry.fromJSON<F,S>(m)) : [];
  }
}

export class ApplicationScopedQuery {
  constructor(public namespace: string,
              public applicationName: string,
              public language: string) {
  }
}

export class PaginatedQuery extends ApplicationScopedQuery {
  constructor(public namespace: string,
              public applicationName: string,
              public language: string,
              public start: number,
              public size: number) {
    super(namespace, applicationName, language)
  }
}
