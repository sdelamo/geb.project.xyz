package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.projectgen.core.buildtools.dependencies.Dependency;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.generator.GeneratorContext;
import io.micronaut.projectgen.core.generator.ModuleContext;
import io.micronaut.projectgen.core.template.StringTemplate;
import jakarta.inject.Singleton;

@Singleton
public class GebSpock implements Feature {
    private static final String GROUP_ID_ORG_GEBISH = "org.gebish";
    private static final String GEB_VERSION = "7.0";
    public static final String ARTIFACT_ID_GEB_SPOCK = "geb-spock";
    private static final Dependency DEPENDENCY_GEB_SPOCK = Dependency.builder()
            .groupId(GROUP_ID_ORG_GEBISH)
            .artifactId(ARTIFACT_ID_GEB_SPOCK)
            .version(GEB_VERSION)
            .test()
            .build();
    private static final Dependency DEPENDENCY_GROOVY_BOM = Dependency.builder()
            .groupId("org.apache.groovy")
            .artifactId("groovy-bom")
            .version("4.0.27")
            .test()
            .pom()
            .build();

    @Override
    @NonNull
    public String getTitle() {
        return "Geb Spock";
    }

    @Override
    @NonNull
    public String getName() {
        return ARTIFACT_ID_GEB_SPOCK;
    }

    @Override
    public String getDescription() {
        return "Adds the geb-spock dependency for integration with the Spock testing framework";
    }

    @Nullable
    @Override
    public String getFrameworkDocumentation(GeneratorContext generatorContext) {
        return "https://groovy.apache.org/geb/manual/current/#spock-junit-testng";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        ModuleContext module = generatorContext.getRootModule();
        module.addDependency(DEPENDENCY_GEB_SPOCK);
        module.addDependency(DEPENDENCY_GROOVY_BOM);
        module.addTemplate("GebPage.groovy",
                new StringTemplate("src/test/groovy/com/example/GebPage.groovy", """
            package com.example

            import geb.Page

            class GebPage extends Page {

                static url = "https://groovy.apache.org/geb/"

                static at = { title.contains("Geb") }
            }
            """));
        module.addTemplate("GebHomepageSpec.groovy",
                new StringTemplate("src/test/groovy/com/example/GebHomepageSpec.groovy", """
            package com.example
            
            import geb.spock.GebSpec
            
            class GebHomepageSpec extends GebSpec {

                void "it is possible to load the Geb homepage"() {
                    when:
                    to(GebPage)

                    then:
                    at(GebPage)
                }
            }
            """));
    }
}
