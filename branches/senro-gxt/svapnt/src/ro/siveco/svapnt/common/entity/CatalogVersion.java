package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CatalogVersionBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Folosita pentru versionare. Nu are interfata cu utilizatorul<br>
 * </p>
 * <p>
 * Nu exista o cerinte de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * validTo validTo
 * versionEntity true
 * validFrom validFrom
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                     
	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_CatalogVersion_getAll",
            query = "from " + CatalogVersion.ENTITY_NAME
        )

    }
    )
@Entity( name = CatalogVersion.ENTITY_NAME )
@Table( name = "COM_CATALOG_VERSIONS" )
public class CatalogVersion extends CatalogVersionBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(CatalogVersion.ENTITY_NAME, CatalogVersion.class);
    }
   
}
