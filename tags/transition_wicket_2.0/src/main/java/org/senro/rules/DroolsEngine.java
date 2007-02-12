package org.senro.rules;

import java.net.URL;
import java.util.Collection;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.DroolsParserException;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 * @author Claudiu Dumitrescu
 */
public class DroolsEngine implements IRulesEngine {
	private RulesRepository repository;
    private RuleBase ruleBase;

    public DroolsEngine() {}

    public void fireRules() {
        Collection contextObjects = ThreadLocalContext.get();
        if (contextObjects != null && contextObjects.size() > 0) {
            try {
            	if (ruleBase == null)
            		ruleBase = loadRules();

                WorkingMemory workingMemory = ruleBase.newWorkingMemory();
                for (Object o : contextObjects) {
                    workingMemory.assertObject(o);
                }

                for (String agendaGroup : RulesRepository.RULE_PRIORITY) {
                	workingMemory.setFocus(agendaGroup);
                	workingMemory.fireAllRules();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* invalidate rules */
        ruleBase = null;

        ThreadLocalContext.remove();
    }

    public RuleBase loadRules() throws Exception, DroolsParserException {
        ruleBase = RuleBaseFactory.newRuleBase();

        for (org.drools.rule.Package pkg : repository.getRules())
        	ruleBase.addPackage(pkg);

        return ruleBase;
    }

	public void addRuleResource(URL fileURL) {
		repository.addResource(fileURL);
	}

	public void setRulesRepository(RulesRepository rulesRepository) {
		this.repository = rulesRepository;
	}
}
