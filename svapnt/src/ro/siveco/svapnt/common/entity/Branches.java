package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.BranchesBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Sucursale<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in dictionarul de
 * date.
 * </p>
 *
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                             
    @NamedQuery
        (
            name = BranchesBase.NQ_findByCode,
            query = "select object(o) from " + Branches.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Branches_getAll",
            query = "from " + Branches.ENTITY_NAME
        )

    }
    )
@Entity( name = Branches.ENTITY_NAME )
@Table( name = "COM_BRANCHES" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Branches extends BranchesBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Branches.ENTITY_NAME, Branches.class);
    }
   
}
