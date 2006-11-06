package org.senro.rules;


/**
 * Define basic behaviour for rules engines.
 * @authorClaudiu Dumitrescu
 */
public interface IRulesEngine {

    //todo Claudiu: add a method to impose a rules repository (eg. setRulesReposotiry) to every engine

    /**
     * Apply any existing rules.
     */
    void fireRules();

}
