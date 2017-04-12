package se.pjodd.service.node.location.mozilla;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import se.pjodd.service.node.ServerConfiguration;
import se.pjodd.service.node.location.mozilla.request.Request;
import se.pjodd.service.node.location.mozilla.response.Response;

import java.net.URLEncoder;

/**
 * @author kalle
 * @since 2017-03-22 08:37
 */
@Singleton
public class MozillaLocationService {

  @Inject
  private ObjectMapper objectMapper;

  @Inject
  private ServerConfiguration serverConfiguration;

  private String userAgent = "pjodd.se";

  private String url = "https://location.services.mozilla.com/v1/geolocate?key=";

  private CloseableHttpClient httpClientFactory() {
    return HttpClientBuilder.create().setUserAgent(userAgent).build();
  }

  private JSONObject sendRequest(JSONObject request) throws Exception {
    CloseableHttpClient httpClient = httpClientFactory();
    try {
      HttpPost post = new HttpPost(url + URLEncoder.encode(serverConfiguration.getMozillaLocationServiceApiKey(), "UTF8"));
      post.setEntity(new StringEntity(request.toString(), ContentType.APPLICATION_JSON));
      CloseableHttpResponse response = httpClient.execute(post);
      try {

        String responseJson = EntityUtils.toString(response.getEntity());
        JSONObject responseJsonObject = new JSONObject(new JSONTokener(responseJson));

        if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() <= 299) {
          return responseJsonObject;
        } else if (response.getStatusLine().getStatusCode() == 404) {
          return null;
        } else {
          throw new Exception("HTTP " + response.getStatusLine().getStatusCode() + "\n" + responseJson);
        }

      } finally {
        response.close();
      }
    } finally {
      httpClient.close();
    }
  }

  private void assertSuccessfullResponse(JSONObject response) throws Exception {
    if (response.has("error")) {
      throw new Exception(response.toString());
    }
  }

  public Response locate(Request request) throws Exception {

    JSONObject requestJson = new JSONObject(new JSONTokener(objectMapper.writeValueAsString(request)));
    JSONObject responseJson = sendRequest(requestJson);
    if (responseJson == null) {
      return null;
    } else {
      assertSuccessfullResponse(responseJson);
      Response response = objectMapper.readValue(responseJson.toString(), Response.class);
      return response;
    }

  }


}