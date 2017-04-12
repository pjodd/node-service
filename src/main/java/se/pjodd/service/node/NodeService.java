package se.pjodd.service.node;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provides;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.pjodd.service.node.domain.Root;

import javax.inject.Singleton;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @author kalle
 * @since 2017-04-12
 */
public class NodeService extends Service {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public List<Module> getModules() {
    return Collections.singletonList(new ServiceModule("node-service") {

      @Override
      public void configure(Binder binder) {
      }

      @Inject private ServerConfiguration serverConfiguration;

      @Provides
      @Singleton
      public Prevayler<Root> prevaylerFactory() throws Exception {
        File dataPath = serverConfiguration.getDataPath("prevalence");
        log.info("Opening Prevalence from {} ...", dataPath.getAbsoluteFile());
        Prevayler<Root> prevayler = PrevaylerFactory.createPrevayler(new Root(), dataPath.getAbsolutePath());
        log.info("Prevalence has been opened.");
        return prevayler;
      }

    });
  }


}
