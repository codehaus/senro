package org.senro.metadata.provider.senro.impl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.senro.metadata.provider.ejb3.EJB3MetadataProperty;
import org.senro.metadata.provider.senro.SenroMetadataProperty;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
@Aspect("pertarget(org.senro.metadata.model.impl.MetadataProperty)")
public class SenroMetadataPropertyImpl implements SenroMetadataProperty {
	@DeclareParents(value = "org.senro.metadata.model.impl.MetadataProperty", defaultImpl = SenroMetadataPropertyImpl.class)
    public static SenroMetadataProperty mixin;

	private boolean visibleReferred;
    private boolean visibleList;
    private boolean visibleDetail;
    private boolean required;
    private boolean readOnly;
    private boolean unchangeable;
    private int length;
    private int visibleLength;
    private int orderNo;
    private int listOrderNo;
    private String label;
    private String widget;
    private String widgetValues;

    public String getWidget() {
		return widget;
	}

	public void setWidget(String widget) {
		this.widget = widget;
	}

	public String getWidgetValues() {
		return widgetValues;
	}

	public void setWidgetValues(String widgetValues) {
		this.widgetValues = widgetValues;
	}

	public boolean isVisibleList() {
		return visibleList;
	}

	public boolean isVisibleReferred() {
		return visibleReferred;
	}

	public void setVisibleList(boolean visibleList) {
		this.visibleList = visibleList;
	}

	public void setVisibleReferred(boolean visibleReferred) {
		this.visibleReferred = visibleReferred;
	}

	public String getLabel() {
		return label;
	}

	public int getLength() {
		return length;
	}

	public int getListOrderNo() {
		return listOrderNo;
	}

	public int getVisibleLength() {
		return visibleLength;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public boolean isRequired() {
		return required;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setListOrderNo(int listOrderNo) {
		this.listOrderNo = listOrderNo;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public void setVisibleLength(int visibleLength) {
		this.visibleLength = visibleLength;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public boolean isVisibleDetail() {
		return visibleDetail;
	}

	public void setVisibleDetail(boolean visibleDetail) {
		this.visibleDetail = visibleDetail;
	}

	public boolean isUnchangeable() {
		return unchangeable;
	}

	public void setUnchangeable(boolean unchangeable) {
		this.unchangeable = unchangeable;
	}

}
