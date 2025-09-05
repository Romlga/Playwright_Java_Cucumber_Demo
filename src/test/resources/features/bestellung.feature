Feature: Bestellprozess im Demo-Online-Shop

  @Teil1 @Bestellprozess_1
  Scenario: Bestellprozess - Teil 1 aus 2
    Given ich öffne den Demo-Shop
    Then die Überschrift "Shop" ist sichtbar
    And der Warenkorb ist leer
    When ich den Test pausieren möchte bis "2025-09-05T11:59:00"


  @Bestellprozess_2
  Scenario: Bestellprozess - Teil 2 aus 2
    Given ich öffne den Demo-Shop
  

