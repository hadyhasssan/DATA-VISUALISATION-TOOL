package com.cordis.model;

import java.io.Serializable;
import java.util.List;

public class FrameworkProgramme implements Serializable {
	private static final long serialVersionUID = 1L;

	private int frameworkId;

	private String frameworkName;

	private List<Project> projects;

	public FrameworkProgramme() {
	}

	public int getFrameworkId() {
		return this.frameworkId;
	}

	public void setFrameworkId(int frameworkId) {
		this.frameworkId = frameworkId;
	}

	public String getFrameworkName() {
		return this.frameworkName;
	}

	public void setFrameworkName(String frameworkName) {
		this.frameworkName = frameworkName;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setFrameworkProgrammeBean(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setFrameworkProgrammeBean(null);

		return project;
	}

}