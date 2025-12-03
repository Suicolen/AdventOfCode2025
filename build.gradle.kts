plugins {
    id("java")
}

group = "org.spritepacker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://projectlombok.org/edge-releases")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly("org.projectlombok:lombok:edge-SNAPSHOT")
    annotationProcessor("org.projectlombok:lombok:edge-SNAPSHOT")
    testCompileOnly("org.projectlombok:lombok:edge-SNAPSHOT")
    testAnnotationProcessor("org.projectlombok:lombok:edge-SNAPSHOT")
    implementation("one.util:streamex:0.8.4")
}

tasks.test {
    useJUnitPlatform()
}