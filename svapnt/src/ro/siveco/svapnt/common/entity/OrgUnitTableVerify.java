package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.OrgUnitTableVerifyBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Contine tabele in care se verifica valabilitatea structurilor
 * organizatorice (in cazul in care se modifica valabilitatea unei
 * structuri organizatorice, se verifica incadrarea datelor
 * tranzactiilor efectuate pe structura respectiva in noul interval
 * de valabiliatate)<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date
 * </p>
 *
 * tableName ORG_UNIT_TABLE_VERIFYS
 * constraintNamePK PK_OU_TBL_VRF
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
            name = "common_OrgUnitTableVerify_getAll",
            query = "from " + OrgUnitTableVerify.ENTITY_NAME
        )

    }
    )
@Entity( name = OrgUnitTableVerify.ENTITY_NAME )
@Table( name = "COM_ORG_UNIT_TABLE_VERIFYS" )
public class OrgUnitTableVerify extends OrgUnitTableVerifyBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(OrgUnitTableVerify.ENTITY_NAME, OrgUnitTableVerify.class);
    }
   
}
