# Artifact Collector Extension

[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/khmarbaise/artifact-collector-extension.svg?label=License)](http://www.apache.org/licenses/)
[![Maven Central](https://img.shields.io/maven-central/v/com.soebes.extensions/artifact-collector-extension.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cga%7C1%7Cartifact-collector-extension)
[![Build Status](https://travis-ci.org/khmarbaise/artifact-collector-extension.svg?branch=master)](https://travis-ci.org/khmarbaise/artifact-collector-extension)

If you like to use this extensions you need to put the resulting jar
file of this project into the `${M2_HOME}/lib/ext` directory.

If you like to use this extension in relationship with Maven 3.3.1+ you
can define the following `.mvn/extensions.xml` file:

(NOT YET POSSIBLE. NOT YET ON CENTRAL)
``` xml
<extensions xmlns="http://maven.apache.org/EXTENSIONS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/EXTENSIONS/1.0.0 http://maven.apache.org/xsd/core-extensions-1.0.0.xsd">
  <extension>
    <groupId>com.soebes.maven.extensions</groupId>
    <artifactId>artifact-collector-extension</artifactId>
    <version>0.1.0</version>
  </extension>
</extensions>
```

You can also use it with Maven 3.1.1+ via defining it via command line:

```
mvn =-Dmaven.ext.class.path=artifact-collector-extension-0.1.0.jar
```

An example output can look like this:

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.423 s
[INFO] Finished at: 2016-05-08T13:22:10+02:00
[INFO] Final Memory: 24M/310M
[INFO] ------------------------------------------------------------------------
[INFO] --             Maven Artifact Collector Summary                       --
[INFO] ------------------------------------------------------------------------
[INFO] test.maven.plugin.profiler:parse-pom:0.1.0-SNAPSHOT:jar
[INFO] test.maven.plugin.profiler:parse-pom:0.1.0-SNAPSHOT:pom
[INFO] test.maven.plugin.profiler:parse-pom:0.1.0-SNAPSHOT:jar:jar-with-dependencies
```

TODO
----
 o To use it at the moment you need to build it yourself and install it into 
   your local cache, cause we are not in Maven Central yet.

[1]: http://maven.apache.org/ref/3.0.3/maven-core/apidocs/org/apache/maven/eventspy/AbstractEventSpy.html
