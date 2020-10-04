package com.cordis.model;

import java.io.Serializable;
import java.util.List;


public class FundingSchemeCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codeFundingScheme;

	private String fundingSchemeDescription;

	private String fundingSchemeTitle;

	private Language language;

	private List<Project> projects;

	public FundingSchemeCategory() {
	}

	public String getCodeFundingScheme() {
		return this.codeFundingScheme;
	}

	public void setCodeFundingScheme(String codeFundingScheme) {
		this.codeFundingScheme = codeFundingScheme;
	}

	public String getFundingSchemeDescription() {
		return this.fundingSchemeDescription;
	}

	public void setFundingSchemeDescription(String fundingSchemeDescription) {
		this.fundingSchemeDescription = fundingSchemeDescription;
	}

	public String getFundingSchemeTitle() {
		return this.fundingSchemeTitle;
	}

	public void setFundingSchemeTitle(String fundingSchemeTitle) {
		this.fundingSchemeTitle = fundingSchemeTitle;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setFundingSchemeCategoryBean(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setFundingSchemeCategoryBean(null);

		return project;
	}

}