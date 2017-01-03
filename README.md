# vb-android-app-quality [![Build Status][1]][2]

Sample Android project using Gradle, with basic quality tools set up.

It can serve as a clean base for any Gradle Android project, and illustrates my articles about
Gradle on my [website](http://vincentbrison.com).

The sample app computes Pi using various algorithms, and talks to a REST API.

## Libraries

The following libraries are used in the project:

- ButterKnife
- Dagger
- Retrofit
- RxAndroid

## Quality

The base configuration to run quality test can be found in the `/config` directory.
The followings tools are used:

- Checkstyle
- Findbugs
- PMD
- Lint
- Infer

To run these quality tools and get reports, you need to execute the following Gradle command:

```bash
./gradlew check
```

By default, reports will be generated in `app/build/reports`.

See [quality.gradle](config/quality.gradle) for configuration options of each tool.
You can for example set:

- The output format (xml or html)
- Abort the task when a failure is found

Refer to the [Gradle documentation][3] to configure those plugins.

## Flavors

The project itself show various uses of the flavor system. Flavors are defined in [build.gradle](app/build.gradle).
The following four flavors are defined:

#### approximationPi
PI is computed using a approximation.

#### daggerMockedPi
PI is computed using a mocked algorithm. REST communication is mocked through Dagger.

#### exactPi
PI is computed using an exact algorithm.

#### mockWebServerPi
PI is computed using a mocked algorithm. REST communication is mocked through MockWebServer.

You can define several properties to your flavors. One very useful is `applicationId` which let you
change the application ID of your application. This can be used to generate different flavors of
your application and install both of them on the same device (free and paid versions, dev, validation and release versions...).

You can also put specific sources and resources with flavors. As it is in this project, just create
a directory under the src folder, name it with the name of your flavor, and put inside java codes and resources.

## Testing

Work in progress...

## To go further

This project is based on these two other projects, which are awesome. Consider take a look at them:

 - [Quality-Tools-for-Android][4]
 - [volley-examples][5]

# License

    Copyright 2015 Vincent Brison.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://travis-ci.org/vincentbrison/vb-android-app-quality.svg?branch=master
[2]: https://travis-ci.org/vincentbrison/vb-android-app-quality
[3]: http://www.gradle.org/docs/current/userguide/userguide.html
[4]: https://github.com/stephanenicolas/Quality-Tools-for-Android
[5]: https://github.com/marcoRS/volley-examples
