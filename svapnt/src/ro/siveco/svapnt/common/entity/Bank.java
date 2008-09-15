package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.BankBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Banci<br>
 * </p>
 * <p>
 * Vezi documentul dictionar de date<>
 * </p>
 * <p>
 * Catalogul de banci
 * </p>
 *
 * constraintNamePK PK_BANKS
 * tableName BANKS
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 * entityLevel entityLevel
 * permanentLog true
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    @NamedQuery
        (
            name = BankBase.NQ_findByCode,
            query = "select object(o) from " + Bank.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

    @NamedQuery
        (
            name = BankBase.NQ_findByUic,
            query = "select object(o) from " + Bank.ENTITY_NAME + " o where " +
                    "o.uic = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Bank_getAll",
            query = "from " + Bank.ENTITY_NAME
        )

    }
    )
@Entity( name = Bank.ENTITY_NAME )
@Table( name = "COM_BANKS" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Bank extends BankBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Bank.ENTITY_NAME, Bank.class);
    }
   
}
