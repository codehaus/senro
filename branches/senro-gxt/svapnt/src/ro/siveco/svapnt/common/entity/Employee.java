package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.EmployeeBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Salariati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName EMPLOYEES
 * constraintNamePK PK_EMPLOYEES
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
            name = EmployeeBase.NQ_findByCode,
            query = "select object(o) from " + Employee.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

    @NamedQuery
        (
            name = EmployeeBase.NQ_findByPid,
            query = "select object(o) from " + Employee.ENTITY_NAME + " o where " +
                    "( o.pid is null or o.pid = ?1 )"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Employee_getAll",
            query = "from " + Employee.ENTITY_NAME
        )

    }
    )
@Entity( name = Employee.ENTITY_NAME )
@Table( name = "COM_EMPLOYEES" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Employee extends EmployeeBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Employee.ENTITY_NAME, Employee.class);
    }
   
}
