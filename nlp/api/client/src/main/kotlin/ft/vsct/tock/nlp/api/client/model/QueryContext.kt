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

package ft.vsct.tock.nlp.api.client.model

import java.time.ZoneOffset.UTC
import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import java.util.Locale
import java.util.UUID

/**
 *
 */
data class QueryContext(val language: Locale,
                        val clientId: String,
                        val dialogId: String,
                        val clientDevice: String? = null,
                        val referenceDate: ZonedDateTime = now(UTC),
                        val engineType: NlpEngineType = NlpEngineType.stanford,
                        val registerQuery: Boolean = true) {
}