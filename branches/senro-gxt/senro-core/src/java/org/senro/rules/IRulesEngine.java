package org.senro.rules;

/**
 * Define basic behaviour for rules engines.
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public interface IRulesEngine {
	public void setRulesRepository(RulesRepository rulesRepository);

    /**
     * Apply any existing rules.
     */
    void fireRules();
}
