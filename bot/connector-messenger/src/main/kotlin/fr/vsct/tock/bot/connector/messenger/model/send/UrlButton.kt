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

package fr.vsct.tock.bot.connector.messenger.model.send

import com.fasterxml.jackson.annotation.JsonProperty
import fr.vsct.tock.bot.engine.action.SendChoice.Companion.EXIT_INTENT
import fr.vsct.tock.bot.engine.action.SendChoice.Companion.TITLE_PARAMETER
import fr.vsct.tock.bot.engine.action.SendChoice.Companion.URL_PARAMETER
import fr.vsct.tock.bot.engine.message.Choice

/**
 *
 */
data class UrlButton(val url: String,
                     val title: String,
                     @JsonProperty("webview_height_ratio") val webviewHeightRatio: String? = null,
                     @JsonProperty("messenger_extensions") val messengerExtensions: String? = null,
                     @JsonProperty("fallback_url") val fallBackUrl: String? = null
) : Button(ButtonType.web_url) {

    override fun toChoice(): Choice {
        return Choice(
                EXIT_INTENT,
                mapOf(
                        URL_PARAMETER to url,
                        TITLE_PARAMETER to title
                ))
    }
}