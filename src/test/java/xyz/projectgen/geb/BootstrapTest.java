package xyz.projectgen.geb;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class BootstrapTest {

    @Test
    void testBootstrapJs(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.GET("/assets/bootstrap/5.3.7-dist/js/bootstrap.bundle.min.js");
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(request));
        assertTrue(response.getHeaders().get(HttpHeaders.CACHE_CONTROL, String.class).isPresent());
        assertEquals("public, max-age=31536000, immutable", response.getHeaders().get(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    void testBootstrapCss(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        HttpRequest<?> request = HttpRequest.GET("/assets/bootstrap/5.3.7-dist/css/bootstrap.min.css");
        HttpResponse<?> response = assertDoesNotThrow(() -> client.exchange(request));
        assertTrue(response.getHeaders().get(HttpHeaders.CACHE_CONTROL, String.class).isPresent());
        assertEquals("public, max-age=31536000, immutable", response.getHeaders().get(HttpHeaders.CACHE_CONTROL));
    }
}
