package framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RandomGen {

    public static String generateRandomString(int length) {
        String chars = "_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public static BigDecimal generateRandomBigDecimal(String start, String end) {
        BigDecimal startVal = new BigDecimal(start);
        BigDecimal endVal = new BigDecimal(end);
        if (startVal.compareTo(endVal) > 0) {
            throw new IllegalArgumentException("Start must be less than or equal to end");
        }
        Random rnd = new Random();
        BigDecimal range = endVal.subtract(startVal);
        BigDecimal randomFraction = BigDecimal.valueOf(rnd.nextDouble());
        BigDecimal result = startVal.add(range.multiply(randomFraction));
        return result.setScale(Math.max(startVal.scale(), endVal.scale()), RoundingMode.HALF_UP);
    }
}
