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

package fr.vsct.tock.nlp.model.service.engine

import fr.vsct.tock.nlp.core.NlpEngineType
import fr.vsct.tock.nlp.model.ClassifierContext
import fr.vsct.tock.nlp.model.ClassifierContextKey
import fr.vsct.tock.nlp.model.EntityCallContext
import fr.vsct.tock.nlp.model.EntityClassifier
import fr.vsct.tock.nlp.model.IntentClassifier
import fr.vsct.tock.nlp.model.IntentContext
import fr.vsct.tock.nlp.model.Tokenizer
import fr.vsct.tock.nlp.model.TokenizerContext
import fr.vsct.tock.shared.ThreadSafe
import java.util.concurrent.ConcurrentHashMap

/**
 *
 */
@ThreadSafe
object NlpEngineRepository {

    private val repository = ConcurrentHashMap<NlpEngineType, NlpEngineProvider>()

    fun registerEngineProvider(provider: NlpEngineProvider) {
        repository.put(provider.type(), provider)
    }

    fun registeredNlpEngineTypes(): Set<NlpEngineType> {
        return repository.keys
    }

    private fun getProvider(nlpEngineType: NlpEngineType): NlpEngineProvider {
        return repository[nlpEngineType] ?: error("Unknown nlp engine type : $nlpEngineType")
    }

    fun getTokenizer(context: TokenizerContext): Tokenizer {
        return getProvider(context.engineType).getTokenizer(NlpModelRepository.getTokenizerModelHodler(context))
    }

    fun getIntentClassifier(context: IntentContext): IntentClassifier {
        return getProvider(context.engineType).let {
            it.getIntentClassifier(NlpModelRepository.getIntentModelHodler(context, it))
        }
    }

    fun getEntityClassifier(context: EntityCallContext): EntityClassifier? {
        return getProvider(context.engineType).let { provider ->
            NlpModelRepository.getEntityModelHodler(context, provider)?.let { model ->
                provider.getEntityClassifier(model)
            }
        }
    }

    fun <T : ClassifierContextKey> getModelBuilder(context: ClassifierContext<T>): NlpEngineModelBuilder {
        return getProvider(context.engineType).getModelBuilder()
    }

    fun <T : ClassifierContextKey> getModelIo(context: ClassifierContext<T>): NlpEngineModelIo {
        return getProvider(context.engineType).getModelIo()
    }

}