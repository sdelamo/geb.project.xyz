package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.projectgen.core.buildtools.MavenCentral;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.generator.GeneratorContext;
import jakarta.inject.Singleton;

@Singleton
class MavenCentralFeature implements Feature {
    @Override
    public @NonNull String getName() {
        return "maven-central";
    }

    @Override
    public String getTitle() {
        return "Adds Maven Central repository";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        generatorContext.getRootModule().repositories().add(new MavenCentral());
    }
}
