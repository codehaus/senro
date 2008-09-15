package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.ModelEntityBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Entitati model<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_BASE_ENTITIES
 * noInsert true
 * noDelete true
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                
    @NamedQuery
        (
            name = ModelEntityBase.NQ_findByCode,
            query = "select object(o) from " + ModelEntity.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_ModelEntity_getAll",
            query = "from " + ModelEntity.ENTITY_NAME
        )

    }
    )
@Entity( name = ModelEntity.ENTITY_NAME )
@Table( name = "CFG_MODEL_ENTITIES" )
public class ModelEntity extends ModelEntityBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ModelEntity.ENTITY_NAME, ModelEntity.class);
    }
   
}
