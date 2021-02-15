package com.jbuild.forms.jbuildforms.model.tools;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.jbuild.forms.jbuildforms.model.MenuProgressBar;

 

@Component
public class MenuProgressBarInfo {
	private String menuCode;

	private Map<String, String> mapLinks;

	public void setMenuProgressBar(MenuProgressBar menuProgressBar, Map<String, String> mapLinks) {
		if (menuProgressBar != null) {
			this.menuCode = menuProgressBar.getMenu();
		}
		this.mapLinks = mapLinks;
	}

	public int getIteration() {
		return this.menuCode.charAt(0) - '0';
	}

	public int getActive() {
		return (this.menuCode.charAt(1) - '0') + 1;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public Map<String, String> getMapLinks() {
		return mapLinks;
	}

	public void setMapLinks(Map<String, String> mapLinks) {
		this.mapLinks = mapLinks;
	}
}