package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.DataTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tipuri de date<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_ATTRIBUTE_TYPE
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                        
    @NamedQuery
        (
            name = DataTypeBase.NQ_findByName,
            query = "select object(o) from " + DataType.ENTITY_NAME + " o where " +
                    "o.name = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_DataType_getAll",
            query = "from " + DataType.ENTITY_NAME
        )

    }
    )
@Entity( name = DataType.ENTITY_NAME )
@Table( name = "CFG_DATA_TYPES" )
public class DataType extends DataTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(DataType.ENTITY_NAME, DataType.class);
    }
   
}
