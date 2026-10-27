package com.adso.pom.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQaFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("userEmail");
    private final By maleRadio = By.cssSelector("label[for='gender-radio-1']");
    private final By mobileInput = By.id("userNumber");
    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By subjectsInput = By.id("subjectsInput");
    private final By sportsCheckbox = By.cssSelector("label[for='hobbies-checkbox-1']");
    private final By uploadInput = By.id("uploadPicture");
    private final By currentAddressInput = By.id("currentAddress");
    private final By submitButton = By.id("submit");
    private final By confirmationTitle = By.id("example-modal-sizes-title-lg");
    private final By confirmationTable = By.cssSelector(".table-responsive");

    public DemoQaFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void ingresarDatos(String nombre, String apellido, String correo, String celular) {
        escribir(firstNameInput, nombre);
        escribir(lastNameInput, apellido);
        escribir(emailInput, correo);
        seleccionarGeneroMasculino();
        escribir(mobileInput, celular);
    }

    public void seleccionarGeneroMasculino() {
        wait.until(ExpectedConditions.elementToBeClickable(maleRadio)).click();
    }

    public void seleccionarFechaNacimiento(String fecha) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(dateOfBirthInput));
        dateField.sendKeys(Keys.CONTROL, "a");
        dateField.sendKeys(fecha);
        dateField.sendKeys(Keys.ENTER);
    }

    public void seleccionarSubject(String subject) {
        WebElement subjectField = wait.until(ExpectedConditions.visibilityOfElementLocated(subjectsInput));
        subjectField.sendKeys(subject);
        subjectField.sendKeys(Keys.ENTER);
    }

    public void seleccionarPasatiempoDeportivo() {
        wait.until(ExpectedConditions.elementToBeClickable(sportsCheckbox)).click();
    }

    public void subirArchivo(String rutaArchivo) {
        wait.until(ExpectedConditions.presenceOfElementLocated(uploadInput)).sendKeys(rutaArchivo);
    }

    public void ingresarDireccion(String direccion) {
        escribir(currentAddressInput, direccion);
    }

    public void enviarFormulario() {
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();
    }

    public boolean seMuestraConfirmacion() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationTitle)).isDisplayed();
    }

    public String obtenerTituloConfirmacion() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationTitle)).getText();
    }

    public String obtenerDatosConfirmados() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationTable)).getText();
    }

    public boolean faltaNombreRequerido() {
        WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(firstNameInput));
        return Boolean.TRUE.equals(((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validity.valueMissing;", firstName));
    }

    private void escribir(By locator, String valor) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        input.clear();
        input.sendKeys(valor);
    }
}
