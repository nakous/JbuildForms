package com.jbuild.forms.jbuildforms.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jbuild.forms.jbuildforms.exception.SystemException;
import com.jbuild.forms.jbuildforms.model.tools.Pair;

 

 
public final class DateUtils {
	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	public static final String DATE_UTILS = "DateUtils";

	/** The 'dd/MM/yyyy' date format. */
	public static final String DD_MM_YYYY_FORMAT = "dd/MM/yyyy";

	public static final String DD_MM_YYYY_HHMMSS_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static final String HH24_MM = "HH:mm";

	public static final String DD_MMMM_YYYY_FORMAT = "dd MMMM yyyy";

	public static final String DDMMYYYY = "ddMMyyyy";

	public static final String DD_MM_YYYY_COMMA_HHMMSS_FORMAT = "dd/MM/yyyy, HH:mm:ss";

	public static final String DD_MM_YYYY_WITH_SLASHES_FORMAT = "dd/MM/yyyy";

	public static final String MM_DD_YY_WITH_SLASHES_FORMAT = "MM/dd/yy";

	public static final String D_M_Y_FORMAT = "d/M/y";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String HHMMSS = "hhmmss";

	private static final int INT_0 = 0;

	private static final int INT_1 = 1;

	private static final int INT_2 = 2;

	private static final int INT_3 = 3;

	private static final int INT_23 = 23;

	private static final int INT_59 = 59;

	public static final int[] FIRST_HOUR = toArrayInternal(new int[] { INT_0, INT_0, INT_0 });

	public static final int[] LAST_HOUR = toArrayInternal(new int[] { INT_23, INT_59, INT_59 });

	public DateUtils() {
	}

	private static final int[] toArrayInternal(final int[] iArray) {
		return iArray;
	}

	/**
	 * Checks equality of a <code>Date</code> object with a given triplet of date,
	 * month, year.
	 * 
	 * @param date
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public static boolean equalsDDMMYY(final Date date, final int day, final int month, final int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month && // months start at 0
				cal.get(Calendar.DAY_OF_MONTH) == day;
	}

	/**
	 * Compare dates only for Day, Month and Year equality
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean equalsDDMMYY(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date2);
		int yy1 = cal1.get(Calendar.YEAR);
		int mm1 = cal1.get(Calendar.MONTH); // months start at 0
		int dd1 = cal1.get(Calendar.DAY_OF_MONTH);
		return equalsDDMMYY(date1, dd1, mm1, yy1);
	}

	/**
	 * Create calendar set to date and time given
	 * 
	 * @param dateObject
	 * @param hourMinSec
	 * @return
	 */
	public static Calendar createCalendar(final Date dateObject, final int[] hourMinSec) {
		Calendar dCalendar = Calendar.getInstance();
		dCalendar.setTime(dateObject);
		setTimeInformation(dCalendar, hourMinSec);
		dCalendar.set(Calendar.MILLISECOND, INT_0);
		return dCalendar;
	}

	/**
	 * @param dateString
	 * @param dateFormat
	 * @param hourMinSec
	 * @return
	 */
	public static Calendar createCalendar(final String dateString, final String dateFormat, final int[] hourMinSec) {
		Calendar outCalendar = null;
		Date dateObject = null;
		try {
			dateObject = (new SimpleDateFormat(dateFormat)).parse(dateString);
			outCalendar = createCalendar(dateObject, hourMinSec);
		} catch (ParseException e) {
			dateObject = null;
		}
		return outCalendar;
	}

	/**
	 * Set input calendar to time given
	 * 
	 * @param dCalendar
	 * @param hourMinSec format: [{@link Calendar#HOUR_OF_DAY},
	 *                   {@link Calendar#MINUTE}, {@link Calendar#SECOND}]
	 */
	private static void setTimeInformation(final Calendar dCalendar, final int[] hourMinSec) {
		if (hourMinSec != null && hourMinSec.length >= INT_1) {
			dCalendar.set(Calendar.HOUR_OF_DAY, hourMinSec[INT_0]);
			if (hourMinSec.length >= INT_2) {
				dCalendar.set(Calendar.MINUTE, hourMinSec[INT_1]);
				if (hourMinSec.length >= INT_3) {
					dCalendar.set(Calendar.SECOND, hourMinSec[INT_2]);
				}
			}
		}
	}

