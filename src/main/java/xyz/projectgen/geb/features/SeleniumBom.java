package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.projectgen.core.buildtools.dependencies.Dependency;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.generator.GeneratorContext;
import jakarta.inject.Singleton;

@Singleton
class SeleniumBom extends RequiresMavenCentralFeature {
    public static final String GROUP_ID_ORG_SELENIUMHQ_SELENIUM = "org.seleniumhq.selenium";
    private static final String SELENIUM_VERSIONS = "4.34.0";
    private static final String ARTIFACT_ID_SELENIUM_BOM = "selenium-bom";
    private static final Dependency DEPENDENCY_SELENIUM_BOM = Dependency.builder()
            .groupId(GROUP_ID_ORG_SELENIUMHQ_SELENIUM)
            .artifactId(ARTIFACT_ID_SELENIUM_BOM)
            .version(SELENIUM_VERSIONS)
            .pom()
            .test()
            .build();

    protected SeleniumBom(MavenCentralFeature mavenCentralFeature) {
        super(mavenCentralFeature);
    }

    @Override
    @NonNull
    public String getTitle() {
        return "Selenium BOM (Bill of Materials)";
    }

    @Override
    @NonNull
    public String getName() {
        return ARTIFACT_ID_SELENIUM_BOM;
    }

    @Override
    public String getDescription() {
        return "Adds the selenium bom dependency.";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.getRootModule().addDependency(DEPENDENCY_SELENIUM_BOM);
    }
}
