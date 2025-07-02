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
class SeleniumChromeDriver extends RequiresMavenCentralFeature {
    private static final Dependency DEPENDENCY_SELENIUM_CHROME_DRIVER = Dependency.builder()
            .groupId(GROUP_ID_ORG_SELENIUMHQ_SELENIUM)
            .artifactId("selenium-chrome-driver")
            .test()
            .build();

    private final SeleniumBom seleniumBom;

    SeleniumChromeDriver(SeleniumBom seleniumBom, MavenCentralFeature mavenCentralFeature) {
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
        return "selenium-chrome-driver";
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Selenium Chrome Driver";
    }

    @Override
    public String getDescription() {
        return "Adds the selenium chrome driver dependency to the proejct";
    }

    @Override
    @Nullable
    public String getThirdPartyDocumentation(GeneratorContext generatorContext) {
        return "https://developer.chrome.com/docs/chromedriver/get-started";
    }
}
