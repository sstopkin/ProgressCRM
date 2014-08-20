package org.progress.crm.util;

import java.util.regex.Pattern;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.ValidationException;

public class Validator {

    private static final String ASCII_PATTERN = "\\A\\p{ASCII}*\\z";
    private static final Pattern ALNUM_PATTERN = Pattern.compile("^\\p{Alnum}*$");
    private static final String PRINTABLE_PATTERN = "^\\p{Print}*$";

    private static final Pattern DOMAIN_PATTERN = Pattern.compile("^[^@]+\\.[^@\\.]+$");
    private static final Pattern LOCAL_NAME_PATTERN = Pattern.compile("^[^@]+$");
    
    public static boolean isEmpty(String string) {
        return (string == null) || (string.trim().length() == 0);
    }

    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isNull(Object[] arr) {
        return (arr == null) || (arr.length == 0);
    }

    public static boolean isNotNull(Object[] arr) {
        return !isNull(arr);
    }

    public static boolean isAlphaNumeric(String string) {
        return ALNUM_PATTERN.matcher(string).matches();
    }

    public static boolean isPrintable(String string) {
        return string.matches(PRINTABLE_PATTERN);
    }

    public static boolean isASCII(String string) {
        return string.matches(ASCII_PATTERN);
    }

    public static boolean isEmail(String string) {
        return isEmail(LOCAL_NAME_PATTERN, DOMAIN_PATTERN, string);
    }

    private static boolean isEmail(Pattern localnamePat, Pattern domainPat, String email) {
        if (null == email) {
            return false;
        }

        String[] emailParts = email.split("@", -1);
        if (emailParts.length != 2) {
            return false;
        }

        String localname = emailParts[0];
        if (!localnamePat.matcher(localname).matches()) {
            return false;
        }

        String hostname = emailParts[1];
        if (!domainPat.matcher(hostname).matches()) {
            return false;
        }

        return true;
    }

    public static void validateLength(String paramName, String value) throws CustomException {
        if (value != null && value.length() > 255) {
            throw new ValidationException("VALUE_TOO_LONG", paramName);
        }
    }

    public static void validateLength(String paramName, String value, int maxLength) throws CustomException {
        if (value != null && value.length() > maxLength) {
            throw new ValidationException("VALUE_TOO_LONG", paramName);
        }
    }

    public static boolean validateUUIDString(String uuid) {
        if (uuid == null || uuid.length() == 0) {
            return false;
        }
        if (uuid.charAt(0) != 'u') {
            return false;
        }
        String baseUUID = uuid.substring(1);
        try {
            java.util.UUID.fromString(baseUUID);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    public static void validateRGBColor(String color) throws CustomException {
        int colorValue = 0;
        try {
            colorValue = Integer.parseInt(color, 16);
            if (colorValue < 0x000000 || colorValue > 0xFFFFFF) {
                throw new ValidationException("INVALID_RGB_COLOR", "");
            }
        } catch (NumberFormatException ex) {
            throw new ValidationException("INVALID_RGB_COLOR", "");
        }
    }

}
