package xyz.projectgen.geb.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import io.micronaut.json.JsonMapper;
import io.micronaut.projectgen.core.diff.FeatureDiffer;
import io.micronaut.projectgen.core.io.PreviewGenerator;
import io.micronaut.projectgen.core.io.TreeNode;
import io.micronaut.projectgen.core.io.TreeNodeGenerator;
import io.micronaut.projectgen.core.options.Options;
import io.micronaut.projectgen.core.utils.CodeSample;
import io.micronaut.projectgen.http.server.OptionsBuilder;
import io.micronaut.views.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Controller
class ModalController {
    private static final Logger LOG = LoggerFactory.getLogger(ModalController.class);
    private final TreeNodeGenerator treeNodeGenerator;
    private final PreviewGenerator previewGenerator;
    private final JsonMapper jsonMapper;
    private final OptionsBuilder optionsBuilder;

    ModalController(TreeNodeGenerator treeNodeGenerator,
                    PreviewGenerator previewGenerator,
                    JsonMapper jsonMapper,
                    OptionsBuilder optionsBuilder) {
        this.treeNodeGenerator = treeNodeGenerator;
        this.previewGenerator = previewGenerator;
        this.jsonMapper = jsonMapper;
        this.optionsBuilder = optionsBuilder;
    }

    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post("/modal/preview")
    HttpResponse<ModelAndView<Map<String, Object>>> preview(@Body Map<String, Object> form) {
        Options options = optionsBuilder.createOptions(form);
        try {
            Map<String, String> project = previewGenerator.generate(options);
            TreeNode treeNode = treeNodeGenerator.generate(project);
            List<CodeSample> codeSamples = CodeSample.of(project);
            String treeJson = jsonMapper.writeValueAsString(treeNode.children());
            return HttpResponse.ok(new ModelAndView<>("modal/preview.html",
                Map.of(
                    "tree", treeNode,
                    "codeSamples", codeSamples,
                    "treeJson", treeJson
                )
            ));
        } catch (Exception e) {
            LOG.error("could not generate preview", e);
            return HttpResponse.serverError();
        }
    }
}
