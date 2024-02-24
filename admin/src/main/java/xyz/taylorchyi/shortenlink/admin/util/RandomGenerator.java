package xyz.taylorchyi.shortenlink.admin.util;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUM = UPPER + LOWER + DIGITS;
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomGenerator(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static String generateRandomString(int length) {
        RandomGenerator generator = new RandomGenerator(length, new SecureRandom(), ALPHANUM);
        return generator.nextString();
    }

    public static String generateSixRandomCharString() {
        RandomGenerator generator = new RandomGenerator(6, new SecureRandom(), ALPHANUM);
        return generator.nextString();
    }
}

