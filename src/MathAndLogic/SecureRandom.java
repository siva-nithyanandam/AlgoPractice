package MathAndLogic;

public class SecureRandom {

    public static void main(String[] args) {
        java.security.SecureRandom secureRandom = new java.security.SecureRandom();
        byte[] bytes = new byte[21];
        secureRandom.nextBytes(bytes);
        System.out.println(new String(bytes));
        secureRandom.nextBytes(bytes);
        System.out.println(new String(bytes));
        String randomValue = String.format("%h", bytes);
        System.out.println(randomValue);

    }
}
//6ea12c19
//6a024a67
//6a024a67
//6a024a67
//6d00a15d
//6d00a15d
//52af6cff