plugins {
    base
    kotlin("jvm") version "1.3.50" apply false
    kotlin("plugin.spring") version "1.3.50" apply false
    //id("com.google.cloud.tools.jib") version "1.7.0"
}

allprojects {
    group = "org.jid.tests"
    version = "1.0.0-SNAPSHOT"
    repositories {
        mavenCentral()
        jcenter()
    }
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}