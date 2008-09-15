package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.BuildingTypeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Tip Cladire<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in dictionarul de
 * date.
 * </p>
 *
 * tableName BUILDING_TYPES
 * constraintNamePK PK_BUILDING_TYPES
 * defaultValueFlag implicitValueFlag
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                               
    @NamedQuery
        (
            name = BuildingTypeBase.NQ_findByCode,
            query = "select object(o) from " + BuildingType.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_BuildingType_getAll",
            query = "from " + BuildingType.ENTITY_NAME
        )

    }
    )
@Entity( name = BuildingType.ENTITY_NAME )
@Table( name = "COM_BUILDING_TYPES" )
public class BuildingType extends BuildingTypeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(BuildingType.ENTITY_NAME, BuildingType.class);
    }
   
}
