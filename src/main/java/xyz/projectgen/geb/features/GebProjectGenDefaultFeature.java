package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.projectgen.core.feature.DefaultFeature;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.feature.FeatureContext;
import io.micronaut.projectgen.core.options.Options;
import io.micronaut.projectgen.core.options.TestFramework;
import jakarta.inject.Singleton;

import java.util.Set;

@Singleton
class GebProjectGenDefaultFeature implements DefaultFeature {
    private final GroovyGradlePlugin groovyGradlePlugin;
    private final GebSpock gebSpock;

    GebProjectGenDefaultFeature(GroovyGradlePlugin groovyGradlePlugin,
                                GebSpock gebSpock) {
        this.groovyGradlePlugin = groovyGradlePlugin;
        this.gebSpock = gebSpock;
    }

    @Override
    public void processSelectedFeatures(FeatureContext featureContext) {
        featureContext.addFeature(groovyGradlePlugin);
        if (featureContext.getOptions().testFramework() == TestFramework.SPOCK) {
            featureContext.addFeature(gebSpock);
        }
    }

    @Override
    public boolean shouldApply(Options options, Set<Feature> selectedFeatures) {
        return true;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public @NonNull String getName() {
        return "geb-project-gen";
    }

    @Override
    public String getDescription() {
        return "Geb Project Generator Default feature";
    }
}
