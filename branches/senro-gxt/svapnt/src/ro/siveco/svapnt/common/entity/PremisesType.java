package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PremisesTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tip Incinta (utilizat in adrese)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in dictionarul de date
 * </p>
 *
 * tableName PREMISE_TYPES
 * constraintNamePK PK_PREMISE_TYPES
 * defaultValueFlag defaultValueFlag
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = PremisesTypeBase.NQ_findByCode,
            query = "select object(o) from " + PremisesType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PremisesType_getAll",
            query = "from " + PremisesType.ENTITY_NAME
        )

    }
    )
@Entity( name = PremisesType.ENTITY_NAME )
@Table( name = "COM_PREMISE_TYPES" )
public class PremisesType extends PremisesTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PremisesType.ENTITY_NAME, PremisesType.class);
    }
   
}
