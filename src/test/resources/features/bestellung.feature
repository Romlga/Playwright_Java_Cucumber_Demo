Feature: Bestellprozess im Demo-Online-Shop

  @Teil1 @Bestellprozess_1
  Scenario: Bestellprozess - Teil 1
    Given ich öffne den Demo-Shop
    Then die Überschrift "Shop" ist sichtbar
    And der Warenkorb ist leer
    When ich den Test pausieren möchte bis "2025-08-28T16:04:00"

  @Teil1 @Bestellablauf_1
  Scenario: Bestellablauf - Teil 1
    Given ich öffne den Demo-Shop
    Then die Überschrift "Shop" ist sichtbar
    And der Warenkorb ist leer
    When ich den Test pausieren möchte bis "2025-08-28T16:05:00"

  @Bestellprozess_2
  Scenario: Bestellprozess - Teil 2
    Given ich öffne den Demo-Shop
    When ich den Test pausieren möchte bis "2025-08-28T16:10:00"

  @Bestellprozess_3
  Scenario: Bestellprozess - Teil 3
      Given ich öffne den Demo-Shop
      When ich lege das Produkt "Album" in den Warenkorb
      And ich öffne den Warenkorb
      And ich setze die Anzahl von "Album" auf 2
      And ich aktualisiere den Warenkorb
      Then der Gesamtpreis ändert sich
      When ich gehe zur Kasse
      And ich fülle alle Pflichtfelder mit Testdaten aus
      Then ist der Button "Bestellung abschicken" aktiv

  @Bestellablauf_2
  Scenario: Bestellablauf - Teil 2
    Given ich öffne den Demo-Shop
    When ich lege das Produkt "Album" in den Warenkorb
    And ich öffne den Warenkorb
    And ich setze die Anzahl von "Album" auf 2
    And ich aktualisiere den Warenkorb
    Then der Gesamtpreis ändert sich
    When ich gehe zur Kasse
    And ich fülle alle Pflichtfelder mit Testdaten aus
    Then ist der Button "Bestellung abschicken" aktiv
