package test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  public static void main(String[] args) {
    HardwareManufacturer hardwareManufacturer = HardwareManufacturer.fromEvseUid("");
    System.out.println(hardwareManufacturer.toString());
  }

  public enum HardwareManufacturer {
    ABB(List.of("AB")), SIGNET(List.of("SI")), BTC(List.of("BT", "EA")), UNKNOWN(List.of("UNKNOWN"));

    private final List<String> prefixes;

    HardwareManufacturer(List<String> prefixes) {
      this.prefixes = prefixes;
    }

    public static HardwareManufacturer fromEvseUid(String uid) {
      if (uid == null) {
        return UNKNOWN;
      }
      for (HardwareManufacturer manufacturer : HardwareManufacturer.values()) {
        for (String prefix : manufacturer.getPrefixes()) {
          if (uid.startsWith(prefix)) {
            return manufacturer;
          }
        }
      }

      return UNKNOWN;
    }

    public List<String> getPrefixes() {
      return prefixes;
    }
  }
}
