package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class BestellungSteps {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;
    private double alterGesamtpreis;

    @Given("ich öffne den Demo-Shop")
    public void ich_oeffne_den_demo_shop() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://demoproject.simplytest.de/");
    }

    @Then("die Überschrift {string} ist sichtbar")
    public void die_ueberschrift_ist_sichtbar(String ueberschrift) {
        assertTrue(page.isVisible("h1:has-text('" + ueberschrift + "')"));
    }

    @Then("der Warenkorb ist leer")
    public void der_warenkorb_ist_leer() {
        String amount = page.textContent("a.cart-contents .woocommerce-Price-amount");
        String count = page.textContent("a.cart-contents .count");
        boolean isEmpty = amount.contains("0,00") && (count.contains("0 items") || count.contains("0 Artikel"));
        assertTrue("Warenkorb ist nicht leer: Betrag=" + amount + ", Anzahl=" + count, isEmpty);
    }

    @When("ich lege das Produkt {string} in den Warenkorb")
    public void ich_lege_produkt_in_warenkorb(String produkt) {
        // Finde das Listenelement mit Überschrift 'Album' und klicke auf den Add-to-cart-Button
        String selector = "li:has(h2:has-text('" + produkt + "')) a.button";
        page.click(selector);
        page.waitForTimeout(1000); // Kurze Wartezeit für UI-Update
    }

    @When("ich öffne den Warenkorb")
    public void ich_oeffne_den_warenkorb() {
        page.click("a[href*='/cart/']");
        page.waitForURL("**/cart/");
    }

    @When("ich setze die Anzahl von {string} auf 2")
    public void ich_setze_anzahl_auf_2(String produkt) {
        page.fill("input.qty", "2");
    }

    @When("ich aktualisiere den Warenkorb")
    public void ich_aktualisiere_den_warenkorb() {
        String preisText = page.textContent("td.product-subtotal bdi");
        preisText = preisText.replace("€","").replace(",",".").replace("\u00A0", "").replaceAll("\\s+", "").trim();
        alterGesamtpreis = Double.parseDouble(preisText);
        page.click("button[name='update_cart']");
        page.waitForTimeout(1000);
    }

    @Then("der Gesamtpreis ändert sich")
    public void der_gesamtpreis_aendert_sich() {
        String neuerPreisText = page.textContent("td.product-subtotal bdi");
        neuerPreisText = neuerPreisText.replace("€","").replace(",",".").replace("\u00A0", "").replaceAll("\\s+", "").trim();
        double neuerPreis = Double.parseDouble(neuerPreisText);
        assertTrue(neuerPreis > alterGesamtpreis);
    }

    @When("ich gehe zur Kasse")
    public void ich_gehe_zur_kasse() {
        page.click(".checkout-button");
        page.waitForURL("**/checkout/");
    }

    @When("ich fülle alle Pflichtfelder mit Testdaten aus")
    public void ich_fuelle_alle_pflichtfelder_aus() {
        page.fill("#billing_first_name", "Max");
        page.fill("#billing_last_name", "Mustermann");
        page.fill("#billing_address_1", "Teststraße 1");
        page.fill("#billing_postcode", "12345");
        page.fill("#billing_city", "Teststadt");
        page.fill("#billing_phone", "0123456789");
        page.fill("#billing_email", "max@example.com");
        // Land auf Deutschland setzen
        page.selectOption("#billing_country", "DE");
    }

    @Then("ist der Button {string} aktiv")
    public void ist_button_aktiv(String buttonText) {
        // Button auf der Checkout-Seite: id="place_order"
        boolean enabled = page.isEnabled("#place_order");
        assertTrue("Bestellung abschicken Button ist nicht aktiv!", enabled);
        browser.close();
        playwright.close();
    }
}
