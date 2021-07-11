package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/18/2021  12:11 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/validate-ip-address/
 *
 * Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 *
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 *
 *
 * Example 1:
 *
 * Input: IP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * Example 2:
 *
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * Example 3:
 *
 * Input: IP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 * Example 4:
 *
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
 * Output: "Neither"
 * Example 5:
 *
 * Input: IP = "1e1.4.5.6"
 * Output: "Neither"
 *
 *
 * Constraints:
 *
 * IP consists only of English letters, digits and the characters '.' and ':'.
 * Accepted
 */
public class ValidateIPAddress {

  public static void main(String[] args) {
    ValidateIPAddress o = new ValidateIPAddress();
    System.out.println();
  }

  public String validIPAddress_faster(String IP) {
    String[] arrOfStr1 = IP.split("\\.",-1);
    String[] arrOfStr2 = IP.split("\\:",-1);

    if(arrOfStr1.length==4)
    {
      for(int i=0;i<arrOfStr1.length;i++)
      {
        String str=arrOfStr1[i];
        int n=arrOfStr1[i].length();
        if(n==1 && (!(str.charAt(0)>='0' && str.charAt(0)<='9')))
        {
          return "Neither";
        }
        else if(n>=2 && n<=3)
        {
          if(!(str.charAt(0)>='1' && str.charAt(0)<='9'))return "Neither";
          int num=str.charAt(0)-'0';
          for(int j=1;j<n;j++)
          {
            if(!(str.charAt(j)>='0' && str.charAt(j)<='9'))return "Neither";
            num=num*10+(str.charAt(j)-'0');
          }
          if(num>=256)return "Neither";
        }
        else if(n>3 || n==0)return "Neither";
      }

      return "IPv4";
    }
    else if(arrOfStr2.length==8)
    {
      for(int i=0;i<arrOfStr2.length;i++)
      {
        String s=arrOfStr2[i];
        int n=arrOfStr2[i].length();
        if(n>=1 && n<=4)
        {
          for(int j=0;j<n;j++)
          {
            if(!(
                (s.charAt(j)>='0' && s.charAt(j)<='9')||
                    (s.charAt(j)>='a' && s.charAt(j)<='f')||
                    (s.charAt(j)>='A' && s.charAt(j)<='F')))
              return "Neither";
          }
        }
        else if(n>4 || n==0)return "Neither";
      }

      return "IPv6";
    }
    else return "Neither";
  }

  public String validateIPv4(String IP) {
    String[] nums = IP.split("\\.", -1);
    for (String x : nums) {
      // Validate integer in range (0, 255):
      // 1. length of chunk is between 1 and 3
      if (x.length() == 0 || x.length() > 3) {
        return "Neither";
      }
      // 2. no extra leading zeros
      if (x.charAt(0) == '0' && x.length() != 1) {
        return "Neither";
      }
      // 3. only digits are allowed
      for (char ch : x.toCharArray()) {
        if (!Character.isDigit(ch)) {
          return "Neither";
        }
      }
      // 4. less than 255
      if (Integer.parseInt(x) > 255) {
        return "Neither";
      }
    }
    return "IPv4";
  }

  public String validateIPv6(String IP) {
    String[] nums = IP.split(":", -1);
    String hexdigits = "0123456789abcdefABCDEF";
    for (String x : nums) {
      // Validate hexadecimal in range (0, 2**16):
      // 1. at least one and not more than 4 hexdigits in one chunk
      if (x.length() == 0 || x.length() > 4) {
        return "Neither";
      }
      // 2. only hexdigits are allowed: 0-9, a-f, A-F
      for (Character ch : x.toCharArray()) {
        if (hexdigits.indexOf(ch) == -1) {
          return "Neither";
        }
      }
    }
    return "IPv6";
  }

  public String validIPAddress(String IP) {
    if (IP.chars().filter(ch -> ch == '.').count() == 3) {
      return validateIPv4(IP);
    } else if (IP.chars().filter(ch -> ch == ':').count() == 7) {
      return validateIPv6(IP);
    } else {
      return "Neither";
    }
  }
}
