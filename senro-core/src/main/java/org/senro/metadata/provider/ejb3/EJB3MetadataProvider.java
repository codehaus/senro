package org.senro.metadata.provider.ejb3;

import java.lang.reflect.Method;

import org.senro.annotations.Label;
import org.senro.annotations.Length;
import org.senro.annotations.ListOrderNo;
import org.senro.annotations.OrderNo;
import org.senro.annotations.ReadOnly;
import org.senro.annotations.Required;
import org.senro.annotations.SessionName;
import org.senro.annotations.Unchangeable;
import org.senro.annotations.VisibleDetail;
import org.senro.annotations.VisibleLength;
import org.senro.annotations.VisibleList;
import org.senro.annotations.VisibleReferred;
import org.senro.annotations.Widget;
import org.senro.annotations.WidgetValues;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.ejb3.impl.EJB3MetadataClassImpl;
import org.senro.metadata.provider.ejb3.impl.EJB3MetadataMethodImpl;
import org.senro.metadata.provider.ejb3.impl.EJB3MetadataPackageImpl;
import org.senro.metadata.provider.ejb3.impl.EJB3MetadataPropertyImpl;
import org.senro.metadata.provider.ejb3.impl.EJB3MetadataReferenceImpl;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class EJB3MetadataProvider implements MetadataProvider {
	public Class getPropertyClass() {
		return EJB3MetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Method element) {
		assert element.getName().startsWith("get");
		EJB3MetadataProperty result = null;
		try {
			result = EJB3MetadataPropertyImpl.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Class getReferenceClass() {
		return EJB3MetadataReferenceImpl.class;
	}

	public boolean supports(Class clazz) {
		return true;
	}

	public Class getClassClass() {
		return EJB3MetadataClassImpl.class;
	}

	public Object getClassMetadata(Class clazz) {
		EJB3MetadataClass result = null;

		try {
			result = EJB3MetadataClassImpl.class.newInstance();

			SessionName sessionName = (SessionName) clazz.getAnnotation(SessionName.class);
			if (sessionName != null)
				result.setSessionName(sessionName.name());

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Class getMethodClass() {
		return EJB3MetadataMethodImpl.class;
	}

	public Object getMethodMetadata(Method element) {
		return null;
	}

	public Class getPackageClass() {
		return EJB3MetadataPackageImpl.class;
	}

	public Object getPackageMetadata(Package element) {
		return null;
	}
}
