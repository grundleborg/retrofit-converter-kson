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
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import java.io.IOException

class BasicTests {
    private val server = MockWebServer()
    private var service: Service? = null

    internal data class DataClass (
            val item: String
    )

    internal interface Service {
        @POST("/")
        fun dataClassTest(): Call<DataClass>
    }

    @Before
    fun setUp() {
        val mapper = ObjectMapper()
        mapper.typeMap[DataClass::class.java] = DataClass::class

        val retrofit = Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(KsonConverterFactory.create(mapper))
                .build()
        service = retrofit.create(Service::class.java)
    }

    @Test
    @Throws(IOException::class, InterruptedException::class)
    fun anInterface() {
        val jsonData = """{"item":"value"}"""
        server.enqueue(MockResponse().setBody(jsonData))

        val call = service!!.dataClassTest()
        val response = call.execute()
        val body = response.body()
        assertThat(body?.item).isEqualTo("value")
    }
}