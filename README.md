retrofit-converter-kson
=======================

A JSON converter for [Retrofit](https://github.com/square/retrofit) powered by
[Kson](https://github.com/grundleborg/kson).

Installation
------------

This library is currently available from a custom bintray repository.
Add the following line to your `build.gradle` in the `repositories` block:

```groovy
maven { url "https://dl.bintray.com/grundleborg/com.grundleborg.kson" }
```

And then in the `dependencies` block, add:

```groovy
compile 'com.grundleborg.kson:retrofit-converter-kson:0.2.1'
```

Example
-------

Here's an example of how to use the converter with your retrofit service to return
a result of the type `MyApiResultDataClass`, which is a Kotlin data class.

```kotlin
val mapper = ObjectMapper()
        mapper.registerType(MyApiResultDataClass::class)

val retrofit = Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addConverterFactory(KsonConverterFactory.create(mapper))
        .build()

val service = retrofit.create(Service::class.java)
```

Contributing
------------

* If you find a bug or missing feature, please file a ticket on the Github [issue tracker](https://github.com/grundleborg/retrofit-converter-kson/issues).
* Contributions in the form of Pull Requests are also welcome, although please open an issue to discuss first if you are proposing a major change.

License
-------

```
Copyright 2018 George Goldberg

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

