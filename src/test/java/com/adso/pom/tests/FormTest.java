package com.adso.pom.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.adso.pom.pages.FormPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FormTest {
    private WebDriver driver;
    private FormPage formPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://formy-project.herokuapp.com/form");
        formPage = new FormPage(driver);
    }

    @Test
    @DisplayName("Diligenciar formulario y validar envío exitoso")
    public void shouldSubmitFormSuccessfully() {
        formPage.ingresarNombreCompleto("Ana", "García");
        formPage.ingresarCargo("Analista de QA");
        formPage.seleccionarNivelEducativo();
        formPage.seleccionarSexo();
        formPage.seleccionarExperiencia("2-4");
        formPage.ingresarFecha("01/15/2025");
        formPage.enviarFormulario();

        String mensaje = formPage.obtenerMensajeConfirmacion();
        assertTrue(mensaje.contains("successfully"), "El formulario no se envió correctamente: " + mensaje);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
