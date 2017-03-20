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

package fr.vsct.tock.duckling.client

import fr.vsct.tock.nlp.core.service.entity.EntityEvaluator
import fr.vsct.tock.nlp.core.service.entity.EntityEvaluatorProvider

/**
 *
 */
object DucklingEntityEvaluatorProvider : EntityEvaluatorProvider {

    //TODO
    //override fun getEntityClassifier(): EntityClassifier = DucklingClassifier

    override fun getEntityEvaluator(): EntityEvaluator = DucklingParser

    override fun getSupportedEntityTypes(): Set<String> = DucklingParser.entityTypes
}