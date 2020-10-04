package com.cordis.model;

import java.io.Serializable;
import java.util.List;


public class Programme implements Serializable {
	private static final long serialVersionUID = 1L;

	private String programmeCode;

	private String programmeName;

	private List<Project> projects;

	public Programme() {
	}

	public String getProgrammeCode() {
		return this.programmeCode;
	}

	public void setProgrammeCode(String programmeCode) {
		this.programmeCode = programmeCode;
	}

	public String getProgrammeName() {
		return this.programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}