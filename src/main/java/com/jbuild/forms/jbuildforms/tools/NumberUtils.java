package com.jbuild.forms.jbuildforms.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to handle numbers, e.g. convert String to Long, Integer, etc. Also conversion between number to percentage, money, etc.
 * 
 */
public final class NumberUtils {
	private static final Logger LOG = LoggerFactory.getLogger(NumberUtils.class);

	/** Expresses 1 Kb in bytes. */
	private static final int INT_1024 = 1024;

	/** String format '00.00'. */
	public static final String NUMBER_FORMAT_00_00 = "00.00";

	public static final String EURO_SYMBOL_UTF8 = "\u20ac";

	public NumberUtils() {
	}

	/**
	 * Formats a provided long size as MegaBytes in a specified number format. Utilizes Locale.UK for symbolizing the decimal separator.
	 * 
	 * @param sizeInBytes
	 * @param sNumberFormat
	 * @return
	 */
	public static String formatAsMegabytes(final long sizeInBytes, final String sNumberFormat) {
		String fileSize = "0";

		if (sizeInBytes != 0) {
			double sizeMb = (double) sizeInBytes / (double) (INT_1024 * INT_1024);
			if (sNumberFormat != null) {
				fileSize = (new DecimalFormat(sNumberFormat, new DecimalFormatSymbols(Locale.UK))).format(sizeMb);
			} else {
				fileSize = String.valueOf(sizeMb);
			}
		}
		return fileSize;
	}

	/**
	 * Format a {@link BigDecimal} as a String representing money in asked {@link Locale}: format=###.##
	 */
	public static String formatAsMoney(final BigDecimal value) {
		DecimalFormat df = new DecimalFormat("#.00");
		Double moneyValue = value.doubleValue();
		return " " + df.format(moneyValue);
	}

	/**
	 * Format input value as String percentage
	 * 
	 * @param value
	 *            e.g. 0.15
	 * @return e.g. 15%
	 */
	public static String formatAsPercentage(final BigDecimal value) {
		BigDecimal value2 = value.setScale(2, BigDecimal.ROUND_DOWN);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);
		return df.format(value2.multiply(new BigDecimal("100"))) + "%";
	}

	/**
	 * Check if input string is of type {@link Integer}
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean isIntegerNumeric(final String inputString) {
		boolean isInteger = true;

		try {
			if (inputString != null) {
				Integer.parseInt(inputString);
			} else {
				isInteger = false;
			}
		} catch (NumberFormatException e) {
			isInteger = false;
		}
		return isInteger;
	}

	/**
	 * Check if input string is of type {@link Long}
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean isLongNumeric(final String inputString) {
		boolean isLong = true;

		try {
			if (inputString != null) {
				Long.parseLong(inputString);
			} else {
				isLong = false;
			}
		} catch (NumberFormatException e) {
			isLong = false;
		}
		return isLong;
	}

	/**
	 * Check if input string is of type {@link Double}
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean isDoubleNumeric(final String inputString) {
		boolean isInteger = true;

		try {
			if (inputString != null) {
				Double.parseDouble(inputString);
			} else {
				isInteger = false;
			}
		} catch (NumberFormatException e) {
			isInteger = false;
		}
		return isInteger;
	}

	/**
	 * Check if value parsed as {@link Double} is strictly under a threshold
	 * 
	 * @param value
	 * @param threshold
	 * @return
	 */
	public static boolean isUnder(String value, String threshold) {
		try {
			return Double.parseDouble(value) < Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if sum of values parsed as {@link Double} is strictly under a threshold
	 * 
	 * @param value1
	 * @param value2
	 * @param threshold
	 * @return
	 */
	public static boolean isUnder(String value1, String value2, String threshold) {
		try {
			return Double.parseDouble(value1) + Double.parseDouble(value2) < Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if value parsed as {@link Double} is strictly over a threshold
	 * 
	 * @param value
	 * @param threshold
	 * @return
	 */
	public static boolean isOver(String value, String threshold) {
		try {
			return Double.parseDouble(value) > Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if sum of values parsed as {@link Double} is strictly over a threshold
	 * 
	 * @param value1
	 * @param value2
	 * @param threshold
	 * @return
	 */
	public static boolean isOver(String value1, String value2, String threshold) {
		try {
			return Double.parseDouble(value1) + Double.parseDouble(value2) > Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if sum of values parsed as {@link Double} is strictly over a threshold
	 * 
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param threshold
	 * @return
	 */
	public static boolean isOver(String value1, String value2, String value3, String threshold) {
		try {
			return Double.parseDouble(value1) + Double.parseDouble(value2) + Double.parseDouble(value3) > Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if value parsed as {@link Double} equals a threshold
	 * 
	 * @param value
	 * @param threshold
	 * @return
	 */
	public static boolean equals(String value, String threshold) {
		try {
			return Double.parseDouble(value) == Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if sum of values parsed as {@link Double} equals a threshold
	 * 
	 * @param value1
	 * @param value2
	 * @param threshold
	 * @return
	 */
	public static boolean equals(String value1, String value2, String threshold) {
		try {
			return Double.parseDouble(value1) + Double.parseDouble(value2) == Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Check if sum of values parsed as {@link Double} equals a threshold
	 * 
	 * @param value1
	 * @param value2
	 * @param value3
	 * @param threshold
	 * @return
	 */
	public static boolean equals(String value1, String value2, String value3, String threshold) {
		try {
			return Double.parseDouble(value1) + Double.parseDouble(value2) + Double.parseDouble(value3) == Double.parseDouble(threshold);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
	}

	/**
	 * Converts a string to Number
	 * 
	 * @throws NumberFormatException
	 */
	public static Double convertStringToNumber(final String value) throws Exception {
		Double result = null;
		try {
			result = Double.parseDouble(value);
		} catch (java.lang.NumberFormatException e) {
			LogUtils.logError(LOG, e, "SPACTC", "SPACTC" + " Error while calling Converting a string to Number");
		}
		return result;
	}
}