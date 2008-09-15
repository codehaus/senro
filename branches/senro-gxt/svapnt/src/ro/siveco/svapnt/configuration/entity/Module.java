package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.ModuleBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Module<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_MODULES
 * tableName MODULES
 * tree true
 * treeDisplay code
 * treeTooltip description
 * treeLeftBound leftBound
 * treeRightBound rightBound
 * entityLevel entityLevel
 * noDelete true
 * noInsert true
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                        
    @NamedQuery
        (
            name = ModuleBase.NQ_findByCode,
            query = "select object(o) from " + Module.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "configuration_Module_getAll",
            query = "from " + Module.ENTITY_NAME
        )

    }
    )
@Entity( name = Module.ENTITY_NAME )
@Table( name = "CFG_MODULES" )
public class Module extends ModuleBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Module.ENTITY_NAME, Module.class);
    }
   
}
