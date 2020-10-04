package com.cordis.model;

import java.io.Serializable;

public class Participation implements Serializable {
	private static final long serialVersionUID = 1L;

	private double ecContribution;

	private boolean endOfParticipation;

	private Actor actor;

	private Project project;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Participation() {
	}

	public double getEcContribution() {
		return this.ecContribution;
	}

	public void setEcContribution(double ecContribution) {
		this.ecContribution = ecContribution;
	}

	@Override
	public String toString() {
		return "Participation{" +
				"ecContribution=" + ecContribution +
				", endOfParticipation=" + endOfParticipation +
				", actor=" + actor +
				", project=" + project +
				", url='" + url + '\'' +
				'}';
	}

	public boolean getEndOfParticipation() {
		return this.endOfParticipation;
	}

	public void setEndOfParticipation(boolean endOfParticipation) {
		this.endOfParticipation = endOfParticipation;
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