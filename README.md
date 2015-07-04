vb-android-app-quality
======================

Sample android project using Gradle, with basic quality tools set up. 

This project for provide a clean base to any Gradle Android project.

This project also illustrate my articles about Gradle on my [website](http://vincentbrison.com).

Quality
-------
You will find under the directory /config the base configuration to run quality test on the project.
The followings tools are used :
 - Checkstyle.
 - Findbugs.
 - PMD.
 - Lint.
 
To run these quality tools and get reports, you need to execute the following gradle command :

 ```bash
  gradle check
  ```

Findbugs needs the binaries of the project, so be sure to run at least one time the following command, before launching a check :
 
 ```bash
   gradle build
 ```
 
You can modify the behaviour of each tool through the [quality.gradle](config/quality.gradle) file.
You can for example set :
 - The output format (xml or html).
 - Abort the task when a failure is found.
 
You should refer to this [gradle documentation](http://www.gradle.org/docs/current/userguide/userguide.html) to configure those plugins.

By default, all the reports will be generated in the folder app/build/reports.

Flavor
------
The project itself show various uses of the flavor system. Flavors are defined in the [build.gradle](app/build.gradle).
You need to provide a least a name to the flavor : 

You can define several properties to your flavors. One very useful is the `packageName` which let you
change the package name of your application. This can be used to generate differents flavors of 
your application and install both of them on the same device (free and paid versions, dev, validation and release versions...).

You can also put specific sources and resources with flavors. As it is in this project, just create
a directory under the src folder, name it with the name of your flavor, and put inside java codes and resources.

Testing
-------
Work in progress...
 

To go further
-------------
This project is based on these two other projects, which are awesome. Consider take a look at them :
 - [Quality-Tools-for-Android](https://github.com/stephanenicolas/Quality-Tools-for-Android).
 - [volley-examples](https://github.com/marcoRS/volley-examples).
 

License
=======

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