	/**
	 * Copy date. This is used for security reasons (safe-copy of dates).
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateCopy(Date date) {
		Date result = null;
		if (date != null) {
			result = new Date(date.getTime());
		}
		return result;
	}

	/**
	 * Check if date2 - date1 is less than a year
	 * 
	 * @param date1
	 * @param date2
	 * @return result=true if date2 - date1 is less than a year
	 * @author dglyk
	 */
	public static boolean isDateDifferenceLessThanOneYear(Date date1, Date date2) {
		boolean result = true;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int yy1 = cal1.get(Calendar.YEAR);
		int yy2 = cal2.get(Calendar.YEAR);
		int difference = yy2 - yy1;
		if (difference > 1) {
			result = false;
		}
		return result;
	}

	/**
	 * Find the months between two given dates. Dates must not be null.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @author Argyriou
	 */
	public static final int getMonthsDifference(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int m1 = cal1.get(Calendar.YEAR) * 12 + cal1.get(Calendar.MONTH);
		int m2 = cal2.get(Calendar.YEAR) * 12 + cal2.get(Calendar.MONTH);
		return m2 - m1 + 1;
	}

	/** If date is null it returns false */
	public static boolean isDateBeforeNMonthFromNow(final Date date, int nMonths) {
		boolean isDateBefore = false;
		if (date == null) {
			isDateBefore = false;
		} else {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(new Date(date.getTime()));

			Calendar nMonthsAgoCalendar = new GregorianCalendar();
			nMonthsAgoCalendar.setTime(new Date());
			nMonthsAgoCalendar.add(Calendar.MONTH, -nMonths);

			isDateBefore = calendar.before(nMonthsAgoCalendar);
		}
		return isDateBefore;
	}

	/** If date is null it returns false */
	public static boolean isDateBeforeNYearsFromNow(final Date date, int nYears) {
		boolean isDateBefore = false;
		if (date == null) {
			isDateBefore = false;
		} else {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(new Date(date.getTime()));

			Calendar nYearsAgoCalendar = new GregorianCalendar();
			nYearsAgoCalendar.setTime(new Date());
			nYearsAgoCalendar.add(Calendar.YEAR, -nYears);

			isDateBefore = calendar.before(nYearsAgoCalendar);
		}
		return isDateBefore;
	}

	/**
	 * Check if dateB is at least n months after dateA.(dateA + nMonths &lt; dateB).
	 * 
	 * @param dateA
	 * @param dateB
	 * @param nMonths
	 * @return true if dateB is at least n months after dateA.(dateA + nMonths &lt;
	 *         dateB). If one or both dates are null returns false.
	 * @author dglyk
	 */
	public static boolean isDateBNMonthAfterDateA(Date dateA, Date dateB, int nMonths) {
		boolean isDateAfter = false;
		if ((dateA == null) || (dateB == null)) {
			isDateAfter = false;
		} else {
			Calendar calendarA = GregorianCalendar.getInstance();
			calendarA.setTime(dateA);

			// add nMonths to DateA: ex: dateA = dateA + 3months
			Calendar nMonthsAfterCalendarA = new GregorianCalendar();
			nMonthsAfterCalendarA.setTime(dateA);
			nMonthsAfterCalendarA.add(Calendar.MONTH, nMonths);

			Calendar calendarB = GregorianCalendar.getInstance();
			calendarB.setTime(dateB);

			// (dateA + 3 months) < dateB => true
			isDateAfter = nMonthsAfterCalendarA.before(calendarB);
		}
		return isDateAfter;
	}

	/**
	 * Format date as dd/MM/yyyy
	 * 
	 * @param date
	 * @return a string containing the date formatted as dd/MM/yyyy
	 * @author dglyk
	 */
	public static String formatDateddMMyyy(Date date) {
		return (new SimpleDateFormat(DD_MM_YYYY_FORMAT)).format(date);
	}

	/**
	 * Format date as ddMMyyyy
	 * 
	 * @param date
	 * @return a string containing the date formatted as ddMMyyyy
	 * @author dglyk
	 */
	public static String formatInDDMMYYYYNoSpaces(Date date) {
		return (new SimpleDateFormat(DDMMYYYY)).format(date);
	}

