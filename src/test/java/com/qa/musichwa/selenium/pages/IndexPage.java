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
	
	@FindBy(className = "del-btn")
	private WebElement delButton;
	
	@FindBy(id = "reset-btn")
	private WebElement resetButton;
	
	@FindBy(className = "edit-btn")
	private WebElement editButton;
	
	@FindBy(className = "alert-success")
	private WebElement successAlert;
	
	@FindBy(id = "results-div")
	private WebElement resultsDiv;
	
	@FindBy(id = "edit-modal")
	private WebElement editModal;

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

	public WebElement getDelButton() {
		return delButton;
	}
	
	public WebElement getEditButton() {
		return editButton;
	}

	public WebElement getSuccessAlert() {
		return successAlert;
	}

	public WebElement getResetButton() {
		return resetButton;
	}

	public WebElement getResultsDiv() {
		return resultsDiv;
	}

	public WebElement getEditModal() {
		return editModal;
	}
}
