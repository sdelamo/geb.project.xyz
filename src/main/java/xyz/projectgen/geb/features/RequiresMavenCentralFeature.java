package xyz.projectgen.geb.features;

import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.feature.FeatureContext;

public abstract class RequiresMavenCentralFeature implements Feature {

    private final MavenCentralFeature mavenCentralFeature;

    protected RequiresMavenCentralFeature(MavenCentralFeature mavenCentralFeature) {
        this.mavenCentralFeature = mavenCentralFeature;
    }

    @Override
    public void processSelectedFeatures(FeatureContext featureContext) {
        featureContext.addFeatureIfNotPresent(MavenCentralFeature.class, mavenCentralFeature);
    }
}
