package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Firme si persoane fizice. Tine de asemenea inregistrari
 * corespondente unitatilor organizatorice, salariatilor,
 * magaziilor, bancilor. - Entitate din care se deriveaza unitati
 * organizatorice, banci, companii, salariati<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * constraintNamePK PK_PARTNERS
 * tableName PARTNERS
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
            name = PartnerBase.NQ_findByCodeAndType,
            query = "select object(o) from " + Partner.ENTITY_NAME + " o where " +
                    "o.code = ?1 and o.type = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_Partner_getAll",
            query = "from " + Partner.ENTITY_NAME
        )

    }
    )
@Entity( name = Partner.ENTITY_NAME )
@Table( name = "COM_PARTNERS" )
@DiscriminatorValue( "-1" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class Partner extends PartnerBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Partner.ENTITY_NAME, Partner.class);
    }
   
}
