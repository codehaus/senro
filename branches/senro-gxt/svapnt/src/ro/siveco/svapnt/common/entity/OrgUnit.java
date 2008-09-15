package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.OrgUnitBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Structuri organizatorice : contine atat unitatile organizatorice
 * (sucursale, filiale etc) cat si departamentele, birouri,
 * ateliere etc.
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Date cu privire la Casele de Asigurari de Sanatate<br>
 * </p>
 * <p>
 * Data necesare evidentei cererilor de acreditare
 * </p>
 *
 * tree true
 * tableName ORG_UNITS
 * constraintNamePK PK_ORG_UNITS
 * entityID 2
 * finderInfo findByParameterIdAndOrgUnitIsNull|1|is null;findByDocTypeIdAndNullOrgUnit|2|is null
 * treeDisplay code
 * treeTooltip name
 * validFrom validFrom
 * validTo validTo
 * entityLevel entityLevel
 * treeLeftBound leftBound
 * treeRightBound rightBound
 * permanentLog true
 *
 */

@NamedQueries
    (
    {
/* Unique Keys */

                                                                                                                                                                                                                                             
    @NamedQuery
        (
            name = OrgUnitBase.NQ_findByCode,
            query = "select object(o) from " + OrgUnit.ENTITY_NAME + " o where " +
                    "o.code = ?1"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = "common_OrgUnit_getAll",
            query = "from " + OrgUnit.ENTITY_NAME
        )

    }
    )
@Entity( name = OrgUnit.ENTITY_NAME )
@Table( name = "COM_ORG_UNITS" )
@DiscriminatorValue( "2" )
@DiscriminatorColumn( name = "TYPE", discriminatorType = INTEGER )
public class OrgUnit extends OrgUnitBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(OrgUnit.ENTITY_NAME, OrgUnit.class);
    }
   
}