	/**
	 * Get current time
	 * 
	 * @return The current time.
	 * @author path
	 */
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Shift input date on future by iDays days
	 * 
	 * @param calendarDate
	 * @param iYears
	 * @return the shifted future date
	 */
	public static Date shiftDateByDays(final Date calendarDate, int iDays) {
		Calendar nCalendar = Calendar.getInstance();
		nCalendar.setTime(calendarDate);
		nCalendar.add(Calendar.DATE, iDays);
		return nCalendar.getTime();
	}

	/**
	 * Shift input date on future by nHours hours
	 * 
	 * @param calendarDate
	 * @param nHours
	 * @return the new Date shifted by nHours hours
	 */
	public static Date shiftDateByHours(final Date calendarDate, int nHours) {
		Calendar nCalendar = Calendar.getInstance();
		nCalendar.setTime(calendarDate);
		nCalendar.add(Calendar.HOUR, nHours);
		return nCalendar.getTime();
	}

	/**
	 * Shift input date on future by <code>months</code> months
	 * 
	 * @param inputDate
	 * @param months
	 * @return new Date shifted by <code>months</code> months
	 */
	public static Date shiftDateByMonths(Date inputDate, int months) {
		Calendar nCalendar = Calendar.getInstance();
		nCalendar.setTime(inputDate);
		nCalendar.add(Calendar.MONTH, months);
		return nCalendar.getTime();
	}

	/**
	 * Shift input date on future by <code>iSeconds</code> seconds
	 * 
	 * @param calendarDate
	 * @param iSeconds
	 * @return new Date shifted by <code>iSeconds</code> seconds
	 */
	public static Date shiftDateBySeconds(final Date calendarDate, int iSeconds) {
		Calendar nCalendar = Calendar.getInstance();
		nCalendar.setTime(calendarDate);
		nCalendar.add(Calendar.SECOND, iSeconds);
		return nCalendar.getTime();
	}

	/**
	 * Convert a string formated in ISO8601 format (.e.g 2172-12-25T00:00:00+02:15)
	 * into {@link Date}
	 * <p>
	 * Returns null if dateStr is empty
	 * 
	 * @throws ParseException
	 * @author Argyriou
	 */
	public static Date str2Iso8601(String dateStr) throws ParseException {
		Date date = null;
		if (!StringUtils.isEmptyOrNull(dateStr)) {
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			df.setTimeZone(tz);
			date = df.parse(dateStr);
		}
		return date;
	}

	/**
	 * Convert input date to ISO 86012 format (i.e. yyyy-MM-dd'T'HH:mm)
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String iso86012Str(Date date) throws ParseException {
		return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")).format(date);
	}

	/**
	 * Convert a {@link XMLGregorianCalendar} into a {@Date}. Useful e.g. on web
	 * services; by default web services return date in GregorianCalendar format.
	 * 
	 * @author Argyriou
	 */
	public static Date xmlGregorianCalendar2date(XMLGregorianCalendar xgcal) {
		return xgcal.toGregorianCalendar().getTime();
	}

	/**
	 * Parse input date according to input date format an return the created date.
	 * 
	 * @param dateStr
	 * @param dateFormat
	 * @return
	 */
	public static Date parseDate(String dateStr, DateFormat dateFormat) {
		Date date = null;
		if (dateStr != null && !"".equals(dateStr)) {
			try {
				date = dateFormat.parse(dateStr);
			} catch (ParseException e) {
				throw new SystemException("parse.exception", e);
			}
		}
		return date;
	}

