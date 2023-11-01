package com.itl.web.dto;

public class CountryVO {

	private String countryId = "";
	
	private String countryName ="";
	
	private String countryDisplayName ="";
	
	private String countryAbbr="";

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryDisplayName() {
		return countryDisplayName;
	}

	public void setCountryDisplayName(String countryDisplayName) {
		this.countryDisplayName = countryDisplayName;
	}

	public String getCountryAbbr() {
		return countryAbbr;
	}

	public void setCountryAbbr(String countryAbbr) {
		this.countryAbbr = countryAbbr;
	}
}
