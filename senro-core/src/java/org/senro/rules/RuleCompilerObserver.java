package org.senro.rules;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.Observable;
import java.util.Observer;

import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderConfiguration;
import org.springframework.core.io.Resource;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class RuleCompilerObserver implements Observer {
	private static PackageBuilderConfiguration builderConfiguration;

	static {
		builderConfiguration = new PackageBuilderConfiguration();
		builderConfiguration.setCompiler(PackageBuilderConfiguration.ECLIPSE);
		builderConfiguration.setJavaLanguageLevel("1.5");
	}

	public synchronized void update(Observable observable, Object obj) {
		if ( !(observable instanceof RulesRepository) )
			return;

		RulesRepository repository = (RulesRepository) observable;
		Resource resource = (Resource) obj;
		PackageBuilder packageBuilder = new PackageBuilder(builderConfiguration);
		File file = null;

		try {
			file = resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		org.drools.rule.Package pkg = new org.drools.rule.Package();

		/* DO NOT COMPILE RULES FOR NOW */

		if (file.getName().endsWith(".drlc")) {
			try {
				//pkg.readExternal(new ObjectInputStream(resource.getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		else {
			try {
				Reader reader = new InputStreamReader(resource.getInputStream());
				packageBuilder.addPackageFromDrl(reader);
				pkg = packageBuilder.getPackage();
				//String outputFileName = file.getPath()+"c";
				//pkg.writeExternal(new ObjectOutputStream(new FileOutputStream(outputFileName)));
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		try {
			if ( !repository.getRuleMap().containsKey(file.toURL()) )
				repository.getRuleMap().put(file.toURL(), pkg);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
