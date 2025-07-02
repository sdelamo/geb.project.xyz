package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.projectgen.core.buildtools.dependencies.Dependency;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.feature.FeatureContext;
import io.micronaut.projectgen.core.generator.GeneratorContext;
import jakarta.inject.Singleton;

import static xyz.projectgen.geb.features.SeleniumBom.GROUP_ID_ORG_SELENIUMHQ_SELENIUM;

@Singleton
class SeleniumEdgeDriver extends RequiresMavenCentralFeature {
    private static final String ARTIFACT_ID_SELENIUM_EDGE_DRIVER = "selenium-edge-driver";
    private static final Dependency DEPENDENCY_SELENIUM_CHROME_DRIVER = Dependency.builder()
            .groupId(GROUP_ID_ORG_SELENIUMHQ_SELENIUM)
            .artifactId(ARTIFACT_ID_SELENIUM_EDGE_DRIVER)
            .test()
            .build();

    private final SeleniumBom seleniumBom;

    SeleniumEdgeDriver(MavenCentralFeature mavenCentralFeature,
                       SeleniumBom seleniumBom) {
        super(mavenCentralFeature);
        this.seleniumBom = seleniumBom;
    }

    @Override
    public void processSelectedFeatures(FeatureContext featureContext) {
        featureContext.addFeatureIfNotPresent(SeleniumBom.class, seleniumBom);
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.getRootModule().addDependency(DEPENDENCY_SELENIUM_CHROME_DRIVER);
    }

    @NonNull
    @Override
    public String getName() {
        return "selenium-edge-driver";
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Selenium Edge Driver";
    }

    @Override
    public String getDescription() {
        return "Adds the selenium edge driver dependency to the proejct";
    }

}
