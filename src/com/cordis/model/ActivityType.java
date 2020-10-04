package com.cordis.model;

import java.io.Serializable;
import java.util.List;


public class ActivityType implements Serializable {
    private static final long serialVersionUID = 1L;

    private String activityTypeCode;

    private String activityTypeDescription;

    private String activityTypeTitle;

    private Language language;

    private List<Actor> actors;

    public ActivityType() {
    }

    public String getActivityTypeCode() {
        return this.activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getActivityTypeDescription() {
        return this.activityTypeDescription;
    }

    public void setActivityTypeDescription(String activityTypeDescription) {
        this.activityTypeDescription = activityTypeDescription;
    }

    public String getActivityTypeTitle() {
        return this.activityTypeTitle;
    }

    public void setActivityTypeTitle(String activityTypeTitle) {
        this.activityTypeTitle = activityTypeTitle;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Actor> getActors() {
        return this.actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Actor addActor(Actor actor) {
        getActors().add(actor);
        actor.setActivityTypeBean(this);

        return actor;
    }

    public Actor removeActor(Actor actor) {
        getActors().remove(actor);
        actor.setActivityTypeBean(null);

        return actor;
    }

    @Override
    public String toString() {
        return "ActivityType{" +
                "activityTypeCode='" + activityTypeCode + '\'' +
                ", activityTypeDescription='" + activityTypeDescription + '\'' +
                ", activityTypeTitle='" + activityTypeTitle + '\'' +
                ", language=" + language +
                '}';
    }
}