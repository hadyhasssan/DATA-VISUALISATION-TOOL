package com.cordis.model;

import java.io.Serializable;
import java.util.List;

public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	private String countryCode;

	private String countryName;


	private List<Actor> actors;

	public Country() {
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Actor addActor(Actor actor) {
		getActors().add(actor);
		actor.setCountry(this);

		return actor;
	}

	public Actor removeActor(Actor actor) {
		getActors().remove(actor);
		actor.setCountry(null);

		return actor;
	}

	@Override
	public String toString() {
		return "Country{" +
				"countryCode='" + countryCode + '\'' +
				", countryName='" + countryName + '\'' +
				", actors=" + actors +
				'}';
	}
}