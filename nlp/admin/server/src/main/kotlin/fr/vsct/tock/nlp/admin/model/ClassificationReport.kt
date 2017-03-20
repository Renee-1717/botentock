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

package fr.vsct.tock.nlp.admin.model

import fr.vsct.tock.nlp.front.shared.parser.ParseResult
import fr.vsct.tock.nlp.front.shared.config.Classification
import fr.vsct.tock.nlp.front.shared.config.IntentDefinition

/**
 *
 */
data class ClassificationReport(
        val intentId: String,
        val entities: List<ClassifiedEntityReport>,
        val intentProbability: Double,
        val entitiesProbability: Double) {

    constructor(query: ParseResult, intentId: String) : this(
            intentId,
            query.entities.map { ClassifiedEntityReport(it) },
            query.intentProbability,
            query.entitiesProbability
    )

    constructor(classification: Classification) : this(
            classification.intentId,
            classification.entities.map { ClassifiedEntityReport(it) },
            1.0,
            1.0)

    fun toClassification(): Classification {
        return Classification(intentId, entities.map { it.toClassifiedEntity() })
    }
}