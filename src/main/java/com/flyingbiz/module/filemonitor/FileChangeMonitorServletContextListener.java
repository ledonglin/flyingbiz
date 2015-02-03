package com.flyingbiz.module.filemonitor;

import java.io.FileNotFoundException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FileChangeMonitorServletContextListener implements
		ServletContextListener {
	private String[] propertiesFilename;
	private long delay;
	private FileChangeListener listenerClass;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if(delay < 0 )
			delay = 10;
		for(int i = 0; i < propertiesFilename.length; i++)
			try {
				FileChangeMonitor.getMonitor().addFileChangeListener(propertiesFilename[i], listenerClass, delay);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		for(int i = 0; i < propertiesFilename.length; i++)
			FileChangeMonitor.getMonitor().removeFileChangeListener(propertiesFilename[i]);
	}

}
