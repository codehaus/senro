package org.senro.metadata.provider.senro;

import java.lang.reflect.Method;

import org.senro.annotations.Label;
import org.senro.annotations.Length;
import org.senro.annotations.ListOrderNo;
import org.senro.annotations.OrderNo;
import org.senro.annotations.ReadOnly;
import org.senro.annotations.Required;
import org.senro.annotations.Unchangeable;
import org.senro.annotations.VisibleDetail;
import org.senro.annotations.VisibleLength;
import org.senro.annotations.VisibleList;
import org.senro.annotations.VisibleReferred;
import org.senro.annotations.Widget;
import org.senro.annotations.WidgetValues;
import org.senro.metadata.MetadataProvider;
import org.senro.metadata.provider.senro.impl.SenroMetadataClassImpl;
import org.senro.metadata.provider.senro.impl.SenroMetadataMethodImpl;
import org.senro.metadata.provider.senro.impl.SenroMetadataPackageImpl;
import org.senro.metadata.provider.senro.impl.SenroMetadataPropertyImpl;
import org.senro.metadata.provider.senro.impl.SenroMetadataReferenceImpl;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class SenroMetadataProvider implements MetadataProvider {
	public Class getPropertyClass() {
		return SenroMetadataPropertyImpl.class;
	}

	public Object getPropertyMetadata(Method element) {
		assert element.getName().startsWith("get");
		SenroMetadataProperty result = null;
		try {
			result = SenroMetadataPropertyImpl.class.newInstance();

			VisibleList visibleList = element.getAnnotation(VisibleList.class);
			if (visibleList != null)
            	result.setVisibleList( visibleList.value() );

			VisibleDetail visibleDetail = element.getAnnotation(VisibleDetail.class);
			if (visibleDetail != null)
            	result.setVisibleDetail( visibleDetail.value() );

			VisibleReferred visibleReferred = element.getAnnotation(VisibleReferred.class);
            if (visibleReferred != null)
            	result.setVisibleReferred( visibleReferred.value() );

            Label label = element.getAnnotation(Label.class);
            if (label != null)
            	result.setLabel(label.value());

            Length length = element.getAnnotation(Length.class);
            if (length != null)
            	result.setLength(new Integer(length.value()));

            VisibleLength visibleLength = element.getAnnotation(VisibleLength.class);
            if (visibleLength != null)
            	result.setVisibleLength(new Integer(visibleLength.value()));

            ListOrderNo listOrderNo = element.getAnnotation(ListOrderNo.class);
            if (listOrderNo != null)
            	result.setListOrderNo(new Integer(listOrderNo.value()));

            OrderNo orderNo = element.getAnnotation(OrderNo.class);
            if (orderNo != null)
            	result.setOrderNo(new Integer(orderNo.value()));

            ReadOnly readOnly = element.getAnnotation(ReadOnly.class);
            if (readOnly != null)
            	result.setReadOnly(readOnly.value());

            Unchangeable unchangeable = element.getAnnotation(Unchangeable.class);
            if (unchangeable != null)
            	result.setUnchangeable(unchangeable.value());

            Required required = element.getAnnotation(Required.class);
            if (required != null)
            	result.setRequired(required.value());

            Widget widget = element.getAnnotation(Widget.class);
            if (widget != null)
            	result.setWidget(widget.value());

            WidgetValues widgetValues = element.getAnnotation(WidgetValues.class);
            if (widgetValues != null)
            	result.setWidgetValues(widgetValues.value());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Class getReferenceClass() {
		return SenroMetadataReferenceImpl.class;
	}

	public boolean supports(Class clazz) {
		return true;
	}

	public Class getClassClass() {
		return SenroMetadataClassImpl.class;
	}

	public Object getClassMetadata(Class clazz) {
		SenroMetadataClass result = null;

		try {
			result = SenroMetadataClassImpl.class.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Class getMethodClass() {
		return SenroMetadataMethodImpl.class;
	}

	public Object getMethodMetadata(Method element) {
		return null;
	}

	public Class getPackageClass() {
		return SenroMetadataPackageImpl.class;
	}

	public Object getPackageMetadata(Package element) {
		return null;
	}
}
