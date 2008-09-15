package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.OrgUnitTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de structuri organizatorice<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ORG_UNIT_TYPES
 * tableName ORG_UNIT_TYPES
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = OrgUnitTypeBase.NQ_findByCode,
            query = "select object(o) from " + OrgUnitType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_OrgUnitType_getAll",
            query = "from " + OrgUnitType.ENTITY_NAME
        )

    }
    )
@Entity( name = OrgUnitType.ENTITY_NAME )
@Table( name = "COM_ORG_UNIT_TYPES" )
public class OrgUnitType extends OrgUnitTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(OrgUnitType.ENTITY_NAME, OrgUnitType.class);
    }
   
}
