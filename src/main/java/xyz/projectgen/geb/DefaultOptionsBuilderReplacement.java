package xyz.projectgen.geb;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.projectgen.core.options.GenericOptionsBuilder;
import io.micronaut.projectgen.core.options.TestFramework;
import io.micronaut.projectgen.http.server.DefaultOptionsBuilder;
import jakarta.inject.Singleton;

import java.util.Map;
import java.util.Optional;

@Replaces(DefaultOptionsBuilder.class)
@Singleton
public class DefaultOptionsBuilderReplacement extends DefaultOptionsBuilder {

    @Override
    protected @NonNull GenericOptionsBuilder createOptionsBuilder(@Nullable Map<String, Object> form) {
        GenericOptionsBuilder builder = super.createOptionsBuilder(form);
        getField(form, "testFramework")
                .flatMap(TestFramework::of)
                .ifPresent(builder::testFramework);
        return builder;
    }

    private Optional<String> getField(Map<String, Object> form, String fieldName) {
        Object nameObject = form.get(fieldName);
        if (nameObject == null) {
            return Optional.empty();
        }
        return Optional.of(nameObject.toString());
    }
}
