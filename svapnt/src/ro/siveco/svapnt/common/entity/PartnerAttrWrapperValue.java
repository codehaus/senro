package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerAttrWrapperValueBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Entitate ajutatoare interna aplicatiei<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * formColumns 3
 * validTo validTo
 * validFrom validFrom
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
    @NamedQuery
        (
            name = PartnerAttrWrapperValueBase.NQ_findByMinStringValAndMinDateValAndMinNumberValAndMaxStringValAndMaxDateValAndMaxNumberValAndWrapper,
            query = "select object(o) from " + PartnerAttrWrapperValue.ENTITY_NAME + " o where " +
                    "( o.minStringVal is null or o.minStringVal = ?1 ) and ( o.minDateVal is null or o.minDateVal = ?2 ) and ( o.minNumberVal is null or o.minNumberVal = ?3 ) and ( o.maxStringVal is null or o.maxStringVal = ?4 ) and ( o.maxDateVal is null or o.maxDateVal = ?5 ) and ( o.maxNumberVal is null or o.maxNumberVal = ?6 ) and o.wrapper.id = ?7"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_PartnerAttrWrapperValue_getAll",
            query = "from " + PartnerAttrWrapperValue.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerAttrWrapperValue.ENTITY_NAME )
@Table( name = "COM_PARTNER_ATTR_WRAPPER_VALUS" )
public class PartnerAttrWrapperValue extends PartnerAttrWrapperValueBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerAttrWrapperValue.ENTITY_NAME, PartnerAttrWrapperValue.class);
    }
   
}
