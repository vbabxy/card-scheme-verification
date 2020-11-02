package com.mintdigital.cardscheme.domain.xtraz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * Author : Owolabi Babalola
 * Email *: babs.owolabi@gmail.com
 * date **: November 21, 2016  09:25 AM
 * -------------------------------------------------------------
 */


public enum ID {

    INSTANCE;

    private static final Random RANDOM1;
    private static final Random RANDOM2;
    private static final Random RANDOM3;
    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final long GLOBAL_PROCESS_ID;
    static Logger log = LoggerFactory.getLogger(ID.class);

    static {
        long time = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long addressHashCode;
        try {
            InetAddress inetAddress;
            inetAddress = InetAddress.getLocalHost();
            addressHashCode = inetAddress.getHostName().hashCode()
                    ^ inetAddress.getHostAddress().hashCode();
        } catch (Exception err) {
            log.warn("Unable to get local host information.", err);
            addressHashCode = ID.class.hashCode();
        }
        GLOBAL_PROCESS_ID = time ^ nanoTime ^ freeMemory ^ addressHashCode;
        RANDOM1 = new Random(time);
        RANDOM2 = new Random(nanoTime);
        RANDOM3 = new Random(addressHashCode ^ freeMemory);
    }

    private ID() {
    }



    public static long generateLong() {
        return Math.abs(RANDOM1.nextLong() ^ RANDOM2.nextLong() ^ RANDOM3.nextLong());
    }

    public static int generateInt() {
        return (int) generateLong();
    }

    public static String generateUUIDString() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString().replace("-", "").toUpperCase();


    }

    public static BigInteger generateBigInteger() {
        return BigInteger.valueOf(generateLong());
    }

    public static byte[] getMD5Bytes(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            return digest.digest(content.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String getHexString(byte[] bytes) {
        // This method cannot change even if it's wrong.
        BigInteger bigInteger = BigInteger.ZERO;
        int shift = 0;
        for (int i = bytes.length; --i >= 0; ) {
            BigInteger contrib = BigInteger.valueOf(bytes[i] & 0xFF);
            contrib = contrib.shiftLeft(shift);
            bigInteger = bigInteger.add(contrib);
            shift += 8;
        }
        return bigInteger.toString(16).toUpperCase();
    }

    /**
     * Gets a process ID that is nearly guaranteed to be globally unique.
     */
    public static long getGlobalProcessId() {
        return GLOBAL_PROCESS_ID;
    }



    public static final String generateRandomCode(int length)  {

        final String abc = "123456789";
        return generateRandomCharacters(length, abc);
    }




    public static final String generateRandomCharacters(int num, String characterSampleSpace) {


        StringBuilder generatedString = new StringBuilder();
        for (int i = 0; i < num; i++) {

            char letter = (characterSampleSpace).charAt(RANDOM2.nextInt(characterSampleSpace.length()));
            generatedString.append(letter);

        }
        log.debug("Random generatedString:- {}", generatedString);
        return String.valueOf(generatedString);

    }
    public static final String generateRandomCharacters(int num) {


        final String abc = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

       return ID.generateRandomCharacters(num, abc);

    }

    public int generateRandomNumber(int upperLimit, int lowerLimit) {

        return generateRandomInteger(lowerLimit, upperLimit, new Random());

    }


    /**
     * generates a random integer within a specified range
     *
     * @param aStart
     * @param aEnd
     * @param aRandom
     * @return
     */

    public int generateRandomInteger(int aStart, int aEnd, Random aRandom) {
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        return (int) (fraction + aStart);

    }


    public final String generateRandomCharactersForPasswd(int num) {

        final String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" +
                "!@#$%^&*()[]-+}{:?<>~";


        StringBuilder generatedString = new StringBuilder();
        for (int i = 0; i < num; i++) {
            char letter = (abc).charAt(new Random().nextInt(83));
            generatedString.append(letter);
        }
        log.debug("generateRandomCharactersForPasswd:- {}", generatedString);
        return String.valueOf(generatedString);
    }




}

