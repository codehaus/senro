package org.senro.rules;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.springframework.core.io.Resource;

/**
 * This class is an observes RulesRepository and can be
 * an Observable for classes that need to know if rules file
 * structure was altered (rule hot-deploy)
 *
 * @author Flavius Burca <flavius.burca@gmail.com>
 */
public class RulesFileMonitor extends Observable implements Observer, Runnable {
	/* TODO: Get this from configuration file */
	public static int SCAN_INTERVAL = 5;
	public static String[] FILES = { ".drl" };
	public static String REPOSITORY_PATH = "overrides/procedural/rules";
	public static String GENERAL_PATH = REPOSITORY_PATH+"/"+"general";
	public static String MODULE_PATH = REPOSITORY_PATH+"/"+"module";
	public static String METADATA_PATH = REPOSITORY_PATH+"/"+"metadata";
	public static String PAGE_PATH = REPOSITORY_PATH+"/"+"page";
	public static String CUSTOM_PATH = REPOSITORY_PATH+"/"+"custom";
	public static String[] SCAN_PATHS = {
		GENERAL_PATH,
		MODULE_PATH,
		METADATA_PATH,
		PAGE_PATH,
		CUSTOM_PATH
	};
	/* TODO: Get this from configuration file */

	private Map<URL,Long> fileList = new HashMap<URL, Long>();
	private boolean isStarted = false;
	private String rootURL;

	public RulesFileMonitor(String rootURL) {
		this.rootURL = rootURL;
		this.isStarted = true;
	}

	public void startMonitor() {
		this.isStarted = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	/**
	 * Notified by RulesRepository that a new rule has been added.
	 */
	public synchronized void update(Observable observable, Object obj) {
		if (! (obj instanceof Resource) )
			return;

		Resource resource = (Resource) obj;

		try {
			File file = resource.getFile();
			URL fileUrl = file.toURL();
			if (!fileList.containsKey(fileUrl)) {
				fileList.put(fileUrl, file.lastModified());
			}
			else {
				fileList.remove(fileUrl);
				fileList.put(fileUrl, file.lastModified());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (isStarted) {
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					for (String suffix : FILES) {
						if (name.endsWith(suffix))
							return true;
					}
					return false;
				}
	    	};

	    	List<URL> foundFiles = new ArrayList<URL>();

	    	for( String scanPath : SCAN_PATHS ) {
	    		File folder = new File(rootURL+scanPath);
	    		File[] files = folder.listFiles(filter);

	    		if (files != null) {
	    			for (File file : files) {
	    				URL fileURL = null;

						try {
							fileURL = file.toURL();
						} catch (MalformedURLException e) {
							e.printStackTrace();
							continue;
						}

						foundFiles.add(fileURL);

	    				if (!fileList.containsKey(fileURL)) {
	    					setChanged();
	    					notifyObservers(new FileMonitorEvent(FileMonitorEvent.NEW_FILE, file));
	    				}
	    				else {
	    					if (!fileList.get(fileURL).equals(file.lastModified())) {
	    						setChanged();
	    						notifyObservers(new FileMonitorEvent(FileMonitorEvent.FILE_CHANGED, file));
	    					}
	    				}
	    			}
	    		}
	    	}

	    	List<URL> keysToDelete = new ArrayList<URL>();

	    	/* scan for deleted files */
	    	for (Iterator<URL> iter = fileList.keySet().iterator(); iter.hasNext(); ) {
	    		URL url = iter.next();
	    		if (!foundFiles.contains(url)) {
	    			keysToDelete.add(url);
	    			setChanged();
	    			notifyObservers(new FileMonitorEvent(FileMonitorEvent.FILE_DELETED, url));
	    		}
	    	}

	    	for(URL url : keysToDelete)
	    		fileList.remove(url);

	    	setChanged();
	    	notifyObservers(new FileMonitorEvent(FileMonitorEvent.REPOSITORY_LOADED));

	    	try {
				Thread.sleep(SCAN_INTERVAL*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
