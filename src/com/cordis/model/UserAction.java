package com.cordis.model;

import java.sql.Date;

public class UserAction {

    private User user;
    private ActionType actionType;
    private Date actionDate;
    private String actionParameters;
    private String username="";

    public String getUsername() {
        if (user!=null){
            username = user.getUsername();
        }
        return username;
    }

    public String getActionName() {
        if (actionType!=null){
            actionName = actionType.getActionName();
        }
        return actionName;
    }

    private String actionName="";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public String getActionParameters() {
        return actionParameters;
    }

    public void setActionParameters(String actionParameters) {
        this.actionParameters = actionParameters;
    }

    @Override
    public String toString() {
        return "UserAction{" +
                "user=" + user +
                ", actionType=" + actionType +
                ", actionDate=" + actionDate +
                ", actionParameters='" + actionParameters + '\'' +
                ", username='" + getUsername() + '\'' +
                ", actionName='" + getActionName() + '\'' +
                '}';
    }
}
