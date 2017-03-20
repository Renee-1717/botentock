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

package fr.vsct.tock.bot.i18n

import fr.vsct.tock.nlp.entity.date.DateEntityValue
import fr.vsct.tock.nlp.entity.date.DateIntervalEntityValue
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Formattable
import java.util.Formatter
import java.util.Locale

/**
 *
 */
class DateTemplate(val date: ZonedDateTime?, val dateFormatter: DateTimeFormatter) : Formattable {

    constructor(date: DateEntityValue?, dateFormatter: DateTimeFormatter) : this(date?.date, dateFormatter)

    constructor(date: DateIntervalEntityValue?, dateFormatter: DateTimeFormatter) : this(date?.date, dateFormatter)

    fun format(locale: Locale): String {
        return date?.format(dateFormatter.withLocale(locale)) ?: ""
    }

    override fun formatTo(formatter: Formatter, flags: Int, width: Int, precision: Int) {
        formatter.format(format(formatter.locale()))
    }
}