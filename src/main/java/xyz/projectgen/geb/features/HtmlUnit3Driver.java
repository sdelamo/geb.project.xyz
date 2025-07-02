package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.projectgen.core.buildtools.dependencies.Dependency;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.generator.GeneratorContext;
import jakarta.inject.Singleton;

@Singleton
class HtmlUnit3Driver extends RequiresMavenCentralFeature {
    private static final String ARTIFACT_ID_HTMLUNIT_3_DRIVER = "htmlunit3-driver";
    private static final String HTMLUNIT_3_VERSION = "4.33.0";
    private static final Dependency DEPENDENCY = Dependency.builder()
            .groupId(SeleniumBom.GROUP_ID_ORG_SELENIUMHQ_SELENIUM)
            .artifactId(ARTIFACT_ID_HTMLUNIT_3_DRIVER)
            .version(HTMLUNIT_3_VERSION)
            .test()
            .build();
    private static final String TITLE_HTML_UNIT_DRIVER = "HtmlUnit Driver";
    private static final String HTML_UNIT_DRIVER_DESCRIPTION = "HtmlUnitDriver is a WebDriver compatible driver for the HtmlUnit headless browser.";
    private static final String HTMLUNIT_DRIVER_GITHUB_URL = "https://github.com/SeleniumHQ/htmlunit-driver";

    protected HtmlUnit3Driver(MavenCentralFeature mavenCentralFeature) {
        super(mavenCentralFeature);
    }

    @Override
    @NonNull
    public String getTitle() {
        return TITLE_HTML_UNIT_DRIVER;
    }

    @Override
    @NonNull
    public String getName() {
        return ARTIFACT_ID_HTMLUNIT_3_DRIVER;
    }

    @Override
    public String getDescription() {
        return HTML_UNIT_DRIVER_DESCRIPTION;
    }

    @Nullable
    @Override
    public String getThirdPartyDocumentation(GeneratorContext generatorContext) {
        return HTMLUNIT_DRIVER_GITHUB_URL;
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.getRootModule().addDependency(DEPENDENCY);
    }
}
