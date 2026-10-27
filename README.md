# Laboratorio 6: Selenium con Page Object Model

Proyecto Maven de automatización E2E con Java, Selenium WebDriver, WebDriverManager y JUnit 5.

## Requisitos

- JDK 17 o superior
- Maven 3.8 o superior
- Google Chrome
- Acceso a Internet para descargar dependencias y abrir las aplicaciones de demostración

## Ejecutar las pruebas

Desde la raíz del proyecto:

```shell
mvn test
```

La suite incluye:

- `FormTest`: diligencia el formulario Formy y verifica el mensaje de envío exitoso.
- `DemoQaFormTest`: ejecuta el reto final en DemoQA, validando un registro exitoso y el rechazo de un envío sin el nombre obligatorio.

Las clases de página están en `src/main/java/com/adso/pom/pages`; las pruebas, en `src/test/java/com/adso/pom/tests`. Los localizadores DOM se mantienen privados dentro de sus clases de página.
