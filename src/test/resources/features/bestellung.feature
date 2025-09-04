Feature: Bestellprozess im Demo-Online-Shop

  @Teil1 @Bestellprozess_1
  Scenario: Bestellprozess - Teil 1 aus 3
    Given ich öffne den Demo-Shop
    Then die Überschrift "Shop" ist sichtbar
    And der Warenkorb ist leer
    When ich den Test pausieren möchte bis "2025-09-04T09:49:00"

  @Teil1 @Ablauf_1
  Scenario: Ablauf - Teil 1 aus 2
    Given ich öffne den Demo-Shop
    When ich den Test pausieren möchte bis "2025-09-04T09:57:00"

  @Teil1 @Test_1
  Scenario: Test - Teil 1 aus 1
  Given ich öffne den Demo-Shop


  @Ablauf_2
  Scenario: Ablauf - Teil 2 aus 2
    Given ich öffne den Demo-Shop

  @Bestellprozess_2
  Scenario: Bestellprozess - Teil 2 aus 3
    Given ich öffne den Demo-Shop
    When ich den Test pausieren möchte bis "2025-09-04T09:56:00"


  @Bestellprozess_3
  Scenario: Bestellprozess - Teil 3 aus 3
    Given ich öffne den Demo-Shop
    When ich lege das Produkt "Album" in den Warenkorb
    And ich öffne den Warenkorb
    And ich setze die Anzahl von "Album" auf 2
    And ich aktualisiere den Warenkorb
    Then der Gesamtpreis ändert sich
    When ich gehe zur Kasse
    And ich fülle alle Pflichtfelder mit Testdaten aus
    Then ist der Button "Bestellung abschicken" aktiv


