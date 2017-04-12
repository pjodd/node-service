package se.pjodd.service.node;


import lombok.Data;
import se.pjodd.service.node.util.Environment;

import java.io.File;
import java.io.IOException;

/**
 * @author kalle
 * @since 2017-04-12
 */
@Data
public class ServerConfiguration {

  private String serviceName;

  private int httpPort;
  private String dataPath;
  private String mozillaLocationServiceApiKey;

  public ServerConfiguration(String serviceName) {
    this.serviceName = serviceName;
     httpPort = Environment.getValue("pjodd_http_port", 8080);
     dataPath = Environment.getValue("pjodd_data_path", "/srv/" + serviceName + "/");
     mozillaLocationServiceApiKey = Environment.getValue("pjodd_mozilla_location_api_key", "test");

  }

  public File getDataPath(String subdirectory) throws IOException {
    File dataPathFile = new File(getDataPath());
    if (!dataPathFile.exists() && !dataPathFile.mkdirs()) {
      throw new IOException("Unable to mkdirs " + dataPathFile.getAbsolutePath());
    }
    File subdirectoryFile = new File(dataPathFile, subdirectory);
    if (!subdirectoryFile.exists() && !subdirectoryFile.mkdirs()) {
      throw new IOException("Unable to mkdirs " + subdirectoryFile.getAbsolutePath());
    }
    return subdirectoryFile;
  }

}
