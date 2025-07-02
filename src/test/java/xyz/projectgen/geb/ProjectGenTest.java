package xyz.projectgen.geb;

import io.micronaut.projectgen.core.buildtools.BuildTool;
import io.micronaut.projectgen.core.buildtools.Scope;
import io.micronaut.projectgen.core.buildtools.gradle.GradleDsl;
import io.micronaut.projectgen.core.io.PreviewGenerator;
import io.micronaut.projectgen.core.options.GenericOptions;
import io.micronaut.projectgen.core.options.GenericOptionsBuilder;
import io.micronaut.projectgen.core.options.JdkVersion;
import io.micronaut.projectgen.core.options.Options;
import io.micronaut.projectgen.core.options.TestFramework;
import io.micronaut.projectgen.test.BuildTestVerifier;
import io.micronaut.projectgen.test.GradleBuildTestVerifier;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class ProjectGenTest {

    @Test
    void testDefaultProjectGeneration(PreviewGenerator generator) throws Exception {
        Options options = GenericOptionsBuilder.builder()
                .name("geb-demo")
                .packageName("com.example")
                .group("com.example")
                .artifact("geb-demo")
                .version("1.0.0")
                .buildTools(List.of(BuildTool.GRADLE))
                .gradleDsl(GradleDsl.GROOVY)
                .testFramework(TestFramework.SPOCK)
                .java(JdkVersion.JDK_17)
                .features(List.of("selenium-firefox-driver"))
                .build();

        Map<String, String> project = generator.generate(options);
        assertTrue(project.containsKey("gradlew"));
        assertTrue(project.containsKey("gradlew.bat"));
        assertTrue(project.containsKey("gradle/wrapper/gradle-wrapper.jar"));
        assertTrue(project.containsKey("gradle/wrapper/gradle-wrapper.properties"));
        String buildGradle = project.get("build.gradle");
        BuildTestVerifier verifier = new GradleBuildTestVerifier(buildGradle, BuildTool.GRADLE, options.language(), options.testFramework());
        assertTrue(verifier.hasDependency("org.seleniumhq.selenium", "selenium-firefox-driver", Scope.TEST), buildGradle);
        assertTrue(verifier.hasDependency("org.gebish", "geb-spock", Scope.TEST), buildGradle);
        assertTrue(verifier.hasBom("org.seleniumhq.selenium", "selenium-bom", Scope.TEST), buildGradle);

    }
}
