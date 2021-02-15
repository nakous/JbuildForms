package com.jbuild.forms.jbuildforms.tools;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.jbuild.forms.jbuildforms.exception.SystemException;

 
/**
 * Utility class to handle strings
 * 
 * @author Argyriou
 */
public final class StringUtils {
	private static final Character WILDCARD_INPUT = Character.valueOf('*');

	private static final Character WILDCARD_SQL = Character.valueOf('%');

	/** Line seperator length */
	public static final int NL_LENGTH = getLineSeparatorLength();

	/** Line Seperator */
	public static final String NL = getLineSeparator();

	/** Default line separator */
	private static final String SEPARATOR_DEFAULT = "\n";

	/** Constants to be used in different classes */
	public static final String CONTENT_DISPOSITION = "Content-Disposition";

	public static final String ATTACHMENT_FILENAME = "attachment; filename=";

	public static final String DOSSIER_ID = "dossierId";
	
	public static final String sit = "sit";

	public static final String DOCUMENT_ID = "documentId";

	private StringUtils() {
	}

	/**
	 * Compare two strings
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalStrings(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * Compare two strings (ignoring case)
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equalsWithIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	/**
	 * Check if a <code>String</code> is empty after trimming or NULL
	 * 
	 * @param str1
	 * @return
	 */
	public static boolean isEmptyOrNull(String str1) {
		return str1 == null || "".equals(str1.trim());
	}

	/**
	 * <code>translatedMessage</code> is a String containing {i}, where i=0,1,... tokens. These tokens are replaced by values found on <code>arguments</code>. The final replaced String is returned.
	 * 
	 * @param translatedMessage
	 * @param arguments
	 * @return
	 */
	public static String replaceParams(final String translatedMessage, final String[] arguments) {
		String outputString = translatedMessage;

		if (outputString != null && arguments != null && arguments.length > 0) {
			for (int k = 0; k < arguments.length; k++) {
				String argPos = "{" + k + "}";
				int argPosIndex = outputString.indexOf(argPos);

				if (argPosIndex >= 0) {
					String part1 = outputString.substring(0, argPosIndex);
					String part2 = "";
					if (argPosIndex + argPos.length() < outputString.length()) {
						part2 = outputString.substring(argPosIndex + argPos.length());
					}
					outputString = part1 + arguments[k] + part2;
				}
			}
		}
		return outputString;
	}

	/**
	 * According to the input boolean flags format the HQL criterion stored on inputSting
	 * 
	 * @param inputString
	 * @param isSpacesWildcard
	 * @param appendLeadingWildcard
	 * @param appendTrailingWildcard
	 * @return
	 */
	public static String formatHQLCriterion(final String inputString, final boolean isSpacesWildcard, final boolean appendLeadingWildcard, final boolean appendTrailingWildcard) {
		StringBuilder output = new StringBuilder(inputString.length());

		if (appendLeadingWildcard && !inputString.startsWith(WILDCARD_INPUT.toString()) && !inputString.startsWith(WILDCARD_SQL.toString())) {
			output.append(WILDCARD_SQL);
		}
		for (int i = 0; i < inputString.length(); i++) {
			char currentChar = inputString.charAt(i);
			if (currentChar == WILDCARD_INPUT.charValue()) {
				output.append(WILDCARD_SQL);
			} else if (isSpacesWildcard && Character.isWhitespace(currentChar)) {
				output.append(WILDCARD_SQL);
			} else {
				output.append(currentChar);
			}
		}
		if (appendTrailingWildcard && !inputString.endsWith(WILDCARD_INPUT.toString()) && !inputString.endsWith(WILDCARD_SQL.toString())) {
			output.append(WILDCARD_SQL);
		}
		return output.toString();
	}

	/**
	 * Split inputString on cDelimiter and return the iIndex'th token found
	 * 
	 * @param inputString
	 * @param cDelimiter
	 * @param iIndex
	 * @return
	 */
	public static String getStringPart(final String inputString, final char cDelimiter, final int iIndex) {
		String outputStringPart = "";

		if (!isEmptyOrNull(inputString)) {
			if (inputString.indexOf(cDelimiter) > 0) {
				String[] stringParts = inputString.split(String.valueOf(cDelimiter));
				if (iIndex >= 0 && iIndex < stringParts.length) {
					outputStringPart = stringParts[iIndex];
				}
			} else if (iIndex <= 0) {
				outputStringPart = inputString;
			}
		}
		return outputStringPart;
	}

