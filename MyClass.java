
import java.util.*;

public class MyClass {
  public static void main(String args[]) {
    String name = null;
    String optionalName = Optional.ofNullable(name).orElse("Unknown");

    System.out.println("Name: " + optionalName);

    //String defaultName = optionalName.orElse("Default");
    //System.out.println("Default name: " + defaultName);
  }
}