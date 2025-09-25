package framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.Random;

public class RandomGen {

    private static final Random random = new Random();

    public static int getRandomYear(int startYear, int endYear) {
        if (startYear > endYear) throw new IllegalArgumentException("Start year must be <= end year");
        return random.nextInt(endYear - startYear + 1) + startYear;
    }

    public static int getRandomMonth() {
        return random.nextInt(12) + 1;
    }

    public static int getRandomDay(int year, int month) {
        int maxDay = YearMonth.of(year, month).lengthOfMonth();
        return random.nextInt(maxDay) + 1;
    }

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
