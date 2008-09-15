package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PersonBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Persoane fizice<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare persoane fizice cu studii
 * non-medicale superioare
 * </p>
 *
 * constraintNamePK PK_PERSONS
 * tableName PERSONS
 * entityID 1
 * validFrom birthDate
 * validTo validTo
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                   
    @NamedQuery
        (
            name = PersonBase.NQ_findByCode,
            query = "select object(o) from " + Person.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

    @NamedQuery
        (
            name = PersonBase.NQ_findByPid,
            query = "select object(o) from " + Person.ENTITY_NAME + " o where " +
                    "( o.pid is null or o.pid = ?1 )"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Person_getAll",
            query = "from " + Person.ENTITY_NAME
        )

    }
    )
@Entity( name = Person.ENTITY_NAME )
@Table( name = "COM_PERSONS" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Person extends PersonBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Person.ENTITY_NAME, Person.class);
    }
   
}
