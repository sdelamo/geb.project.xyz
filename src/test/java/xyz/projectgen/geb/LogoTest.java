package xyz.projectgen.geb;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@MicronautTest
class LogoTest {
    @Test
    void testHealth(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        assertDoesNotThrow(() -> client.exchange(HttpRequest.GET("/assets/images/logo.svg")));
    }
}
