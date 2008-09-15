package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.PartnerAttributeValueBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Valori atribute parteneri<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * finders com_pav_1|false||AND|;com_pav_2|false||AND|;com_pav_3|false||AND|;com_pav_4|false||AND|;com_pav_5|false||AND|;com_pav_6|false||AND|
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_findByMinDateValAndMaxDateValueAndMinStringValueAndMaxStringValueAndMinNumberValueAndMaxNumberValueAndPartnerAttribute,
            query = "select object(o) from " + PartnerAttributeValue.ENTITY_NAME + " o where " +
                    "( o.minDateVal is null or o.minDateVal = ?1 ) and ( o.maxDateValue is null or o.maxDateValue = ?2 ) and ( o.minStringValue is null or o.minStringValue = ?3 ) and ( o.maxStringValue is null or o.maxStringValue = ?4 ) and ( o.minNumberValue is null or o.minNumberValue = ?5 ) and ( o.maxNumberValue is null or o.maxNumberValue = ?6 ) and o.partnerAttribute.id = ?7"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_F1findByMinDateValAndMaxDateValue,
            query = "from " + PartnerAttributeValue.ENTITY_NAME + " o "
                      + " where  o.minDateVal = ?1 AND o.maxDateValue = ?2  "
            ),

  

    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_F2findByMinDateValAndMaxDateValue,
            query = "from " + PartnerAttributeValue.ENTITY_NAME + " o "
                      + " where  o.minDateVal = ?1 AND o.maxDateValue is null  "
            ),

  

    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_F3findByMinStringValueAndMaxStringValue,
            query = "from " + PartnerAttributeValue.ENTITY_NAME + " o "
                      + " where  o.minStringValue = ?1 AND o.maxStringValue = ?2  "
            ),

  

    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_F4findByMinStringValueAndMaxStringValue,
            query = "from " + PartnerAttributeValue.ENTITY_NAME + " o "
                      + " where  o.minStringValue = ?1 AND o.maxStringValue is null  "
            ),

  

    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_F5findByMinNumberValueAndMaxNumberValue,
            query = "from " + PartnerAttributeValue.ENTITY_NAME + " o "
                      + " where  o.minNumberValue = ?1 AND o.maxNumberValue = ?2  "
            ),

  

    @NamedQuery
        (
            name = PartnerAttributeValueBase.NQ_F6findByMinNumberValueAndMaxNumberValue,
            query = "from " + PartnerAttributeValue.ENTITY_NAME + " o "
                      + " where  o.minNumberValue = ?1 AND o.maxNumberValue is null  "
            ),

  

    @NamedQuery
        (
            name = "common_PartnerAttributeValue_getAll",
            query = "from " + PartnerAttributeValue.ENTITY_NAME
        )

    }
    )
@Entity( name = PartnerAttributeValue.ENTITY_NAME )
@Table( name = "COM_PARTNER_ATTRIBUTE_VALUES" )
public class PartnerAttributeValue extends PartnerAttributeValueBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(PartnerAttributeValue.ENTITY_NAME, PartnerAttributeValue.class);
    }
   
}
