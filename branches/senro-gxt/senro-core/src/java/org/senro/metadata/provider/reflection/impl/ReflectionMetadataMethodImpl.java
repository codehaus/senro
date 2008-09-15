package org.senro.metadata.provider.reflection.impl;

import org.senro.metadata.provider.reflection.ReflectionMetadataMethod;

/**
 * @author Flavius Burca
 *
 */
public class ReflectionMetadataMethodImpl implements ReflectionMetadataMethod {
    private String name;
    private Class<?>[] argumentTypes;
    private String displayName;
    private String shortDescription;
    private Class<?> type;
    private boolean hidden;

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#getName()
	 */
    public String getName() {
        return name;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#setName(java.lang.String)
	 */
    public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#getArgumentTypes()
	 */
    public Class<?>[] getArgumentTypes() {
        return argumentTypes;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#setArgumentTypes(java.lang.Class[])
	 */
    public void setArgumentTypes(Class<?>[] argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#getDisplayName()
	 */
    public String getDisplayName() {
        return displayName;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#setDisplayName(java.lang.String)
	 */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#getShortDescription()
	 */
    public String getShortDescription() {
        return shortDescription;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#setShortDescription(java.lang.String)
	 */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#getType()
	 */
    public Class<?> getType() {
        return type;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#setType(java.lang.Class)
	 */
    public void setType(Class<?> type) {
        this.type = type;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#isHidden()
	 */
    public boolean isHidden() {
        return hidden;
    }

    /* (non-Javadoc)
	 * @see org.senro.metadata.provider.reflection.impl.RMM#setHidden(boolean)
	 */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
