package com.jbuild.forms.jbuildforms.tools;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * Singlenton class that provides the xstream configured
 * 
 */
public final class XStreamProvider {
	private static final Logger LOG = LoggerFactory.getLogger(XStreamProvider.class);

	public static final String XSTREAM_PROPS = "xstream.properties";

	private static volatile XStreamProvider instance = null;

	private XStream xstream;

	private XStreamProvider() {
		init();
	}

	public static XStreamProvider getInstance() {
		if (instance == null) {
			instance = new XStreamProvider();
		}
		return instance;
	}

	private void init() {
		try {
			xstream = new XStream();

			Class<?>[] classes = getAliases();

			xstream.processAnnotations(classes);
		} catch (Exception ex) {
			LOG.error("XStreamProvider", ex);
		}
	}

	private Class<?>[] getAliases() {
		Class<?>[] cls = null;
		List<Class<?>> clsList = new ArrayList<Class<?>>();
		try {
			URL propertyFileUrl = getClass().getResource("/" + XSTREAM_PROPS);
			InputStream in = propertyFileUrl.openStream();
			Properties props = new Properties();
			props.load(in);
			in.close();
			for (@SuppressWarnings("rawtypes")
			Map.Entry entry : props.entrySet()) {
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				String[] classNamesStr = val.split(",");
				for (int i = 0; i < classNamesStr.length; i++) {
					String className = key + "." + classNamesStr[i].trim();
					Class<?> cl = Class.forName(className);
					clsList.add(cl);
				}
			}

			if (!clsList.isEmpty()) {
				cls = new Class<?>[clsList.size()];
				cls = clsList.toArray(cls);
			}
		} catch (Exception ex) {
			LOG.error("XStreamProvider", ex);
		}
		return cls;
	}

	/**
	 * return the XStream configured instance
	 * 
	 * @return
	 */
	public XStream getXStream() {
		return xstream;
	}

	/*
	 * WORKERS
	 */
}