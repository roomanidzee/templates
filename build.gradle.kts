import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

project.extra.set("versions", mapOf(
        "jackson" to "2.9.9",
        "kotlin" to "1.3.41",
        "logback" to "1.2.3",
        "slf4j" to "1.7.26",
        "jjwt" to "0.9.1",
        "jaxb" to "2.3.1",
        "mapstruct" to "1.3.0.Beta2",
        "spring" to "5.1.8.RELEASE"
))


plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.3.41"
    kotlin("plugin.spring") version "1.3.41"
    kotlin("kapt") version "1.3.41"
}

val versions : Map<String, String> by project.extra

group = "com.romanidze"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.bootJar {
    archiveName = "app.jar"
}

repositories {
    mavenCentral()
    mavenLocal()
    maven(url="https://jitpack.io")
    gradlePluginPortal()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-quartz")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${versions["jackson"]}")

    implementation("org.jetbrains.kotlin:kotlin-reflect:${versions["kotlin"]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions["kotlin"]}")

    implementation("com.github.pozo.mapstruct-kotlin:mapstruct-kotlin:${versions["mapstruct"]}")
    compile("org.mapstruct:mapstruct:${versions["mapstruct"]}")
    kapt("org.mapstruct:mapstruct-processor:${versions["mapstruct"]}")
    kapt("com.github.pozo.mapstruct-kotlin:mapstruct-kotlin-processor:${versions["mapstruct"]}")

    compile("org.springframework.boot:spring-boot-configuration-processor")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    compile("org.springframework:spring-oxm:${versions["spring"]}")

    compile("ch.qos.logback:logback-classic:${versions["logback"]}")
    compile("ch.qos.logback:logback-access:${versions["logback"]}")

    compile("org.slf4j:slf4j-api:${versions["slf4j"]}")
    compile("org.slf4j:jul-to-slf4j:${versions["slf4j"]}")
    compile("org.slf4j:log4j-over-slf4j:${versions["slf4j"]}")

    compile("io.jsonwebtoken:jjwt:${versions["jjwt"]}")

    compile("javax.xml.bind:jaxb-api:${versions["jaxb"]}")
    
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
