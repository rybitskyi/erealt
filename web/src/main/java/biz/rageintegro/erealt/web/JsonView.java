package biz.rageintegro.erealt.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.rageintegro.erealt.domain.*;
import org.springframework.web.servlet.View;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class JsonView implements View {

    public static final JsonView instance = new JsonView();
    public static final String JSON_ROOT = "root";

    // This instance of XStream is thread-safe.  XStream is not
    // thread-safe if auto-detection of annotations is used.
    private XStream xstream = new XStream(new JettisonMappedXmlDriver());

    private JsonView() {
        // in a large project, listing every class with XStream
        // annotations is not practical.  2 alternatives are:
        //   * traversing the classpath using a specified root package
        //     and calling processAnnotations
        //   * enabling annotation auto-detection, but then the XStream
        //     instance is not thread-safe
        xstream.processAnnotations(District.class);
        xstream.processAnnotations(Region.class);
        xstream.processAnnotations(Street.class);
    }

    public String getContentType() {
        return "text/html; charset=UTF-8";
    }

    @SuppressWarnings("unchecked")
    public void render(Map model, HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        Object root = model.get(JsonView.JSON_ROOT);
        if (root == null) {
            throw new RuntimeException("JSON root object with key '"
                    + JsonView.JSON_ROOT + "' not found in model");
        }
        PrintWriter writer = response.getWriter();
        // even though the following method is "toXML" XStream is configured
        // to use Jettison to output JSON instead
        String json = xstream.toXML(root);
//        System.out.println("json: " + json); //todo: add log in debug mode
        writer.write(json);
    }

}
