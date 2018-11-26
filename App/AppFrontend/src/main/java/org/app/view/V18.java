package org.app.view;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.app.controler.SettingsService;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vaadin.cdi.annotation.VaadinServiceScoped;
import com.vaadin.cdi.annotation.VaadinSessionScoped;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.flow.i18n.I18NProvider;

@VaadinSessionScoped
public class V18 implements I18NProvider {

	@Inject
	SettingsService settings;

	private static final long serialVersionUID = 1L;

	private static final String BUNDLE_PREFIX = "translate";

	private final Locale LOCALE_FI = new Locale("fi", "FI");
	private final Locale LOCALE_EN = new Locale("en", "GB");

	private List<Locale> locales = Collections.unmodifiableList(Arrays.asList(LOCALE_FI, LOCALE_EN));
	
	private Locale loc;

	private static final LoadingCache<Locale, ResourceBundle> bundleCache = CacheBuilder.newBuilder()
			.expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<Locale, ResourceBundle>() {

				public ResourceBundle load(final Locale key) throws Exception {
					return initializeBundle(key);
				}
			});
	
	@PostConstruct
    void init() {
		loc = settings.getLocale();
	}

	@Override
	public List<Locale> getProvidedLocales() {
		return locales;
	}
	

	public String getTranslation(String key) {
		return getTranslation(key, loc);
		
	}

	@Override
	public String getTranslation(String key, Locale locale, Object... params) {
		if (key == null) {
			LoggerFactory.getLogger(V18.class.getName()).warn("Got lang request for key with null value!");
			return "";
		}

		final ResourceBundle bundle = bundleCache.getUnchecked(locale);

		String value;
		try {
			value = bundle.getString(key);
		} catch (final MissingResourceException e) {
			LoggerFactory.getLogger(V18.class.getName()).warn("Missing resource", e);
			return "!" + locale.getLanguage() + ": " + key;
		}
		if (params.length > 0) {
			value = MessageFormat.format(value, params);
		}
		return value;
	}

	private static ResourceBundle initializeBundle(final Locale locale) {
		return readProperties(locale);
	}

	protected static ResourceBundle readProperties(final Locale locale) {
		final ClassLoader cl = V18.class.getClassLoader();

		ResourceBundle propertiesBundle = null;
		try {
			propertiesBundle = ResourceBundle.getBundle(BUNDLE_PREFIX, locale, cl);
		} catch (final MissingResourceException e) {
			LoggerFactory.getLogger(V18.class.getName()).warn("Missing resource", e);
		}
		return propertiesBundle;
	}
}