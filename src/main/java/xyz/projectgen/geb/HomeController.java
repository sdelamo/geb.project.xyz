package xyz.projectgen.geb;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.View;

import java.util.Collections;
import java.util.Map;

@Controller
class HomeController {

    @View("index.html")
    @Get
    @Produces(MediaType.TEXT_HTML)
    Map<String, Object> index() {
        return Collections.emptyMap();
    }
}
