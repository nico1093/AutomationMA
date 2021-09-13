Feature: test Mercantil Andina
  Se realizaran pruebas en: (https://www.mercantilandina.com.ar) con el navegador (Google Chrome)

  @Test
  Scenario: Validaciones de cotizacion Mercantil Andina.
    Given Acceder a la web de mercantil andina
    Then Cargar la informacion DOM POMIndexMAndina.json
    Then Hacer doble Clic sobre seguros personales
    Then Esperar hasta 2 segundos
    And Hacer clic en el elemento hogar
    Then Cargar la informacion DOM POMHogarMAndina.json
    And Hacer clic en el elemento cotizar
    Then Cargar la informacion DOM POMCotizarMAndina.json
    And Completar el campo nombre con el texto Nicolas Martin
    And Completar el campo telefono con el texto 42983032
    And Completar el campo email con el texto prueba_MAndina@gmail.com
    And Seleccionar en el despegable vivienda el valor Casa
    And Seleccionar en el despegable superficie el valor 90 a 100 m2
    And Seleccionar en el despegable ubicacion el valor CABA
    And Hacer clic en el elemento boton cotizar
    Then Esperar hasta 5 segundos
    Then Cargar la informacion DOM POMValidacionesMAndina.json
    Then Validar el costo total en la cotizacion

  @Test
  Scenario: Validar titulo web Seguro Hogar - Mercantil Andina
    Given Acceder a la web de mercantil andina
    Then Cargar la informacion DOM POMIndexMAndina.json
    Then Hacer doble Clic sobre seguros personales
    Then Esperar hasta 2 segundos
    And Hacer clic en el elemento hogar
    Then Validar que el titulo de la Web sea Seguro de hogar - Mercantil Andina
    Then Cargar la informacion DOM POMHogarMAndina.json
    And Hacer clic en el elemento cotizar
    Then Validar que el titulo de la Web sea Seguro de hogar - Mercantil Andina
    Then Cargar la informacion DOM POMCotizarMAndina.json
    And Completar el campo nombre con el texto Nicolas Martin
    And Completar el campo telefono con el texto 42983032
    And Completar el campo email con el texto prueba_MAndina@gmail.com
    And Seleccionar en el despegable vivienda el valor Casa
    And Seleccionar en el despegable superficie el valor 90 a 100 m2
    And Seleccionar en el despegable ubicacion el valor CABA
    And Hacer clic en el elemento boton cotizar
    Then Esperar hasta 5 segundos
    Then Validar que el titulo de la Web sea Seguro de hogar - Mercantil Andina

  @Test
  Scenario: Validar visual de chat online
    Given Acceder a la web de mercantil andina
    Then Cargar la informacion DOM POMIndexMAndina.json
    Then Hacer doble Clic sobre seguros personales
    Then Esperar hasta 2 segundos
    And Hacer clic en el elemento hogar
    Then Cargar la informacion DOM POMHogarMAndina.json
    And Hacer clic en el elemento cotizar
    Then Cargar la informacion DOM POMCotizarMAndina.json
    And Completar el campo nombre con el texto Nicolas Martin
    And Completar el campo telefono con el texto 42983032
    And Completar el campo email con el texto prueba_MAndina@gmail.com
    And Seleccionar en el despegable vivienda el valor Casa
    And Seleccionar en el despegable superficie el valor 90 a 100 m2
    And Seleccionar en el despegable ubicacion el valor CABA
    And Hacer clic en el elemento boton cotizar
    Then Esperar hasta 5 segundos
    Then Cargar la informacion DOM POMValidacionesMAndina.json
    When Cambiar al Frame: chat frame
    And Hacer clic en el elemento boton chat online
    Then Esperar hasta 3 segundos
    Then Validar que el elemento chat se visualiza.
