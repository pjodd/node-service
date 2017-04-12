package se.pjodd.service;

import com.google.inject.Binder;
import com.google.inject.Module;
import junit.framework.Assert;
import lombok.Data;
import org.junit.Test;
import se.pjodd.service.node.Service;
import se.pjodd.service.node.ServiceModule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalle
 * @since 2017-03-07 22:06
 */
public class TestService {

  @Test
  public void test() throws Exception {

    ServiceModule serverModule = new ServiceModule("test") {
      @Override
      public void configure(Binder binder) {
      }
    };

    Service service = new Service() {
      @Override
      public List<Module> getModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(serverModule);
        return modules;
      }

    };
    
    Assert.assertTrue(service.open());
    try {



      System.currentTimeMillis();

      service.run();
//      while (true) {
//        Thread.sleep(1000);
//      }

    } finally {
      Assert.assertTrue(service.close());
    }
  }

  @Data
  public static class Root {

  }

}
