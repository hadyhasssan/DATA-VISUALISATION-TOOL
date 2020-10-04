package com.cordis.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	private long projectId;

	private String acronym;

	private String call;

	private double ecMaxContribution;

	private Date endDate;

	private String objective;

	private String projectUrl;

	private String rcn;

	private Date startDate;

	private String status;

	private String subjects;

	private String title;

	private List<Topic> topics;

	private String topic;

	private double totalCost;

	private Cooridnation cooridnation;

	private List<Participation> participations;

	private List<Actor> actors;

	private FrameworkProgramme frameworkProgrammeBean;

	private FundingSchemeCategory fundingSchemeCategoryBean;

	private List<Programme> programmes;

	public Project() {
	}

	public long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getAcronym() {
		return this.acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getCall() {
		return this.call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public double getEcMaxContribution() {
		return this.ecMaxContribution;
	}

	public void setEcMaxContribution(double ecMaxContribution) {
		this.ecMaxContribution = ecMaxContribution;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getObjective() {
		return this.objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getProjectUrl() {
		return this.projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	public String getRcn() {
		return this.rcn;
	}

	public void setRcn(String rcn) {
		this.rcn = rcn;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubjects() {
		return this.subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public double getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Cooridnation getCooridnation() {
		return this.cooridnation;
	}

	public void setCooridnation(Cooridnation cooridnation) {
		this.cooridnation = cooridnation;
	}

	public List<Participation> getParticipations() {
		return this.participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public Participation addParticipant(Participation participation) {
		getParticipations().add(participation);
		participation.setProject(this);

		return participation;
	}

	public Participation removeParticipant(Participation participation) {
		getParticipations().remove(participation);
		participation.setProject(null);

		return participation;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public FrameworkProgramme getFrameworkProgrammeBean() {
		return this.frameworkProgrammeBean;
	}

	public void setFrameworkProgrammeBean(FrameworkProgramme frameworkProgrammeBean) {
		this.frameworkProgrammeBean = frameworkProgrammeBean;
	}

	public FundingSchemeCategory getFundingSchemeCategoryBean() {
		return this.fundingSchemeCategoryBean;
	}

	public void setFundingSchemeCategoryBean(FundingSchemeCategory fundingSchemeCategoryBean) {
		this.fundingSchemeCategoryBean = fundingSchemeCategoryBean;
	}

	public List<Programme> getProgrammes() {
		return this.programmes;
	}

	public void setProgrammes(List<Programme> programmes) {
		this.programmes = programmes;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}