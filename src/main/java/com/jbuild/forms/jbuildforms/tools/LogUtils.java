package com.jbuild.forms.jbuildforms.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;

public class LogUtils {
	public static void logError(Logger logger, Exception e, String appName, String text, Boolean throwException) throws Exception {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.close();
		String trace = sw.toString();
		logger.error("\r\n\t[" + appName + "] " + text + "\r\n\t" + trace);
		if (throwException) {
			throw new Exception(e);
		}
	}

	public static void logError(Logger logger, Exception e, String appName, String text) throws Exception {
		logError(logger, e, appName, text, true);
	}
}