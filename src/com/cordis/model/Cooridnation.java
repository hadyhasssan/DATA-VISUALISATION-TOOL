package com.cordis.model;

import java.io.Serializable;

public class Cooridnation implements Serializable {
	private static final long serialVersionUID = 1L;

	private int projectId;

	private double ecContribution;

	private Actor actor;

	private Project project;

	public Cooridnation() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public double getEcContribution() {
		return this.ecContribution;
	}

	public void setEcContribution(double ecContribution) {
		this.ecContribution = ecContribution;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}