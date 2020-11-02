package com.mintdigital.cardscheme.domain.xtraz;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : Owolabi Babalola
 * Email *: babs.owolabi@gmail.com
 * date **: November 21, 2016  09:25 AM
 * -------------------------------------------------------------
 */
public enum EncDec {

    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(EncDec.class);
    private static final String PASSWORD = "zK1idhqSmHYYwScyeKvFG0ChjYp9fl";
    StandardPBEStringEncryptor stringEncryptor;

    EncDec() {
        stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setPassword(PASSWORD);
        stringEncryptor.setStringOutputType("hexadecimal");
        stringEncryptor.initialize();
    }

    private String encrypt(String valueToEncrypt) {

        String encrypted = stringEncryptor.encrypt(valueToEncrypt);
        LOGGER.debug("finished encrypting {} & encrypted value is {}", valueToEncrypt, encrypted);
        return encrypted;
    }

    private String decrypt(String valueToDecrypt) {
        try {
            String decrypted = stringEncryptor.decrypt(valueToDecrypt);
            LOGGER.debug("finished decrypting {} & decrypted value is {}", valueToDecrypt, decrypted);
            return decrypted;
        } catch (EncryptionOperationNotPossibleException e) {
            LOGGER.error("EncryptionOperationNotPossibleException just occurred " +
                    "as this value might have been formerly decrypted prior to this time.");
            return "4533-3F15-28184-A3AA5-429FC5-09DF24B-345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B345333F1528184A3AA5429FC509DF24B3";
        }
    }

    public String encryptAndEncode(String valueToEncrypt) {
        LOGGER.debug("finished encryptAndEncode {}", valueToEncrypt);
        return encrypt(valueToEncrypt);//short cct this side for speed to hasten dev

    }

    public String decodeAndDecrypt(String valueToDecrypt) {
        LOGGER.debug("finished decodeAndDecrypt {}", valueToDecrypt);
        return decrypt(valueToDecrypt);//short cct this side for speed to hasten dev

    }
}
