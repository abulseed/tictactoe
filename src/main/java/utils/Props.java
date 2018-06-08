package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {
  private Props() {
  }

  private Properties loadProps() {
    Properties props = new Properties();
    FileInputStream in;
    try {
      in = new FileInputStream("properties.txt");
      props.load(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return props;
  }

  private static class PropsHolder {
    private static final Properties INSTANCE_PROPS = new Props().loadProps();
  }

  public static Properties getProperties() {
    return PropsHolder.INSTANCE_PROPS;
  }

  public static String readProp(String key) {
    return Props.getProperties().getProperty(key);
  }
}