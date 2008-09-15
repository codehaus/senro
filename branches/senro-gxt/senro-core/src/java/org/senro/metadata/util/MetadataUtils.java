package org.senro.metadata.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.senro.metadata.Metadata;
import org.senro.metadata.MetadataManager;
import org.senro.metadata.exception.NoMetadataFoundException;

/**
 * 
 * @author Flavius Burca
 *
 */
public class MetadataUtils {
	public static Map<String, String> convert( Map<String, Object> metadataMap ) {
		Map<String, String> result = new HashMap<String, String>();
		for( String key : metadataMap.keySet() ) {
			Object value = metadataMap.get(key);
			String strValue = ConvertUtils.convert(value);
			if( value instanceof Class )
				strValue = ((Class<?>)value).getName();
			result.put(key, strValue);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Metadata findMetadata( String typeName, MetadataManager metadataManager ) {
		Metadata result = null;
		try {
			Iterator<Class<?>> typesSet = ((Collection<Class<?>>) PropertyUtils.getProperty(metadataManager, "types")).iterator();
			while (typesSet.hasNext()) {
				Class<?> aClass = typesSet.next();
				if( aClass.getName().equals(typeName) ) {
					result = metadataManager.getMetadata(aClass);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int getSize(Metadata metadata, int MAX_SIZE) {
		int length = 0;

		if (  MetadataAccessor.readMetadataInfo(metadata, "visibleLength", Instance.INTEGER) > 0) {
			length = MetadataAccessor.readMetadataInfo(metadata, "visibleLength", Instance.INTEGER);
		}
		else if ( MetadataAccessor.readMetadataInfo(metadata, "length", Instance.INTEGER) > 0 ) {
			length = MetadataAccessor.readMetadataInfo(metadata, "length", Instance.INTEGER);
		}

		return (length > MAX_SIZE ? MAX_SIZE : length);
	}

	public static int getOrderNo(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "orderNo", Instance.INTEGER);
	}

	public static String getSessionBeanName(Metadata classMetadata) {
		return MetadataAccessor.readMetadataInfo(classMetadata, "sessionName", Instance.STRING);
	}

	public static String getName(Metadata metadata) {
		return MetadataAccessor.readMetadataInfo(metadata, "name", Instance.STRING);
	}

	public static Class<?> getType(Metadata metadata) {
		return MetadataAccessor.readMetadataInfo(metadata, "type", Instance.CLASS);
	}

	public static Class<?> getDeclaringClass(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "declaringClass", Instance.CLASS);
	}

	public static Class<?> getTargetEntity(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "targetEntity", Instance.CLASS);
	}

	public static boolean isIdentifier(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "identifier", Instance.BOOLEAN);
	}

	public static boolean isVisibleReferred(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "visibleReferred", Instance.BOOLEAN);
	}

	public static boolean isVisibleList(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "visibleList", Instance.BOOLEAN);
	}

	public static boolean isVisibleDetail(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "visibleDetail", Instance.BOOLEAN);
	}

	public static boolean isOneToMany(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "oneToMany", Instance.BOOLEAN);
	}

	public static boolean isManyToOne(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "manyToOne", Instance.BOOLEAN);
	}

	public static boolean isRequired(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "required", Instance.BOOLEAN);
	}

	public static final String WIDGET_CK = "CK";
	public static final String WIDGET_CB = "CB";
	public static final String WIDGET_TA = "TA";
	public static String getWidget(Metadata propertyMetadata) {
		String widget = MetadataAccessor.readMetadataInfo(propertyMetadata, "widget", Instance.STRING);
		widget = StringUtils.trimToEmpty(widget).toUpperCase();

		if (widget.equalsIgnoreCase(WIDGET_CK))
			return WIDGET_CK;
		else if (widget.equalsIgnoreCase(WIDGET_CB))
			return WIDGET_CB;
		else if (widget.equalsIgnoreCase(WIDGET_TA))
			return WIDGET_TA;
		else
			return widget;
	}

	public static Map<String,String> getWidgetValues(Metadata propertyMetadata) {
		String widgetValues = MetadataAccessor.readMetadataInfo(propertyMetadata, "widgetValues", Instance.STRING);
		Map<String,String> result = new HashMap<String,String>();
		widgetValues = StringUtils.trimToEmpty(widgetValues);
		String[] pieces = StringUtils.split(widgetValues, ';');

		if (pieces != null) {
			List<String> labelValuePairs = Arrays.asList(pieces);
			for (String labelValuePair : labelValuePairs) {
				String[] bits = StringUtils.split(labelValuePair,'|');
				if (bits != null && bits.length == 2) {
					result.put(bits[0], bits[1]);
				}
			}
		}

		return result;
	}

	public static List<Metadata> getVisibleDetailFields(Metadata classMetadata, MetadataManager manager) {
		List<Metadata> visibleReferred = new ArrayList<Metadata>();
		try {
			for (Method visField : classMetadata.getProperties() ) {
				Metadata meta = manager.getMetadata(visField);
				if ( isVisibleDetail(meta) )
					visibleReferred.add(meta);
			}
		} catch (NoMetadataFoundException e) {}

		return visibleReferred;
	}
	
	public static List<Metadata> getVisibleListFields(Metadata classMetadata, MetadataManager manager) {
		List<Metadata> visibleReferred = new ArrayList<Metadata>();
		try {
			for (Method visField : classMetadata.getProperties() ) {
				Metadata meta = manager.getMetadata(visField);
				if ( isVisibleList(meta) )
					visibleReferred.add(meta);
			}
		} catch (NoMetadataFoundException e) {}

		return visibleReferred;
	}
	
	public static List<Metadata> getVisibleReferredFields(Metadata classMetadata, MetadataManager manager) {
		List<Metadata> visibleReferred = new ArrayList<Metadata>();
		try {
			for (Method visField : classMetadata.getProperties() ) {
				Metadata meta = manager.getMetadata(visField);
				if ( isVisibleReferred(meta) )
					visibleReferred.add(meta);
			}
		} catch (NoMetadataFoundException e) {}

		return visibleReferred;
	}

	public static List<Metadata> getManyToOneFields( Metadata classMetadata, MetadataManager manager ) {
		List<Metadata> manyToOnes = new ArrayList<Metadata>();
		try {
			for (Method visField : classMetadata.getProperties() ) {
				Metadata meta = manager.getMetadata(visField);
				if ( isManyToOne(meta) )
					manyToOnes.add(meta);
			}
		} catch (NoMetadataFoundException e) {}

		return manyToOnes;		
	}
	
	public static List<Metadata> getOneToManyFields( Metadata classMetadata, MetadataManager manager ) {
		List<Metadata> oneToManys = new ArrayList<Metadata>();
		try {
			for (Method visField : classMetadata.getProperties() ) {
				Metadata meta = manager.getMetadata(visField);
				if ( isOneToMany(meta) )
					oneToManys.add(meta);
			}
		} catch (NoMetadataFoundException e) {}

		return oneToManys;		
	}
	
	public static boolean isReadOnly(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "readOnly", Instance.BOOLEAN);
	}

	public static boolean isUnchangeable(Metadata propertyMetadata) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "unchangeable", Instance.BOOLEAN);
	}

	public static boolean isInvisible( Metadata propertyMetadata ) {
		return MetadataAccessor.readMetadataInfo(propertyMetadata, "invisible", Instance.BOOLEAN);
	}
}
