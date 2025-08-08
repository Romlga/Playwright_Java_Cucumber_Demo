Feature: Bestellprozess im Demo-Online-Shop

  @initial
  Scenario: Bestellprozess - Teil 1
    Given ich öffne den Demo-Shop
    Then die Überschrift "Shop" ist sichtbar
    And der Warenkorb ist leer
    When ich den Test pausieren möchte bis "2025-08-08T09:09:00"

  @Bestellprozess_2
  Scenario: Bestellprozess - Teil 2
    Given ich öffne den Demo-Shop
    When ich lege das Produkt "Album" in den Warenkorb
    And ich öffne den Warenkorb
    And ich setze die Anzahl von "Album" auf 2
    And ich aktualisiere den Warenkorb
    Then der Gesamtpreis ändert sich
    When ich gehe zur Kasse
    And ich fülle alle Pflichtfelder mit Testdaten aus
    Then ist der Button "Bestellung abschicken" aktiv
