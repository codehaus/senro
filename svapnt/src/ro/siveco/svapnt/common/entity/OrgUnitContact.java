package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.OrgUnitContactBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de contacte pe structuri organizatorice<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * validTo validTo
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
            name = "common_OrgUnitContact_getAll",
            query = "from " + OrgUnitContact.ENTITY_NAME
        )

    }
    )
@Entity( name = OrgUnitContact.ENTITY_NAME )
@Table( name = "COM_ORG_UNIT_CONTACTS" )
public class OrgUnitContact extends OrgUnitContactBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(OrgUnitContact.ENTITY_NAME, OrgUnitContact.class);
    }
   
}
