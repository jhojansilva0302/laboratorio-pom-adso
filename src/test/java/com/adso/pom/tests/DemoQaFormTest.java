package com.adso.pom.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.adso.pom.pages.DemoQaFormPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoQaFormTest {
    private WebDriver driver;
    private DemoQaFormPage formPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/automation-practice-form");
        formPage = new DemoQaFormPage(driver);
    }

    @Test
    @DisplayName("Completar el formulario DemoQA y confirmar el registro")
    public void shouldSubmitCompleteFormSuccessfully() {
        File picture = new File("src/test/resources/sample-upload.svg");

        formPage.ingresarDatos("Ana", "Garcia", "ana.garcia@example.com", "3001234567");
        formPage.seleccionarFechaNacimiento("15 May 2000");
        formPage.seleccionarSubject("Maths");
        formPage.seleccionarPasatiempoDeportivo();
        formPage.subirArchivo(picture.getAbsolutePath());
        formPage.ingresarDireccion("Calle 10 # 20-30");
        formPage.enviarFormulario();

        assertTrue(formPage.seMuestraConfirmacion(), "Debe aparecer el modal de confirmación.");
        assertEquals("Thanks for submitting the form", formPage.obtenerTituloConfirmacion());
        assertTrue(formPage.obtenerDatosConfirmados().contains("Ana Garcia"));
        assertTrue(formPage.obtenerDatosConfirmados().contains("Maths"));
    }

    @Test
    @DisplayName("Rechazar el envío cuando falta el nombre obligatorio")
    public void shouldRejectFormWhenRequiredNameIsMissing() {
        formPage.enviarFormulario();

        assertEquals(true, formPage.faltaNombreRequerido(),
                "El navegador debe marcar el nombre vacío como campo obligatorio.");
        assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
