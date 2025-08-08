package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import entity.TestContext;
import org.springframework.web.reactive.function.client.WebClient;

public class BestellungSteps {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private double alterGesamtpreis;
    private String variablesJsonFromResume;

    // Hilfsmethode, um die Test-Variablen vom Service zu holen und auszugeben
    private void ich_hole_die_test_variablen_vom_service() {
        TestContext context = WebClient.create("http://localhost:8080")
            .get()
            .uri("/resume/resume")
            .retrieve()
            .bodyToMono(TestContext.class)
            .block();
        if (context != null && context.getVariablesJson() != null) {
            variablesJsonFromResume = context.getVariablesJson();
            System.out.println("[Teil 2] variablesJson: " + variablesJsonFromResume);
        } else {
            System.out.println("[Teil 2] variablesJson: <leer oder nicht gefunden>");
        }
    }

    @Given("ich öffne den Demo-Shop")
    public void ich_oeffne_den_demo_shop() {
        System.out.println("Step: ich öffne den Demo-Shop");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://demoproject.simplytest.de/");
    }

    @Then("die Überschrift {string} ist sichtbar")
    public void die_ueberschrift_ist_sichtbar(String ueberschrift) {
        System.out.println("START Step: die Überschrift '" + ueberschrift + "' ist sichtbar");
        assertTrue(page.isVisible("h1:has-text('" + ueberschrift + "')"));
        System.out.println("END Step: die Überschrift '" + ueberschrift + "' ist sichtbar");
    }

    @Then("der Warenkorb ist leer")
    public void der_warenkorb_ist_leer() {
        System.out.println("START Step: der Warenkorb ist leer");
        String amount = page.textContent("a.cart-contents .woocommerce-Price-amount");
        String count = page.textContent("a.cart-contents .count");
        boolean isEmpty = amount.contains("0,00") && (count.contains("0 items") || count.contains("0 Artikel"));
        assertTrue("Warenkorb ist nicht leer: Betrag=" + amount + ", Anzahl=" + count, isEmpty);
        System.out.println("END Step: der Warenkorb ist leer");
    }

    @When("ich lege das Produkt {string} in den Warenkorb")
    public void ich_lege_produkt_in_warenkorb(String produkt) {
        System.out.println("START Step: ich lege das Produkt '" + produkt + "' in den Warenkorb");
        // Finde das Listenelement mit Überschrift 'Album' und klicke auf den Add-to-cart-Button
        String selector = "li:has(h2:has-text('" + produkt + "')) a.button";
        page.click(selector);
        page.waitForTimeout(1000); // Kurze Wartezeit für UI-Update
        System.out.println("END Step: ich lege das Produkt '" + produkt + "' in den Warenkorb");
    }

    @When("ich öffne den Warenkorb")
    public void ich_oeffne_den_warenkorb() {
        System.out.println("START Step: ich öffne den Warenkorb");
        page.click("a[href*='/cart/']");
        page.waitForURL("**/cart/");
        System.out.println("END Step: ich öffne den Warenkorb");
    }

    @When("ich setze die Anzahl von {string} auf 2")
    public void ich_setze_anzahl_auf_2(String produkt) {
        System.out.println("START Step: ich setze die Anzahl von '" + produkt + "' auf 2");
        page.fill("input.qty", "2");
        System.out.println("END Step: ich setze die Anzahl von '" + produkt + "' auf 2");
        // Gebe die Test-Variablen nach dem Setzen der Anzahl aus
        ich_hole_die_test_variablen_vom_service();
    }

    @When("ich aktualisiere den Warenkorb")
    public void ich_aktualisiere_den_warenkorb() {
        System.out.println("START Step: ich aktualisiere den Warenkorb");
        String preisText = page.textContent("td.product-subtotal bdi");
        preisText = preisText.replace("€","").replace(",",".").replace("\u00A0", "").replaceAll("\\s+", "").trim();
        alterGesamtpreis = Double.parseDouble(preisText);
        page.click("button[name='update_cart']");
        page.waitForTimeout(1000);
        System.out.println("END Step: ich aktualisiere den Warenkorb");
    }

    @Then("der Gesamtpreis ändert sich")
    public void der_gesamtpreis_aendert_sich() {
        System.out.println("START Step: der Gesamtpreis ändert sich");
        String neuerPreisText = page.textContent("td.product-subtotal bdi");
        neuerPreisText = neuerPreisText.replace("€","").replace(",",".").replace("\u00A0", "").replaceAll("\\s+", "").trim();
        double neuerPreis = Double.parseDouble(neuerPreisText);
        assertTrue(neuerPreis > alterGesamtpreis);
        System.out.println("END Step: der Gesamtpreis ändert sich");
    }

    @When("ich gehe zur Kasse")
    public void ich_gehe_zur_kasse() {
        System.out.println("START Step: ich gehe zur Kasse");
        page.click(".checkout-button");
        page.waitForURL("**/checkout/");
        System.out.println("END Step: ich gehe zur Kasse");
    }

    @When("ich fülle alle Pflichtfelder mit Testdaten aus")
    public void ich_fuelle_alle_pflichtfelder_aus() {
        System.out.println("START Step: ich fülle alle Pflichtfelder mit Testdaten aus");
        page.fill("#billing_first_name", "Max");
        page.fill("#billing_last_name", "Mustermann");
        page.fill("#billing_address_1", "Teststraße 1");
        page.fill("#billing_postcode", "12345");
        page.fill("#billing_city", "Teststadt");
        page.fill("#billing_phone", "0123456789");
        page.fill("#billing_email", "max@example.com");
        // Land auf Deutschland setzen
        page.selectOption("#billing_country", "DE");
        System.out.println("END Step: ich fülle alle Pflichtfelder mit Testdaten aus");
    }

    @Then("ist der Button {string} aktiv")
    public void ist_button_aktiv(String buttonText) {
        System.out.println("START Step: ist der Button '" + buttonText + "' aktiv");
        // Button auf der Checkout-Seite: id="place_order"
        boolean enabled = page.isEnabled("#place_order");
        assertTrue("Bestellung abschicken Button ist nicht aktiv!", enabled);
        browser.close();
        playwright.close();
        System.out.println("END Step: ist der Button '" + buttonText + "' aktiv");
    }
}
