package xyz.projectgen.geb.features;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.projectgen.core.buildtools.gradle.GradlePlugin;
import io.micronaut.projectgen.core.feature.Feature;
import io.micronaut.projectgen.core.generator.GeneratorContext;
import io.micronaut.projectgen.core.generator.ModuleContext;
import io.micronaut.projectgen.core.options.JdkVersion;
import io.micronaut.projectgen.core.utils.OptionUtils;
import jakarta.inject.Singleton;

@Singleton
public class GroovyGradlePlugin implements Feature {
    private static final int DEFAULT_JAVA_VERSION = 17;

    @Override
    public @NonNull String getName() {
        return "groovy-gradle-plugin";
    }

    @Override
    public void apply(GeneratorContext generatorContext) {
        if (OptionUtils.hasGradleBuildTool(generatorContext.getOptions())) {
            ModuleContext moduleContext = generatorContext.getRootModule();
            JdkVersion jdkVersion = generatorContext.getOptions().java();
            int java = jdkVersion != null ? jdkVersion.majorVersion() : DEFAULT_JAVA_VERSION;
            moduleContext.addBuildPlugin(GradlePlugin.builder().id("groovy")
                    .extension(String.format("""
test {
    useJUnitPlatform()
}
java {
    sourceCompatibility = JavaVersion.VERSION_%s
    targetCompatibility = JavaVersion.VERSION_%s
}
""", java, java)).build());
        }
    }

    @Override
    public @Nullable String getThirdPartyDocumentation(GeneratorContext generatorContext) {
        return "https://docs.gradle.org/current/userguide/groovy_plugin.html";
    }
}
