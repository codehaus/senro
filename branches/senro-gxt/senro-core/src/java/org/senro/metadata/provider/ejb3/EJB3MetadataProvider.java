package org.senro.metadata.provider.ejb3;

import java.lang.reflect.Method;
import java.util.Collection;

import org.senro.annotations.SessionName;
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
	public Class<?> getPropertyClass() {
		return EJB3MetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Object object) {
		if( object instanceof Method ) {
			try {
				EJB3MetadataProperty result = EJB3MetadataPropertyImpl.class.newInstance();
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Class<?> getReferenceClass() {
		return EJB3MetadataReferenceImpl.class;
	}

	public boolean supports(Object clazz) {
		return clazz instanceof Class;
	}

	public Class<?> getClassClass() {
		return EJB3MetadataClassImpl.class;
	}

	public Object getClassMetadata(Object object) {
		if( object instanceof Class ) {
			Class<?> clazz = (Class<?>) object;
			try {
				EJB3MetadataClass result = EJB3MetadataClassImpl.class.newInstance();
				
				SessionName sessionName = (SessionName) clazz.getAnnotation(SessionName.class);
				if (sessionName != null)
					result.setSessionName(sessionName.name());
				
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Class<?> getMethodClass() {
		return EJB3MetadataMethodImpl.class;
	}

	public Object getMethodMetadata(Object element) {
		return null;
	}

	public Class<?> getPackageClass() {
		return EJB3MetadataPackageImpl.class;
	}

	public Object getPackageMetadata(Object element) {
		return null;
	}

	public Collection<String> getMethods(Object element) {
		return null;
	}

	public Collection<String> getProperties(Object element) {
		return null;
	}
}
