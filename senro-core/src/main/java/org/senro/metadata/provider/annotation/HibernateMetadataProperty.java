package org.senro.metadata.provider.annotation;

/**
 * Created by IntelliJ IDEA.
 * User: briantopping
 * Date: Nov 3, 2006
 * Time: 2:06:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface HibernateMetadataProperty {

    boolean isManyToOne();

    void setManyToOne(boolean boolVal);
}
