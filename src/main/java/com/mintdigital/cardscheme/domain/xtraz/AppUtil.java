package com.mintdigital.cardscheme.domain.xtraz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Author : Owolabi Babalola
 * Email *: babs.owolabi@gmail.com
 * date **: November 21, 2016  09:25 AM
 * -------------------------------------------------------------
 */
public enum AppUtil {


    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUtil.class);

    AppUtil() {

    }

    public final boolean stringIsNullOrEmpty(String arg) {
        if ((arg == null)) return true;
        else
            return ("".equals(arg)) || (arg.trim().length() == 0);
    }


    public static String generateReferenceNumber(String prefix){
       return prefix+"-"+System.currentTimeMillis();
    }

}
