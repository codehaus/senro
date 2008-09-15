package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.ReportBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Rapoartele realizate cu instrumentul de raportare sunt
 * inregistrate aici ca inregistrari distincte in aceasta
 * entitate<br>
 * </p>
 * <p>
 * Nu exista o cerinta de date corespondenta in documentul
 * dictionar de date<br>
 * </p>
 *
 * noUpdate true
 * noDelete true
 * noInsert true
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
            name = "common_Report_getAll",
            query = "from " + Report.ENTITY_NAME
        )

    }
    )
@Entity( name = Report.ENTITY_NAME )
@Table( name = "COM_REPORTS" )
public class Report extends ReportBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(Report.ENTITY_NAME, Report.class);
    }
   
}
