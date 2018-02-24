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
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class KsonConverterFactory: Converter.Factory {
    companion object {
        @JvmStatic
        fun create() : KsonConverterFactory = create()

        @JvmStatic
        fun create(om: ObjectMapper) : KsonConverterFactory {
            return KsonConverterFactory(om)
        }
    }

    private val objectMapper: ObjectMapper

    private constructor(objectMapper: ObjectMapper = ObjectMapper()) {
        this.objectMapper = objectMapper
    }

    override fun requestBodyConverter(
            type: Type?,
            parameterAnnotations: Array<out kotlin.Annotation>?,
            methodAnnotations: Array<out kotlin.Annotation>?,
            retrofit: Retrofit?
    ): Converter<*, RequestBody>? {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }

    override fun responseBodyConverter(
            type: Type?,
            annotations: Array<out kotlin.Annotation>?,
            retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        return KsonResponseBodyConverter<Any>(objectMapper, type)
    }
}