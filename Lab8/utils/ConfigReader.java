package utils;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigReader {

    private Properties prop;

    public static void main(String[] args) {
        ConfigReader cr = new ConfigReader();
        cr.readConfig("assets/GameSnake.cfg");
        System.out.println(cr.get("KEY_LEFT", int.class));
    }


    public ConfigReader() {
        prop = new Properties();
    }

    public Object get(String name, Class<?> type){
        String value = prop.getProperty(name);
        if (type == String.class) {
            return value;
        }
        if (type == boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (type == int.class){
            return Integer.parseInt(value);
        }
        if (type == float.class) {
            return Float.parseFloat(value);
        }

        return 0;
    }

    public int prepareCoord(int value) {
        return (value * (int)get("CELL_SIZE", int.class) + (int)((int)get("CELL_SIZE", int.class) / 2));
    }

    public boolean readConfig(String fileName) {
      try (FileInputStream fis = new FileInputStream(fileName)) {
          prop.load(fis);
          return true;
      } catch (IOException ex) { System.out.println("Settings read error!"); }

      return false;
    }
}
