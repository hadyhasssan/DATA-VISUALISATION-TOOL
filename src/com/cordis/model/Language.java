package com.cordis.model;

import java.io.Serializable;
import java.util.List;

public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	private String languageCode;

	private String languageName;

	private List<ActivityType> activityTypes;

	private List<FundingSchemeCategory> fundingSchemeCategories;

	public Language() {
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageName() {
		return this.languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public List<ActivityType> getActivityTypes() {
		return this.activityTypes;
	}

	public void setActivityTypes(List<ActivityType> activityTypes) {
		this.activityTypes = activityTypes;
	}

	public ActivityType addActivityType(ActivityType activityType) {
		getActivityTypes().add(activityType);
		activityType.setLanguage(this);

		return activityType;
	}

	public ActivityType removeActivityType(ActivityType activityType) {
		getActivityTypes().remove(activityType);
		activityType.setLanguage(null);

		return activityType;
	}

	public List<FundingSchemeCategory> getFundingSchemeCategories() {
		return this.fundingSchemeCategories;
	}

	public void setFundingSchemeCategories(List<FundingSchemeCategory> fundingSchemeCategories) {
		this.fundingSchemeCategories = fundingSchemeCategories;
	}

	public FundingSchemeCategory addFundingSchemeCategory(FundingSchemeCategory fundingSchemeCategory) {
		getFundingSchemeCategories().add(fundingSchemeCategory);
		fundingSchemeCategory.setLanguage(this);

		return fundingSchemeCategory;
	}

	public FundingSchemeCategory removeFundingSchemeCategory(FundingSchemeCategory fundingSchemeCategory) {
		getFundingSchemeCategories().remove(fundingSchemeCategory);
		fundingSchemeCategory.setLanguage(null);

		return fundingSchemeCategory;
	}

}