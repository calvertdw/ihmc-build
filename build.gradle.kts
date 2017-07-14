//plugins {
//   id 'java-gradle-plugin'
//   id 'maven-publish'
//   id 'eclipse'
//   id 'idea'
//   id 'org.jetbrains.kotlin.jvm' version '1.1.1'
//   id 'com.gradle.plugin-publish' version '0.9.3'
//   id "org.gradle.kotlin.embedded-kotlin" version "0.10.9"
//   id "org.gradle.kotlin.kotlin-dsl" version "0.10.9"
//}

plugins {
   `java-gradle-plugin`
   `kotlin-dsl`
   `maven-publish`
   id("com.gradle.plugin-publish") version "0.9.7"
}

group = "us.ihmc.gradle"
version = "0.3.0"

extra["licenseURL"] = "http://www.apache.org/licenses/LICENSE-2.0.txt"
extra["licenseName"] = "Apache License, Version 2.0"
extra["bintrayLicenseName"] = "Apache-2.0"

gradlePlugin {
   (plugins) {
      "ihmc-build" {
         id = "ihmc-build"
         implementationClass = "us.ihmc.build.IHMCBuild"
      }
   }
}

pluginBundle {
   tags = listOf("build", "ihmc", "robotics")
   website = "https://github.com/ihmcrobotics/ihmc-build"
   vcsUrl = "https://github.com/ihmcrobotics/ihmc-build"
   description = "IHMC Robotics's Gradle common build logic plugin"
}

java {
   sourceCompatibility = JavaVersion.VERSION_1_8
   targetCompatibility = JavaVersion.VERSION_1_8
}

//sourceCompatibility = "1.8"
//targetCompatibility = "1.8"
//
//project.ext.licenseURL = "http://www.apache.org/licenses/LICENSE-2.0.txt"
//project.ext.licenseName = "Apache License, Version 2.0"
//project.ext.bintrayLicenseName = "Apache-2.0"

repositories {
   jcenter()
   maven {
      url = uri("https://plugins.gradle.org/m2/")
   }
}

dependencies {
   compile("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.5")
   compile("ca.cutterslade.gradle:gradle-dependency-analyze:1.0.3")
   compile("gradle.plugin.com.dorongold.plugins:task-tree:1.3")
   compile("us.ihmc:ihmc-ci-plugin:0.14.4")
   compile("org.jfrog.artifactory.client:artifactory-java-client-services:+")
   compile("org.jetbrains.kotlin:kotlin-stdlib:1.1.1")
}

//task sourceJar(type: Jar) {
//  from sourceSets.main.allJava
//  from sourceSets.main.allGroovy
//}

//task docJar(type: Jar, dependsOn: groovydoc) {
//   from groovydoc.destinationDir
//}

//jar {
//   manifest {
//      attributes(
//            "Created-By": "IHMC Gradle Build Script",
//            "Implementation-Title": project.name,
//            "Implementation-Version": project.version,
//            "Implementation-Vendor": "IHMC",
//
//            "Bundle-Name": project.name,
//            "Bundle-Version": project.version,
//            "Bundle-License": "${project.ext.licenseURL}",
//            "Bundle-Vendor": "IHMC")
//   }
//}

//publishing {
//   publications {
//      mavenJava(MavenPublication) {
//         group "${project.group}"
//         artifactId "${project.name}"
//         version "${project.version}"
//
//         from components.java
//
//        //artifact sourceJar {
//         //   classifier "sources"
//         //}
//
//         //artifact docJar {
//         //   classifier "javadoc"
//         //}
//      }
//   }
//}

//pluginBundle {
//   website = 'https://github.com/ihmcrobotics/ihmc-build'
//   vcsUrl = 'https://github.com/ihmcrobotics/ihmc-build'
//   tags = ['build', 'ihmc', 'robotics']
//   description = "IHMC Robotics's Gradle common build logic plugin"
//
//   plugins {
//      ihmcBuildPlugin {
//         id = 'us.ihmc.gradle.ihmc-build'
//         displayName = 'IHMC Build Plugin'
//         version = project.version
//      }
//   }
//}
