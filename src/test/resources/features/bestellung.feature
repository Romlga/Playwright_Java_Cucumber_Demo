Feature: Bestellprozess im Demo-Online-Shop

  Scenario: Mehrstufiger Bestellvorgang
    Given ich öffne den Demo-Shop
    Then die Überschrift "Shop" ist sichtbar
    And der Warenkorb ist leer
    When ich lege das Produkt "Album" in den Warenkorb
    And ich öffne den Warenkorb
    And ich setze die Anzahl von "Album" auf 2
    And ich aktualisiere den Warenkorb
    Then der Gesamtpreis ändert sich
    When ich gehe zur Kasse
    And ich fülle alle Pflichtfelder mit Testdaten aus
    Then ist der Button "Bestellung abschicken" aktiv
