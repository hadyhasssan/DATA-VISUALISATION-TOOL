package com.cordis.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	private long actorId;

	private String city;

	private String name;

	private String organizationUrl;

	private String postCode;

	private String shortName;

	private String street;

	private String vatNumber;

	private ActivityType activityTypeBean;

	private Country country;

	private List<Cooridnation> cooridnations = new ArrayList<Cooridnation>();

	private List<Participation> participations=new ArrayList<Participation>();

	private List<Project> projects;

	private Double totalEcParticipation;

	@Override
	public String toString() {
		return "Actor{" +
				"actorId=" + actorId +
				", city='" + city + '\'' +
				", name='" + name + '\'' +
				", organizationUrl='" + organizationUrl + '\'' +
				", postCode='" + postCode + '\'' +
				", shortName='" + shortName + '\'' +
				", street='" + street + '\'' +
				", vatNumber='" + vatNumber + '\'' +
				", activityTypeBean=" + activityTypeBean +
				", country=" + country +
				", cooridnations=" + cooridnations +
				", participations=" + participations +
				", projects=" + projects +
				", totalEcParticipation=" + this.getTotalEcParticipation() +
				'}';
	}

	public Double getTotalEcParticipation() {
		totalEcParticipation = 0d;
		for (Participation p: this.getParticipations()){
			totalEcParticipation+=p.getEcContribution();
		}
		for (Cooridnation c: this.getCooridnations()){
			totalEcParticipation+=c.getEcContribution();
		}

		return totalEcParticipation;
	}

	public Actor() {
	}

	public long getActorId() {
		return this.actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationUrl() {
		return this.organizationUrl;
	}

	public void setOrganizationUrl(String organizationUrl) {
		this.organizationUrl = organizationUrl;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getVatNumber() {
		return this.vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public ActivityType getActivityTypeBean() {
		return this.activityTypeBean;
	}

	public void setActivityTypeBean(ActivityType activityTypeBean) {
		this.activityTypeBean = activityTypeBean;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Cooridnation> getCooridnations() {
		return this.cooridnations;
	}

	public void setCooridnations(List<Cooridnation> cooridnations) {
		this.cooridnations = cooridnations;
	}

	public Cooridnation addCooridnator(Cooridnation cooridnation) {
		getCooridnations().add(cooridnation);
		cooridnation.setActor(this);

		return cooridnation;
	}

	public Cooridnation removeCooridnator(Cooridnation cooridnation) {
		getCooridnations().remove(cooridnation);
		cooridnation.setActor(null);

		return cooridnation;
	}

	public List<Participation> getParticipations() {
		return this.participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public Participation addParticipant(Participation participation) {
		getParticipations().add(participation);
		participation.setActor(this);

		return participation;
	}

	public Participation removeParticipant(Participation participation) {
		getParticipations().remove(participation);
		participation.setActor(null);

		return participation;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}