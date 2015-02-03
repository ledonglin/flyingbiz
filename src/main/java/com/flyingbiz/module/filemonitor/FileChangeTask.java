package com.flyingbiz.module.filemonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TimerTask;

public class FileChangeTask extends TimerTask {

	private long lastModified;
	private String filename;
	private FileChangeListener fileChangeListener;
	private File file;

	public FileChangeTask(String filename, FileChangeListener fileChangeListener)
			throws FileNotFoundException {
		super();
		this.filename = filename;
		this.fileChangeListener = fileChangeListener;
		file = new File(filename);
		if (!file.exists())
			throw new FileNotFoundException("File not found : " + filename);

		this.lastModified = file.lastModified();
	}

	@Override
	public void run() {

		long modified = file.lastModified();
		if (modified > lastModified) {
			lastModified = modified;
			fileChangeListener.setChanged(file.getName());
		}

	}

}
