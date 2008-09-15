package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.StreetTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tip artera/strada (pentru adrese)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * constraintNamePK PK_STREET_TYPES
 * tableName STREET_TYPES
 * defaultValueFlag defaultValueFlag
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = StreetTypeBase.NQ_findByCode,
            query = "select object(o) from " + StreetType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_StreetType_getAll",
            query = "from " + StreetType.ENTITY_NAME
        )

    }
    )
@Entity( name = StreetType.ENTITY_NAME )
@Table( name = "COM_STREET_TYPES" )
public class StreetType extends StreetTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(StreetType.ENTITY_NAME, StreetType.class);
    }
   
}
