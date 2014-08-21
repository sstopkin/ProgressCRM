package org.progress.crm.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import org.progress.crm.exceptions.ActionException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.ValidationException;

public class ParamUtil {

    public static BigDecimal getBigDecimal(Map<String, String> request, String name)
            throws CustomException {
        BigDecimal value;
        String param = request.get(name);
        try {
            value = new BigDecimal(param);
        } catch (NumberFormatException e) {
            throw new ValidationException(ActionException.INCORRECT_DATA_TYPE, name);
        }
        return value;
    }

    public static BigDecimal getBigDecimalOfNotEmptyParamOrZero(Map<String, String> request, String name)
            throws CustomException {
        String value = request.get(name);
        if (value.equals("")) {
            return BigDecimal.ZERO;
        }
        BigDecimal result;
        result = getBigDecimal(request, name);
        return result;
    }

    public static String getNotEmpty(Map<String, String> request, String name)
            throws CustomException {

        String value = getNotNull(request, name);

        if (value.length() == 0) {
            throw new ValidationException(ActionException.PARAM_MUST_NOT_BE_EMPTY, name);
        }
        return value;
    }

    public static String getNotEmptyIfNotNull(Map<String, String> request, String name)
            throws CustomException {

        String value = null;
        if (request.get(name) != null) {
            value = ParamUtil.getNotEmpty(request, name);
        }
        return value;
    }

    public static String getNotNull(Map<String, String> parameters, String name)
            throws CustomException {

        String value = parameters.get(name);

        if (value == null) {
            throw new ValidationException(ActionException.PARAM_MUST_NOT_BE_NULL, name);
        }
        return value;
    }

    private static Boolean parseBoolean(String value)
            throws CustomException {

        boolean result;
        if (value.equalsIgnoreCase(Boolean.TRUE.toString())) {
            result = true;
        } else {
            if (value.equalsIgnoreCase(Boolean.FALSE.toString())) {
                result = false;
            } else {
                throw new ValidationException(ActionException.INCORRECT_DATA_TYPE, "");
            }
        }

        return result;
    }

    public static Boolean getBoolean(Map<String, String> parameters, String paramName)
            throws CustomException {

        String value = parameters.get(paramName);
        if (!Validator.isEmpty(value)) {
            try {
                return parseBoolean(value);
            } catch (Exception e) {
                throw new ValidationException(ActionException.PARAM_ERROR, paramName + " " + e.getMessage());
            }
        }

        return null;
    }

    public static boolean getBoolean(Map<String, String> parameters, String paramName, boolean defaultValue)
            throws CustomException {
        String value = parameters.get(paramName);
        if (!Validator.isEmpty(value)) {
            try {
                return parseBoolean(value);
            } catch (Exception e) {
                // The default value will be returned.
            }
        }

        return defaultValue;
    }

    public static boolean getNotEmptyBoolean(Map<String, String> parameters, String paramName)
            throws CustomException {

        String value = getNotEmpty(parameters, paramName);

        boolean result;
        try {
            result = parseBoolean(value);
        } catch (Exception e) {
            throw new ValidationException(ActionException.PARAM_ERROR, paramName, e.getMessage());
        }

        return result;
    }

    public static int getNotEmptyInt(Map<String, String> request, String name)
            throws CustomException {

        int result;
        String value = getNotEmpty(request, name);

        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ValidationException(ActionException.INCORRECT_DATA_TYPE, name);
        }

        return result;

    }

    public static int getNotNegativeInt(Map<String, String> request, String name)
            throws CustomException {

        int result;
        result = getNotEmptyInt(request, name);
        if (result < 0) {
            throw new ValidationException(ActionException.INCORRECT_DATA_TYPE, name);
        }

        return result;

    }

    public static int getNotNegativeIntOrZeroIfEmpty(Map<String, String> request, String name)
            throws CustomException {

        String value = request.get(name);

        if (value == null) {
            throw new ValidationException(ActionException.PARAM_MUST_NOT_BE_NULL, name);
        }
        if (value.equals("")) {
            return 0;
        }
        return getNotNegativeInt(request, name);
    }

    public static Integer getInt(Map<String, String> request, String name)
            throws CustomException {

        Long longValue = getLong(request, name);
        if (longValue != null) {
            return (int) longValue.longValue();
        } else {
            return null;
        }
    }

    public static int getInt(Map<String, String> request, String name, int defaultValue)
            throws CustomException {
        Integer nulableIntValue = getInt(request, name);
        return nulableIntValue == null ? defaultValue : nulableIntValue.intValue();
    }

    public static Integer getIntInRange(Map<String, String> parameters, String name,
            int firstValue, int lastValue) throws CustomException {

        Integer result = getInt(parameters, name);

        if ((result != null) && ((result < firstValue) || (result > lastValue))) {
            throw new ValidationException(ActionException.INVALID_INTEGER_VALUE, name, Integer.toString(firstValue), Integer.toString(lastValue));
        }

        return result;
    }

    public static Integer getIntInRange(Map<String, String> parameters, String name,
            int firstValue, int lastValue, int defaultValue) throws CustomException {

        Integer result = getIntInRange(parameters, name, firstValue, lastValue);

        return result == null ? new Integer(defaultValue) : result;
    }

    public static Integer getNotNullIntInRange(Map<String, String> parameters, String name,
            int firstValue, int lastValue) throws CustomException {

        Integer result = getIntInRange(parameters, name, firstValue, lastValue);
        if (result == null) {
            throw new ValidationException(ActionException.INVALID_INTEGER_VALUE, name, Integer.toString(firstValue), Integer.toString(lastValue)
            );
        }
        return result;
    }

    public static Long getLong(Map<String, String> request, String name)
            throws CustomException {

        Long result = null;
        String value = request.get(name);

        if (Validator.isNotEmpty(value)) {
            try {
                result = Long.decode(value);
            } catch (NumberFormatException e) {
                throw new ValidationException(ActionException.INCORRECT_DATA_TYPE, name);
            }
        }

        return result;
    }

    public static Long getLongInRange(Map<String, String> parameters, String name,
            Long firstValue, Long lastValue) throws CustomException {

        Long result = getLong(parameters, name);

        if ((result != null) && ((result < firstValue) || (result > lastValue))) {
            throw new ValidationException(ActionException.INVALID_LONG_VALUE, name, Long.toString(firstValue), Long.toString(lastValue)
            );
        }

        return result;
    }

    private static Date parseDate(String dateStr, TimeZone memberTZ, String fieldName) throws CustomException {
        if (dateStr != null && "now".equalsIgnoreCase(dateStr)) {
            return new Date();
        }

        String format = "yyyy-MM-dd-HH:mm:ss.S";
        if ((dateStr != null)) {
            // Handle the case where there is a space after the date instead of a dash.
            if ((dateStr.length() > 10) && (dateStr.charAt(10) == ' ')) {
                format = "yyyy-MM-dd HH:mm:ss.S";
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(memberTZ);
        Date result = null;

        try {
            result = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new ValidationException("INCORRECT_DATE_FORMAT", dateStr, fieldName);
        }

        return result;
    }

    public static Double getDouble(Map<String, String> request, String name)
            throws CustomException {

        Double result = null;
        String value = request.get(name);

        if (Validator.isNotEmpty(value)) {
            try {
                result = Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new ValidationException(ActionException.INCORRECT_DATA_TYPE, name);
            }
        }

        return result;
    }

    public static Double getNotEmptyDouble(Map<String, String> request, String name)
            throws CustomException {

        getNotEmpty(request, name);
        return getDouble(request, name);
    }

}
