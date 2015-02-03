package com.flyingbiz.module.filemonitor;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class FileChangeMonitor {

	private HashMap<String, FileChangeTask> listenerMap;
	private static FileChangeMonitor monitor = new FileChangeMonitor();
	private Timer timer;

	private FileChangeMonitor() {

		listenerMap = new HashMap<String, FileChangeTask>();
		timer = new Timer(FileChangeMonitor.class.getName());
	}

	public static synchronized FileChangeMonitor getMonitor() {

		if (monitor == null)
			return new FileChangeMonitor();
		else
			return monitor;
	}

	public void addFileChangeListener(String filename,
			FileChangeListener listener, long delay)
			throws FileNotFoundException {

		TimerTask task = new FileChangeTask(filename, listener);
		if (listenerMap.containsKey(filename))
			removeFileChangeListener(filename);
		timer.schedule(task, delay, delay);
	}

	public void removeFileChangeListener(String filename) {

		FileChangeTask task = listenerMap.get(filename);
		if (task != null)
			task.cancel();
		listenerMap.remove(task);
	}

	public static void main(String args[]) throws FileNotFoundException {
		String filename = "schoolFellow.properties";
		FileChangeListener listener = new ConfigFileChangeListener();
		long delay = 10;
		FileChangeMonitor.getMonitor().addFileChangeListener(filename,
				listener, delay);
	}

}
