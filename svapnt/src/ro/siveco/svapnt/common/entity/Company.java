package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.CompanyBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Companii<br>
 * </p>
 * <p>
 * Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Date despre contribuabil <br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 * <p>
 * Date de identificare a furnizorului de servicii medicale ?i
 * farmaceutice<br>
 * </p>
 * <p>
 * Date cu privire la partenerii sistemului
 * </p>
 *
 * tableName COMPANIES
 * constraintNamePK PK_COMPANIES
 * entityID 1
 * validFrom validFrom
 * validTo validTo
 * entityLevel entityLevel
 * finders findByFiscalCode|true||
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
    @NamedQuery
        (
            name = CompanyBase.NQ_findByCode,
            query = "select object(o) from " + Company.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

    @NamedQuery
        (
            name = CompanyBase.NQ_findByUic,
            query = "select object(o) from " + Company.ENTITY_NAME + " o where " +
                    "o.uic = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = CompanyBase.NQ_F1findAllEntities,
            query = "from " + Company.ENTITY_NAME + " o "
            ),

  

    @NamedQuery
        (
            name = "common_Company_getAll",
            query = "from " + Company.ENTITY_NAME
        )

    }
    )
@Entity( name = Company.ENTITY_NAME )
@Table( name = "COM_COMPANIES" )
@DiscriminatorValue( "1" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Company extends CompanyBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Company.ENTITY_NAME, Company.class);
    }
   
}
