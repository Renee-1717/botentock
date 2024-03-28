/*
 * Copyright (C) 2017/2021 e-voyageurs technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.tock.bot.connector.whatsapp.cloud.model.send.media

import com.fasterxml.jackson.annotation.JsonProperty

data class Media(
        @JsonProperty("sha256") val sha256: String,
        @JsonProperty("mime_type") val mimeType: FileType,
        @JsonProperty("messaging_product") val messagingProduct: String,
        @JsonProperty("id") val id: String,
        @JsonProperty("url") val url: String,
        @JsonProperty("file_size") val fileSize: Long
)