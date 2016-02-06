package ratpack.librato;

import org.codehaus.groovy.runtime.StringGroovyMethods;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Converts Heroku formatted envrionment variables for librato into a config source for ratpack.
 */
public class HerokuLibratoConfigUtility {
  public static Map<String, String> getLibratoProperties() {

    String source = System.getenv("LIBRATO_SOURCE");
    String token = System.getenv("LIBRATO_TOKEN");
    String user = System.getenv("LIBRATO_USER");

    Map<String, String> properties = new LinkedHashMap<String, String>();

    if (source != null && !source.isEmpty()) {
      properties.put("librato.sourceIdentifier", source);
    }

    if (token != null && !token.isEmpty()) {
      properties.put("librato.token", token);
    }

    if (user != null && !user.isEmpty()) {
      properties.put("librato.email", user);
    }


    return properties;
  }

}
