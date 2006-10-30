package org.senro.rules;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Author: Claudiu Dumitrescu
 */
public class DroolsEngine implements IRulesEngine, ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    private RuleBase ruleBase;


    private ArrayList<String> rulesRepository = new ArrayList<String>();

    public void setRulesRepository(ArrayList<String> rulesRepository) {
        this.rulesRepository = rulesRepository;
    }


    public void fireRules() {
        Collection contextObjects = ThreadLocalContext.get();
        if (contextObjects != null && contextObjects.size() > 0) {
            try {
                WorkingMemory workingMemory = ruleBase.newWorkingMemory();
                for (Object o : contextObjects) {
                    workingMemory.assertObject(o);
                }
                workingMemory.fireAllRules();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ThreadLocalContext.remove();
    }

    public RuleBase loadRulesFromRepository() throws Exception, DroolsParserException {
        ruleBase = RuleBaseFactory.newRuleBase();
        for (Iterator<String> iterator = rulesRepository.iterator(); iterator.hasNext();) {
            Resource ruleResource = resourceLoader.getResource(iterator.next());
            Reader reader = new InputStreamReader(ruleResource.getInputStream());
            PackageBuilderConfiguration builderConfiguration = new PackageBuilderConfiguration();
            builderConfiguration.setCompiler(PackageBuilderConfiguration.JANINO);
            PackageBuilder packageBuilder = new PackageBuilder(builderConfiguration);
            packageBuilder.addPackageFromDrl(reader);
            org.drools.rule.Package pkg = packageBuilder.getPackage();
            ruleBase.addPackage(pkg);
        }
        return ruleBase;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
