package com.cordis.model;

import java.io.Serializable;


/**
 * The persistent class for the action database table.
 * 
 */
public class ActionType implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String SIGNUP="SIGNUP";
	public static final String LOGIN="LOGIN";
	public static final String LOGOUT="LOGOUT";
	public static final String SEARCH_ACTOR="SEARCH_ACTOR";
	public static final String SEARCH_PROJECT="SEARCH_PROJECT";
	public static final String SEARCH_USER_ACTION="SEARCH_USER_ACTION";
	public static final String SEARCH_DASHBOARD="SEARCH_DASHBOARD";

	private int actionId;

	private String actionName;

	public ActionType() {
	}

	public int getActionId() {
		return this.actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}