package ro.siveco.svapnt.configuration.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.configuration.generated.entity.ParamValueBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Parametri aplicatii specifici pe unitati organizatorice<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName ORG_UNIT_PARAMS
 * constraintNamePK PK_ORG_UNIT_PARAMS
 * finders findByParameterIdAndOrgUnitIsNull|true| |AND| 
 * validFrom validFrom
 * validTo validTo
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                   
	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = ParamValueBase.NQ_F1findByOrgUnitAndParameterId,
            query = "from " + ParamValue.ENTITY_NAME + " o "
                      + " where  o.orgUnit is null AND o.parameter.id = ?1  "
            ),

  

    @NamedQuery
        (
            name = "configuration_ParamValue_getAll",
            query = "from " + ParamValue.ENTITY_NAME
        )

    }
    )
@Entity( name = ParamValue.ENTITY_NAME )
@Table( name = "CFG_ORG_UNIT_PARAMS" )
public class ParamValue extends ParamValueBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(ParamValue.ENTITY_NAME, ParamValue.class);
    }
   
}
