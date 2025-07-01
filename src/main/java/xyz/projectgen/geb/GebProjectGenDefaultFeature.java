package xyz.projectgen.geb;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.projectgen.core.feature.DefaultFeature;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.options.Options;
import jakarta.inject.Singleton;

import java.util.Set;

@Singleton
class GebProjectGenDefaultFeature implements DefaultFeature {
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