	/**
	 * Create a new class of the class type stored in str (using reflection).
	 * 
	 * @param str
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromString(String str, Class<T> clazz) {
		T obj = null;
		try {
			if (!isEmptyOrNull(str)) {
				Constructor<?> c = clazz.getConstructor(new Class[] { String.class });
				obj = (T) c.newInstance(str.trim());
			}
		} catch (Exception e) {
			throw new SystemException("Class: " + clazz + " failled to initialize with parameter: " + str, e);
		}
		return obj;
	}

	/**
	 * Split str on ',', get class FQNs and return list of classes (using reflection)
	 * 
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fromDelimitedString(String str, Class<T> clazz) {
		List<T> resList = new ArrayList<T>();
		try {
			if (!isEmptyOrNull(str)) {
				Constructor<?> c = clazz.getConstructor(new Class[] { String.class });
				if (c == null) {
					throw new SystemException("Class: " + clazz + " does not have a String constructor!");
				}
				T obj = null;

				if (str.indexOf(',') == -1) {
					obj = fromString(str.trim(), clazz);
					resList.add(obj);
				} else {
					String[] strArray = str.split(",");
					for (int i = 0; i < strArray.length; i++) {
						obj = fromString(strArray[i].trim(), clazz);
						resList.add(obj);
					}
				}
			}
		} catch (Exception e) {
			throw new SystemException("exception", e);
		}
		return resList;
	}

	/**
	 * It removes from an input string, the character used for csv entries separation. This way, there is no problem of broken csv
	 * 
	 * @param inputString
	 * @param csvSeperator
	 * @return inputString with any occurrences of csvSeperator removed
	 */
	public static String makeStringCSVSafe(String inputString, String csvSeperator) {
		return inputString.replace(csvSeperator, " ");
	}

	/**
	 * Encodes string such it could be used as a value in a cell in the CSV file.
	 * Value is surrounded by double quotes "" and single occurrences of double quotes in the input are replaced with
	 * double double quotes.
	 * 
	 * @param inputString
	 * @return inputString encoded.
	 */
	public static String encodeCsvCell(String inputString) {
		return "\"" + inputString.replace("\"", "\"\"") + "\"";
	}

	/**
	 * Encode {@code inputString} in charset {@code iCharset}
	 * 
	 * @param inputString
	 * @param iCharset
	 * @return the encoded string
	 */
	public static String encodeString(final String inputString, final String iCharset) {
		String outputString = null;
		try {
			byte[] inputBytes = inputString.getBytes(iCharset);
			outputString = new String(inputBytes);
		} catch (IOException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return outputString;

	}

	/**
	 * Return the default line separator. If empty use {@code SEPARATOR_DEFAULT}
	 * 
	 * @return the default line separator
	 */
	private static String getLineSeparator() {
		String lineSeparator = System.getProperty("line.separator");
		if (StringUtils.isEmptyOrNull(lineSeparator)) {
			lineSeparator = SEPARATOR_DEFAULT;
		}
		return lineSeparator;
	}

	/**
	 * Return line separator length
	 * 
	 * @return the line separator length
	 */
	private static int getLineSeparatorLength() {
		return getLineSeparator().length();
	}

	/**
	 * Return the inputList as String delimited by iDelimiter
	 * 
	 * @param inputList
	 *            the list of strings
	 * @param iDelimiter
	 *            the delimiter character
	 * @return the delimited string
	 */
	public static String toDelimited(final List<String> inputList, final Character iDelimiter) {
		StringBuilder outBuilder = new StringBuilder();
		if (inputList != null && !inputList.isEmpty()) {
			for (int i = 0; i < inputList.size(); i++) {
				outBuilder = outBuilder.append(inputList.get(i));
				if (i != inputList.size() - 1) {
					outBuilder = outBuilder.append(iDelimiter).append(" ");
				}
			}
		}
		return outBuilder.toString();
	}

	/**
	 * Remove ascents and non-ascii chars
	 * <p>
	 * Examples:
	 * <ul>
	 * <li>Ö: O</li>
	 * <li>': '</li>
	 * <li>ç: c</li>
	 * <li>ü: u</li>
	 * <li>´:</li>
	 * 
	 * @author Argyriou
	 */
	public static String removeAccents(String text) {
		return text == null ? null : Normalizer.normalize(text, Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Checks if an array contains a value
	 * 
	 * @param input
	 * @param values
	 * @return
	 */
	public static Boolean containsValue(String input, String[] values) {
		Boolean result = false;
		for (String requestParameterValue : values) {
			if (input != null && input.trim().equals(requestParameterValue.trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * Return true if the two collection of strings overlap
	 */
	public static boolean areCollectionsOverlapped(final Collection<String> col1, final Collection<String> col2) {
		Collection<String> commonRoles = new HashSet<String>(col1);
		commonRoles.retainAll(col2);
		return !commonRoles.isEmpty();
	}

	/**
	 * Converts list of enums into list of strings
	 * 
	 * @param enumList
	 * @return
	 */
	public static <E extends Enum<E>> List<String> enumListToStringList(final List<E> enumList) {
		if (enumList == null) {
			return null;
		}

		List<String> stringList = new ArrayList<String>();

		for (Enum<?> e : enumList) {
			stringList.add(e.name());
		}

		return stringList;

	}

	/**
	 * Puts the text in upper case
	 * 
	 * @author xballiet
	 */
	public static String toUpperCase(String text) {
		return text == null ? null : text.toUpperCase();
	}

	/**
	 * Puts the text in lower case
	 * 
	 * @author xballiet
	 */
	public static String toLowerCase(String text) {
		return text == null ? null : text.toLowerCase();
	}
}