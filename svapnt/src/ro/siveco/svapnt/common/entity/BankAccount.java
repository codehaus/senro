package ro.siveco.svapnt.common.entity;

import javax.persistence.*;
import static javax.persistence.DiscriminatorType.*;
import ro.siveco.svapnt.common.generated.entity.BankAccountBase;
import ro.siveco.svapnt.common.session.EntityBeansRepositoryImpl;

/**
 * <p>
 * Conturi bancare
 * </p>
 * <p>
 * <br>Vezi documentul Dictionar Date<br>
 * </p>
 * <p>
 * Informatii de identificare a persoanelor juridice<br>
 * </p>
 * <p>
 * Conturi bancare persoane fizice sau juridice<br>
 * </p>
 *
 * constraintNamePK PK_BANK_ACCOUNTS
 * tableName BANK_ACCOUNTS
 * finders common_BankAccount_1|true||AND|AND|AND|
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
            name = BankAccountBase.NQ_findByBankAccountAndBank,
            query = "select object(o) from " + BankAccount.ENTITY_NAME + " o where " +
                    "o.bankAccount = ?1 and o.bank.id = ?2"
        ),

	//Unique keys methods with Relations as parameters

//model defined finders


    @NamedQuery
        (
            name = BankAccountBase.NQ_F1findByPreferredAccountAndCurrencyCodeAndPartnerCodeAndPartnerType,
            query = "from " + BankAccount.ENTITY_NAME + " o "
                      + " where  o.preferredAccount = ?1 AND o.currency.code = ?2 AND o.partner.code = ?3 AND o.partner.type = ?4  "
            ),

  

    @NamedQuery
        (
            name = "common_BankAccount_getAll",
            query = "from " + BankAccount.ENTITY_NAME
        )

    }
    )
@Entity( name = BankAccount.ENTITY_NAME )
@Table( name = "COM_BANK_ACCOUNTS" )
public class BankAccount extends BankAccountBase implements java.io.Serializable
{
    // Inserati cod aici; adaugati numai proprietati marcate cu @Transient; nu modificati liniile de mai sus
    // Insert code here; only add @Transient properties; do not modify lines above
    static {
        EntityBeansRepositoryImpl.getInstance().addEntity(BankAccount.ENTITY_NAME, BankAccount.class);
    }
   
}
