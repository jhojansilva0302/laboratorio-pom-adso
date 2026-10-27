package com.adso.pom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By jobTitleInput = By.id("job-title");
    private final By radioButtonCollege = By.id("radio-button-2");
    private final By sexCheckboxFemale = By.id("checkbox-2");
    private final By experienceSelect = By.id("select-menu");
    private final By dateInput = By.id("datepicker");
    private final By submitButton = By.xpath("//a[contains(text(),'Submit')]");
    private final By successAlert = By.xpath("//div[contains(@class, 'alert-success')]");

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void ingresarNombreCompleto(String nombre, String apellido) {
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstName.clear();
        firstName.sendKeys(nombre);

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        lastName.clear();
        lastName.sendKeys(apellido);
    }

    public void ingresarCargo(String cargo) {
        WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(jobTitleInput));
        jobTitle.clear();
        jobTitle.sendKeys(cargo);
    }

    public void seleccionarNivelEducativo() {
        WebElement collegeOption = wait.until(ExpectedConditions.elementToBeClickable(radioButtonCollege));
        if (!collegeOption.isSelected()) {
            collegeOption.click();
        }
    }

    public void seleccionarSexo() {
        WebElement femaleOption = wait.until(ExpectedConditions.elementToBeClickable(sexCheckboxFemale));
        if (!femaleOption.isSelected()) {
            femaleOption.click();
        }
    }

    public void seleccionarExperiencia(String textoVisible) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(experienceSelect));
        Select select = new Select(dropdown);
        select.selectByVisibleText(textoVisible);
    }

    public void ingresarFecha(String fecha) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));
        dateField.clear();
        dateField.sendKeys(fecha);
    }

    public void enviarFormulario() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        button.click();
    }

    public String obtenerMensajeConfirmacion() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(successAlert));
        return message.getText();
    }
}
