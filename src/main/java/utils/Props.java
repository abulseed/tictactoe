package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {
  private Props() {
  }

  private Properties loadProps() throws Exception {
    try {
      Properties props = new Properties();
      FileInputStream in;

      in = new FileInputStream("config.properties");
      props.load(in);
      return props;
    } catch (IOException e) {
      throw e;
    }
  }

  private static class PropsHolder {
    private static final Properties INSTANCE_PROPS() throws Exception {
      return new Props().loadProps();
    }
  }

  public static Properties getProperties() throws Exception {
    try {
      return PropsHolder.INSTANCE_PROPS();
    } catch (Exception e) {
      throw e;
    }
  }

  public static String readProp(String key) throws Exception {
    try {
      return Props.getProperties().getProperty(key);
    } catch (Exception e) {
      throw e;
    }
  }
}