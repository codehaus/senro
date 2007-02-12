package org.senro.metadata.provider.senro;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface SenroMetadataProperty {
 	boolean isVisibleDetail();
	void setVisibleDetail(boolean visibleDetail);

	boolean isVisibleList();
	void setVisibleList(boolean visibleList);

	boolean isVisibleReferred();
	void setVisibleReferred(boolean visibleReferred);

	String getLabel();
	void setLabel(String label);

	int getLength();
	void setLength(int length);

	int getOrderNo();
	void setOrderNo(int orderNo);

	int getListOrderNo();
	void setListOrderNo(int listOrderNo);

	boolean isReadOnly();
	void setReadOnly(boolean readOnly);

	boolean isRequired();
	void setRequired(boolean required);

	int getVisibleLength();
	void setVisibleLength(int visibleLength);

    void setWidget(String widget);
    public String getWidget();

    void setWidgetValues(String widgetValues);
    public String getWidgetValues();

    public boolean isUnchangeable();
    public void setUnchangeable(boolean unchangeable);

}
