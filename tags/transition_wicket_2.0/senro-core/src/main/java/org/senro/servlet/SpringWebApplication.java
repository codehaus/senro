package org.senro.servlet;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import wicket.Application;
import wicket.extensions.proxy.LazyInitProxyFactory;
import wicket.protocol.http.WebApplication;
import wicket.spring.ISpringContextLocator;
import wicket.spring.SpringBeanLocator;

public abstract class SpringWebApplication extends WebApplication implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	protected void internalInit() {
		super.internalInit();

		if (applicationContext == null) {
			// this application was not created as a spring bean so we
			// locate the app context from servlet context

			ServletContext sc = getWicketFilter().getFilterConfig()
					.getServletContext();
			applicationContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);

		}
	}

	public final void setApplicationContext(
			ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * Retrieves the spring application context associated with this application
	 * object
	 *
	 * This method is protected and named internalGetApplicationContext so that
	 * the subclass can choose whether or not to add a public
	 * getApplicationContext() method
	 *
	 * @return spring application context
	 */
	protected final ApplicationContext internalGetApplicationContext() {
		return applicationContext;
	}

	private final static ISpringContextLocator contextLocator = new ISpringContextLocator() {

		public ApplicationContext getSpringContext() {
			Application app = Application.get();
			return ((SenroApplication) app).internalGetApplicationContext();
		}
	};

	/**
	 * Creates a proxy for a spring bean that is safe to put into session and
	 * serialize
	 *
	 * @param clazz
	 *            class of spring bean
	 * @param beanName
	 *            name of spring bean
	 * @return proxy representing the spring bean
	 */
	protected Object createSpringBeanProxy(Class clazz, String beanName) {
		return LazyInitProxyFactory.createProxy(clazz, new SpringBeanLocator(
				beanName, clazz, getSpringContextLocator()));
	}

	/**
	 * Retrieves the spring application context locator object
	 *
	 * @return spring application context locator object
	 */
	public ISpringContextLocator getSpringContextLocator() {
		return contextLocator;
	}

}
