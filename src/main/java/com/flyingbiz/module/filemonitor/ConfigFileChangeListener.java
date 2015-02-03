package com.flyingbiz.module.filemonitor;

import com.flyingbiz.module.config.Config;

public class ConfigFileChangeListener implements FileChangeListener {

	@Override
	public void setChanged(String filename) {
		// TODO Auto-generated method stub
		Config.loadProperties(filename);
	}

}
