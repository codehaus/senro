package org.senro.rules;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.senro.SenroRuntimeException;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class RulesRepository extends Observable implements ResourceLoaderAware, Observer {
	public static RulesRepository DEFAULT_REPOSITORY;
	public static String[] RULE_PRIORITY = { "GENERAL", "MODULE", "METADATA", "PAGE", "CUSTOM" };

	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	private List<URL> resources = new ArrayList<URL>();
	private Map<URL, org.drools.rule.Package> rules = new HashMap<URL, org.drools.rule.Package>();
	private boolean resourcesLoaded = false;

	private RulesRepository() {}

	public static void initDefaultRepository(String rootURL) throws SenroRuntimeException {
		DEFAULT_REPOSITORY = new RulesRepository();
		RulesFileMonitor monitor = new RulesFileMonitor(rootURL);
		monitor.addObserver(DEFAULT_REPOSITORY);

		DEFAULT_REPOSITORY.addObserver(new RuleCompilerObserver());
		DEFAULT_REPOSITORY.addObserver(monitor);
		monitor.startMonitor();
	}

	public synchronized void addResource(URL fileURL) {
		Resource resource = resourceLoader.getResource(fileURL.toString());

		if (!resources.contains(fileURL)) {
			resources.add(fileURL);
			setChanged();
			notifyObservers(resource);
		}

		resource = null;
	}

	public Iterable<org.drools.rule.Package> getRules() {
		while (!resourcesLoaded) {
			try {
				System.out.println("Waiting for repository to load...");
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return rules.values();
	}

	public void setResourceLoader(ResourceLoader resourceloader) {
		this.resourceLoader = resourceloader;
	}

	public Map<URL, org.drools.rule.Package> getRuleMap() {
		return rules;
	}

	public void updateResource(URL url) {
		resources.remove(url);
		rules.remove(url);

		addResource(url);
	}

	/**
	 * Can be notified by RulesFileMonitor of rule file changes
	 */
	public synchronized void update(Observable observable, Object obj) {
		if ( ! (obj instanceof FileMonitorEvent) )
			return;

		FileMonitorEvent evt = (FileMonitorEvent) obj;

		if (evt.getEvent() == FileMonitorEvent.NEW_FILE) {
			File file = (File) evt.getAttachedObject();
			try {
				System.out.println("New resource found: "+file.getPath());
				addResource(file.toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else if (evt.getEvent() == FileMonitorEvent.FILE_CHANGED) {
			File file = (File) evt.getAttachedObject();

			try {
				URL fileURL = file.toURL();
				if (resources.contains(fileURL)) {
					System.out.println("Resource modified: "+fileURL.getFile());
					updateResource(fileURL);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else if (evt.getEvent() == FileMonitorEvent.FILE_DELETED) {
			URL fileURL = (URL) evt.getAttachedObject();
			System.out.println("Resource deleted: "+fileURL.getFile());
			resources.remove(fileURL);
			rules.remove(fileURL);
		}
		else if (evt.getEvent() == FileMonitorEvent.REPOSITORY_LOADED) {
			resourcesLoaded = true;
		}
	}
}
