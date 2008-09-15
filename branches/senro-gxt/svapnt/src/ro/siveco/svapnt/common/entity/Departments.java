package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.DepartmentsBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Departamente - derivat din structuri organizationale<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * entityID 2
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
            name = DepartmentsBase.NQ_findByCode,
            query = "select object(o) from " + Departments.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Departments_getAll",
            query = "from " + Departments.ENTITY_NAME
        )

    }
    )
@Entity( name = Departments.ENTITY_NAME )
@Table( name = "COM_DEPARTMENTS" )
@DiscriminatorValue( "2" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Departments extends DepartmentsBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Departments.ENTITY_NAME, Departments.class);
    }
   
}
