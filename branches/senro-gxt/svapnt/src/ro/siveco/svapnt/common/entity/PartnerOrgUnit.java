package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerOrgUnitBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Structuri organizatorice pe parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tree true
 * entityID 3
 * treeLeftBound leftBound
 * treeRightBound rightBound
 * treeDisplay code
 * treeTooltip name
 * entityLevel entityLevel
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                         
    @NamedQuery
        (
            name = PartnerOrgUnitBase.NQ_findByCode,
            query = "select object(o) from " + PartnerOrgUnit.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PartnerOrgUnit_getAll",
            query = "from " + PartnerOrgUnit.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerOrgUnit.ENTITY_NAME )
@Table( name = "COM_PARTNER_ORG_UNITS" )
@DiscriminatorValue( "3" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class PartnerOrgUnit extends PartnerOrgUnitBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerOrgUnit.ENTITY_NAME, PartnerOrgUnit.class);
    }
   
}
