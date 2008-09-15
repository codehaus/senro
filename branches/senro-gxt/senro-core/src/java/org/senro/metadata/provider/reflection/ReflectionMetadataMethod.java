package org.senro.metadata.provider.reflection;

public interface ReflectionMetadataMethod {

	public String getName();

	public void setName(String name);

	public Class<?>[] getArgumentTypes();

	public void setArgumentTypes(Class<?>[] argumentTypes);

	public String getDisplayName();

	public void setDisplayName(String displayName);

	public String getShortDescription();

	public void setShortDescription(String shortDescription);

	public Class<?> getType();

	public void setType(Class<?> type);

	public boolean isHidden();

	public void setHidden(boolean hidden);

}