package com.qa.musichwa.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {

	@FindBy(id = "create-btn")
	private WebElement createBtn;
	
	@FindBy(name = "name")
	private WebElement nameInput;
	
	@FindBy(name = "artist")
	private WebElement artistInput;
	
	@FindBy(name = "year")
	private WebElement yearInput;
	
	@FindBy(name = "type")
	private WebElement typeInput;
	
	public WebElement getNameInput() {
		return nameInput;
	}

	public WebElement getArtistInput() {
		return artistInput;
	}

	public WebElement getYearInput() {
		return yearInput;
	}

	public WebElement getTypeInput() {
		return typeInput;
	}

	public WebElement getCreateBtn() {
		return createBtn;
	}
}
