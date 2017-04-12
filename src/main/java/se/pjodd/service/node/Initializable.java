package se.pjodd.service.node;

/**
 * @author kalle
 * @since 2017-02-15 02:24
 */
public interface Initializable {

  public abstract boolean open() throws Exception;
  public abstract boolean close() throws Exception;

}
