plugins {
    id("io.micronaut.application") version "4.5.4"
    id("com.gradleup.shadow") version "8.3.7"
}

version = "0.1"
group = "xyz.projectgen.geb"

repositories {
    mavenCentral()
}

dependencies {
    // ProjectGen
    implementation(platform("io.micronaut.projectgen:micronaut-projectgen-bom:0.0.1"))
    implementation("io.micronaut.projectgen:micronaut-projectgen-http-server")

    // Views
    implementation("io.micronaut.views:micronaut-views-thymeleaf")

    // Serialization
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.serde:micronaut-serde-jackson")

    // Validation
    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut.validation:micronaut-validation")
    // Route Validation
    annotationProcessor("io.micronaut:micronaut-http-validation")

    // Management
    implementation("io.micronaut:micronaut-management")

    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass = "xyz.projectgen.geb.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("24")
    targetCompatibility = JavaVersion.toVersion("24")
}


graalvmNative.toolchainDetection = false
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("xyz.projectgen.geb.*")
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "24"
}