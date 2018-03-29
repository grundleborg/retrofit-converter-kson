/*
 * Copyright 2018 George Goldberg.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grundleborg.kson.retrofit

import com.grundleborg.kson.ObjectMapper
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

class KsonResponseBodyConverter<T: Any>(
        private val objectMapper: ObjectMapper,
        private val type: Type?
): Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody?): T {
        return objectMapper.parse(value!!.charStream()!!, type!!)
    }
}
