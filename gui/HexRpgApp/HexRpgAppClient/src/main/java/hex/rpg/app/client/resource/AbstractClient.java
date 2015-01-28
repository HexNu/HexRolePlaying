package hex.rpg.app.client.resource;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hln
 */
public abstract class AbstractClient {

    private static final String BASEPATH = "http://localhost:8084/rpg/resources/";

    protected WebResource getWebResource() {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(config);
        return client.resource(BASEPATH);
    }

    protected ClientResponse getClientResponse(String uri) {
        ClientConfig config = new DefaultClientConfig();
        config.getClasses().add(JacksonJsonProvider.class);
        Client client = Client.create(config);
        WebResource resource = client.resource(uri);
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (!ClientResponse.Status.OK.equals(ClientResponse.Status.fromStatusCode(response.getStatus()))) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        } else {
            return response;
        }
    }

}