	/**
	 * Gets a date in String and returns a date with format.
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static Date parseDate(String date, String dateFormat) {
		Date result = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			Date convertedDate = simpleDateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(convertedDate);
			result = cal.getTime();
		} catch (Exception e) {
			String errorMessage = "Could not parse date string " + date + " in format " + dateFormat;
			LOG.error(errorMessage, e);
			throw new SystemException(errorMessage, e);
		}
		return result;
	}

	/**
	 * Takes a date and returns the String representation with the specified date
	 * format
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String getDateAsStringWithFormat(Date date, String dateFormat) {
		return (new SimpleDateFormat(dateFormat)).format(date);
	}

	/**
	 * Gets a date in String and returns a date in String with a number of days
	 * added.
	 * 
	 * @param date
	 * @param dateFormat
	 * @param daysToAdd  can be negative if you want to subtract days
	 * @return
	 */
	public static String addDays(String date, String dateFormat, Integer daysToAdd) {
		String result = "date";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			Date convertedDate = simpleDateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(convertedDate);
			cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
			result = simpleDateFormat.format(cal.getTime());
		} catch (Exception e) {
			throw new SystemException("Probably input string is not in this date format.", e);
		}
		return result;
	}

	/**
	 * Produces a list of HH:mm strings for start to end time, based on hour/minute
	 * etc interval. ex: 9:00,9:30,10:00,10:30,11:00 if d1/d2 are null, today and
	 * tomorrow are set as dates ...
	 * 
	 * @param d1
	 * @param d2
	 * @param hourInterval
	 * @param minuteInterval
	 * @return
	 */
	public static Set<String> getHourMinuteListForInterval(Date d1, Date d2, int calendarType, int interval) {
		Set<String> result = new TreeSet<String>();
		try {
			SimpleDateFormat sdfHourMinute = new SimpleDateFormat(DateUtils.HH24_MM);

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(d2);

			String time = sdfHourMinute.format(d1);
			result.add(time);
			boolean loop = true;
			while (loop) {
				cal1.add(calendarType, interval);

				loop = cal2.getTime().compareTo(cal1.getTime()) >= 0;
				if (loop) {
					time = sdfHourMinute.format(cal1.getTime());
					result.add(time);
				} else {
					time = sdfHourMinute.format(d2);
					result.add(time);
				}
			}
		} catch (Exception ex) {
			LOG.error(DATE_UTILS, ex);
		}
		return result;
	}

	/**
	 * Produces a day from a date string and an hours:minutes string
	 * 
	 * @param strDate
	 * @param strHourMinute
	 * @return
	 */
	public static Date getDateWithHoursMinutes(String strDate, String strHourMinute) {
		Date date = null;
		try {
			if (strDate == null)
				return null;
			SimpleDateFormat sdfDate = new SimpleDateFormat(DateUtils.DD_MM_YYYY_FORMAT);

			String strTimeFromHour = strHourMinute.split(":")[0];
			String strTimeFromMinute = strHourMinute.split(":")[1];

			Calendar calDate = Calendar.getInstance();
			calDate.setTime(sdfDate.parse(strDate));

			calDate.set(Calendar.HOUR_OF_DAY, Integer.valueOf(strTimeFromHour));
			calDate.set(Calendar.MINUTE, Integer.valueOf(strTimeFromMinute));

			date = calDate.getTime();
		} catch (Exception ex) {
			LOG.error(DATE_UTILS, ex);
		}
		return date;
	}

	
	/**
	 * Produces a day from a date string 
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date getDateFromString(String strDate) {
		Date date = null;
		try {
			if (strDate == null)
				return null;
			SimpleDateFormat sdfDate = new SimpleDateFormat(DateUtils.DD_MM_YYYY_FORMAT);

			date = sdfDate.parse(strDate);
		} catch (Exception ex) {
			LOG.error(DATE_UTILS, ex);
		}
		return date;
	}
	/**
	 * Produces an hours:minutes string from a day
	 * 
	 * @param date
	 * @return
	 */
	public static String getHoursMinutesFromDate(Date date) {
		String result = null;
		if (date != null) {
			SimpleDateFormat sdfHoursMinutes = new SimpleDateFormat(DateUtils.HH24_MM);
			result = sdfHoursMinutes.format(date);
		}
		return result;
	}

	/**
	 * Produces a calendar object from a date object
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * Checks if date is in the future.
	 * 
	 * @param date
	 * @return
	 */
	public static Boolean isDateInTheFuture(Date date) {
		Date currentDate = new Date();
		Calendar currentCalendar = dateToCalendar(currentDate);
		Calendar inputCalendar = dateToCalendar(date);
		return inputCalendar.after(currentCalendar);
	}

	/**
	 * Check if input date is in the past (from now)
	 * 
	 * @param date
	 * @return
	 * @author dglyk
	 */
	public static Boolean isDateInThePast(Date date) {
		Date currentDate = new Date();
		Calendar currentCalendar = dateToCalendar(currentDate);
		Calendar dateToTest = dateToCalendar(date);
		return dateToTest.before(currentCalendar);
	}

	/**
	 * Checks if date1 is one month before date2
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isDate1AMonthEarlierOfDate2(Date date, Date date2) {
		Calendar date1Calendar = dateToCalendar(date);
		Calendar date2Calendar = dateToCalendar(date2);
		date2Calendar.add(Calendar.MONTH, -1);
		return date1Calendar.before(date2Calendar);
	}

	/**
	 * Format input date into String using the format DD_MMMM_YYYY
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateWholeMonthName(Date date) {
		return (new SimpleDateFormat(DD_MMMM_YYYY_FORMAT)).format(date);
	}

	/**
	 * Returns true, if date1 is before date2
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isDate1BeforeDate2(Date date1, Date date2) {
		Calendar cal1 = dateToCalendar(date1);
		Calendar cal2 = dateToCalendar(date2);
		return cal1.before(cal2);
	}

	/**
	 * Returns true, if date1 > date2
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isDate1AfterDate2(Date date1, Date date2) {
		Calendar cal1 = dateToCalendar(date1);
		Calendar cal2 = dateToCalendar(date2);
		return cal1.after(cal2);
	}

	/**
	 * Format input date into the input date format
	 * 
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String formatDateInDateFormat(Date date, String dateFormat) {
		return (new SimpleDateFormat(dateFormat)).format(date);
	}

	/**
	 * Get a new date with time set to the end of the input {@link date}. End of
	 * date is on 23:59:59.999.
	 * 
	 * @param date
	 * @return new date
	 */
	public static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Get a new date with time set to the start of the input {@link date}. End of
	 * date is on 0:0:0.0.
	 * 
	 * @param date
	 * @return new date
	 */
	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Sets to date1 the time of date2 (HH:MM:SS)
	 * 
	 * @param date1
	 * @param date2
	 * @return date1 with date2 time (HH:MM:SS)
	 */
	public static Date setToDate1TheTimeOfDayOfDate2(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		cal1.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal1.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		cal1.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
		return cal1.getTime();
	}

	/**
	 * Sets to date a time given in format HH:MM(ex: 22:24) returns the same date,
	 * with the new time.
	 */
	public static Date setTimeHHMMToDate(Date date, String time) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat(HH24_MM);
		Date newTime = new Date();
		try {
			newTime = sdf.parse(time);
		} catch (ParseException e) {
		}
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(newTime);
		cal1.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
		cal1.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
		cal1.set(Calendar.SECOND, cal2.get(Calendar.SECOND));
		return cal1.getTime();
	}

	/**
	 * Removes time from the specified date
	 * 
	 * @param date
	 * @return
	 */
	public static Date excludeTimeFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Check if inputDate is in [lowerDate, upperDate]
	 * 
	 * @param inputDate the date to check
	 * @param lowerDate the lower end date
	 * @param upperDate the upper end date
	 * @return
	 */
	public static Boolean isDateBetweenTwoDates(Date inputDate, Date lowerDate, Date upperDate) {
		Boolean result = false;

		Calendar inputCalendar = Calendar.getInstance();
		inputCalendar.setTime(inputDate);

		Calendar lowerCalendar = Calendar.getInstance();
		lowerCalendar.setTime(lowerDate);

		Calendar upperCalendar = Calendar.getInstance();
		upperCalendar.setTime(upperDate);

		if ((lowerCalendar.before(inputCalendar) || lowerCalendar.equals(inputCalendar))
				&& (upperCalendar.after(inputCalendar) || upperCalendar.equals(inputCalendar))) {
			result = true;
		}
		return result;
	}

	/**
	 * Returns the Beginning of the Next day. Mostly used for sql queries so as to
	 * include current day.
	 * 
	 * @param newDate
	 * @return returns the Beginning of the Next day
	 */
	public static Date getStartOfNextDay(Date date) {
		Date newDate = date;
		Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		cal.add(Calendar.DATE, +1);
		newDate = cal.getTime();
		newDate = excludeTimeFromDate(newDate);
		return newDate;
	}

	/**
	 * Find max date from the dates string-formated in the given collection
	 * {@code dates}. string-formated dates should be in the date format
	 * {@link DD_MM_YYYY_WITH_SLASHES_FORMAT}.
	 * 
	 * @param dates
	 * @return max date
	 */
	// CHANGE REQUEST: SPRINT 4: 4.4. [FCA.5] Book slot for signature
	public static Date findMaxDateFromAStringListOfDates(List<String> dates) {
		Date date = null;
		if (!dates.isEmpty()) {
			date = DateUtils.parseDate(dates.get(0), DateUtils.DD_MM_YYYY_WITH_SLASHES_FORMAT);
			for (String dateStr : dates) {
				Date d = DateUtils.parseDate(dateStr, DateUtils.DD_MM_YYYY_WITH_SLASHES_FORMAT);
				if (DateUtils.isDate1BeforeDate2(date, d)) {
					date = d;
				}
			}
		}
		return date;
	}

	/**
	 * Check if day after input date is weekend (i.e. if date is Friday or Saturday)
	 * 
	 * @param date date
	 * @return if day after date is weekend
	 */
	public static Boolean isWeekend(Date date) {
		Boolean result = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.FRIDAY) { // If it's Friday so skip to Monday
			result = true;
		} else if (dayOfWeek == Calendar.SATURDAY) { // If it's Saturday skip to Monday
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Check if day input date is Saturday or Sunday (i.e. if date is Friday or
	 * Saturday)
	 * 
	 * @param date date
	 * @return if day after date is weekend
	 */
	public static Boolean isSaturdayOrSunday(Date date) {
		Boolean result = false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SATURDAY) {
			result = true;
		} else if (dayOfWeek == Calendar.SUNDAY) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * Return the 1st day after {@link date} after skipping weekend.
	 * 
	 * @param date
	 * @return
	 */
	public static Date skipWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.FRIDAY) { // If it's Friday so skip to Monday
			cal.add(Calendar.DATE, 3);
		} else if (dayOfWeek == Calendar.SATURDAY) { // If it's Saturday skip to Monday
			cal.add(Calendar.DATE, 2);
		} else {
			cal.add(Calendar.DATE, 1);
		}
		return cal.getTime();
	}

	public static List<Pair<Date, Date>> mergeOverlappingPeriods(List<Pair<Date, Date>> periods) {
		if (periods.size() < 2)
			return periods;

		List<Pair<Date, Date>> mergedPeriods = new ArrayList<Pair<Date, Date>>();

		for (int i = 0; i < periods.size(); ++i) {
			boolean isOverlap = false;
			for (int j = i + 1; j < periods.size(); ++j) {
				if (isOverlap(periods.get(i), periods.get(j))) {
					isOverlap = true;
					Pair<Date, Date> merged = mergeOverlappingPeriods(periods.get(i), periods.get(j));
					mergedPeriods.add(merged);
				}
			}
			if (!isOverlap)
				mergedPeriods.add(periods.get(i));
		}
		return mergedPeriods;
	}

	public static Pair<Date, Date> mergeOverlappingPeriods(Pair<Date, Date> l, Pair<Date, Date> r) {
		Date start = isDate1BeforeDate2(l.getL(), r.getL()) ? l.getL() : r.getL();
		Date end = isDate1AfterDate2(l.getR(), r.getR()) ? l.getR() : r.getR();
		return new Pair<Date, Date>(start, end);
	}

	public static boolean isOverlap(Pair<Date, Date> l, Pair<Date, Date> r) {
		// return l.getL() <= r.getR() && r.getL() <= l.getR();
		return isDate1BeforeDate2(l.getL(), r.getR()) && isDate1BeforeDate2(r.getL(), l.getR());
	}

	public static long numberOfDaysBetweenTwoDates(final Date dateStart, final Date dateEnd) {
		Calendar startDate = dateToCalendar(getStartOfDay(dateStart));
		Calendar endDate = dateToCalendar(getStartOfDay(dateEnd));
		long diff = endDate.getTimeInMillis() - startDate.getTimeInMillis();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public final static Date parseStringToDate(String s) {
		Date date = null;
		if (s != null && !s.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				date = sdf.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
				date = null;
			}
		}
		return date;

	}

	public static LocalDate asLocalDate(String date) throws Exception {
		return asLocalDate(parseStringToDate(date), ZoneId.systemDefault());
	}

	public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
		if (date == null)
			return null;

		if (date instanceof java.sql.Date)
			return ((java.sql.Date) date).toLocalDate();
		else
			return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
	}
	public static boolean  isLesserMonth(String startDate, String endDate, long nbMonth) throws Exception {
		if(startDate!=null && endDate!=null  && nbMonth<=ChronoUnit.MONTHS.between(asLocalDate(startDate), asLocalDate(endDate))) {
			return true;
		}else {
			return false;
		}
	}
	
}