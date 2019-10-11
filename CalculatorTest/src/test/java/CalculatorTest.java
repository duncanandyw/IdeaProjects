import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

// import org.junit.Test;

public class CalculatorTest {

    @Test(alwaysRun = true)
    public void openCalculator() {
        // choose our browser for testing
        // this must be changed when we run different headless tests also
        // chrome is the default in selenide, so this is for clarity
        System.setProperty("selenide.browser", "Chrome");
        System.err.println("*** Using Chrome ***");
//        System.setProperty("selenide.browser", "Firefox");
//        System.err.println("*** Using Firefox ***");

        // open our URL
        open("https://duffmanns.github.io/calc-test/calculator/app/index.html");
        // verify the correct URL loaded
        assert(title().equals("Calculator"));

        // TODO:
        // verify elements of calculator
        // add more incorrect input tests
        // methods for repeated code

    }

    @Test(alwaysRun = true, dependsOnMethods = "openCalculator")
    public void simpleAddition () {
        // A. Simple Addition
        // 1. 1 + 3

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number one button
        $("#one").click();
        // verify the number one shows
        $("#display > div").shouldBe(text("1"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number one still shows (memory was empty)
        $("#display > div").shouldBe(text("1"));

        // click on the number three button
        $("#three").click();
        // verify the number three shows
        $("#display > div").shouldBe(text("3"));

        // click on the equals sign
        $("#equals").click();
        // verify the number four shows, as 1 + 3 = 4
        $("#display > div").shouldBe(text("4"));

        // 2. 5 + 6

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number five button
        $("#five").click();
        // verify the number five shows
        $("#display > div").shouldBe(text("5"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number five still shows (memory was empty)
        $("#display > div").shouldBe(text("5"));

        // click on the number six button
        $("#six").click();
        // verify the number six shows
        $("#display > div").shouldBe(text("6"));

        // click on the equals sign
        $("#equals").click();
        // verify the number eleven shows, as 5 + 6 = 11
        $("#display > div").shouldBe(text("11"));

        // 3. 3 + 7

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number three button
        $("#three").click();
        // verify the number three shows
        $("#display > div").shouldBe(text("3"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number three still shows (memory was empty)
        $("#display > div").shouldBe(text("3"));

        // click on the number seven button
        $("#seven").click();
        // verify the number seven shows
        $("#display > div").shouldBe(text("7"));

        // click on the equals sign
        $("#equals").click();
        // verify the number ten shows, as 3 + 7 = 10
        $("#display > div").shouldBe(text("10"));

        // 4. Add all of the three together to make the whole total

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number one button
        $("#one").click();
        // verify the number one shows
        $("#display > div").shouldBe(text("1"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number one still shows (memory was empty)
        $("#display > div").shouldBe(text("1"));

        // click on the number three button
        $("#three").click();
        // verify the number three shows
        $("#display > div").shouldBe(text("3"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number four now shows (we have added to our total)
        // since 1 + 3 = 4
        $("#display > div").shouldBe(text("4"));

        // click on the number five button
        $("#five").click();
        // verify the number five shows
        $("#display > div").shouldBe(text("5"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number nine now shows (we have added to our total)
        // since 1 + 3 + 5 = 9
        $("#display > div").shouldBe(text("9"));

        // click on the number six button
        $("#six").click();
        // verify the number six shows
        $("#display > div").shouldBe(text("6"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number fifteen now shows (we have added to our total)
        // since 1 + 3 + 5 + 6 = 15
        $("#display > div").shouldBe(text("15"));

        // click on the number three button
        $("#three").click();
        // verify the number three shows
        $("#display > div").shouldBe(text("3"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number eighteen now shows (we have added to our total)
        // since 1 + 3 + 5 + 6 + 3 = 18
        $("#display > div").shouldBe(text("18"));

        // click on the number seven button
        $("#seven").click();
        // verify the number seven shows
        $("#display > div").shouldBe(text("7"));

        // click on the equals sign
        $("#equals").click();
        // verify the number twenty-five now shows (we have added to our total)
        // since 1 + 3 + 5 + 6 + 3 + 7 = 25
        $("#display > div").shouldBe(text("25"));
    }

    @Test(alwaysRun = true, dependsOnMethods = "openCalculator")
    public void simpleDivision() {
        // B. Simple Division
        // 1. 5 / 1

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number five button
        $("#five").click();
        // verify the number five shows
        $("#display > div").shouldBe(text("5"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 0 to get division
        // click on the divide button
        $("#divide", 0).click();
        // verify the number five still shows (memory was empty)
        $("#display > div").shouldBe(text("5"));

        // click on the number one button
        $("#one").click();
        // verify the number one shows
        $("#display > div").shouldBe(text("1"));

        // click on the equals sign
        $("#equals").click();
        // verify the number five shows, as 5 / 1 = 5
        $("#display > div").shouldBe(text("5"));

        // 2. 6 / 2

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number six button
        $("#six").click();
        // verify the number six shows
        $("#display > div").shouldBe(text("6"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 0 to get division
        // click on the divide button
        $("#divide", 0).click();
        // verify the number six still shows (memory was empty)
        $("#display > div").shouldBe(text("6"));

        // click on the number two button
        $("#two").click();
        // verify the number two shows
        $("#display > div").shouldBe(text("2"));

        // click on the equals sign
        $("#equals").click();
        // verify the number three shows, as 6 / 2 = 3
        $("#display > div").shouldBe(text("3"));

        // 3. 100 / 2

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number one button
        $("#one").click();
        // verify the number one shows
        $("#display > div").shouldBe(text("1"));

        // click on the number zero button
        $("#zero").click();
        // verify the number ten shows
        $("#display > div").shouldBe(text("10"));

        // click on the number one button
        $("#zero").click();
        // verify the number one hundred shows
        $("#display > div").shouldBe(text("100"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 0 to get division
        // click on the divide button
        $("#divide", 0).click();
        // verify the number one hundred still shows (memory was empty)
        $("#display > div").shouldBe(text("100"));

        // click on the number two button
        $("#two").click();
        // verify the number two shows
        $("#display > div").shouldBe(text("2"));

        // click on the equals sign
        $("#equals").click();
        // verify the number fifty shows, as 100 / 2 = 50
        $("#display > div").shouldBe(text("50"));

        // 4. 0 / 2

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number zero button
        $("#zero").click();
        // verify the number zero shows
        $("#display > div").shouldBe(text("0"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 0 to get division
        // click on the divide button
        $("#divide", 0).click();
        // verify the number zero still shows (memory was empty)
        $("#display > div").shouldBe(text("0"));

        // click on the number two button
        $("#two").click();
        // verify the number two shows
        $("#display > div").shouldBe(text("2"));

        // click on the equals sign
        $("#equals").click();
        // verify the number zero shows, as 0 / 2 = 0
        $("#display > div").shouldBe(text("0"));

        // 5. 4 / 0

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number four button
        $("#four").click();
        // verify the number four shows
        $("#display > div").shouldBe(text("4"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 0 to get division
        // click on the divide button
        $("#divide", 0).click();
        // verify the number four still shows (memory was empty)
        $("#display > div").shouldBe(text("4"));

        // click on the number zero button
        $("#zero").click();
        // verify the number zero shows
        $("#display > div").shouldBe(text("0"));

        // click on the equals sign
        $("#equals").click();
        // verify the text "Error" shows, as 4 / 0 = Error
        $("#display > div").shouldBe(text("Error"));
    }

    @Test(alwaysRun = true, dependsOnMethods = "openCalculator")
    public void someDecimals() {
        // C. Decimals
        // 1. 0.5 + 2.65354

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number zero button
        $("#zero").click();
        // verify the number zero shows
        $("#display > div").shouldBe(text("0"));

        // click on the period button
        $("#panel > div.row-block > div:nth-child(4) > input.small").click();
        // verify the number zero with a period shows
        $("#display > div").shouldBe(text("0."));

        // click on the number five button
        $("#five").click();
        // verify the number zero point five shows
        $("#display > div").shouldBe(text("0.5"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 1 to get plus
        // click on the plus button
        $("#divide", 1).click();
        // verify the number zero point five still shows (memory was empty)
        $("#display > div").shouldBe(text("0.5"));

        // click on the number two button
        $("#two").click();
        // verify the number two shows
        $("#display > div").shouldBe(text("2"));

        // click on the period button
        $("#panel > div.row-block > div:nth-child(4) > input.small").click();
        // verify the number two with a period shows
        $("#display > div").shouldBe(text("2."));

        // click on the number six button
        $("#six").click();
        // verify the number two point six shows
        $("#display > div").shouldBe(text("2.6"));

        // click on the number five button
        $("#five").click();
        // verify the number two point six five shows
        $("#display > div").shouldBe(text("2.65"));

        // click on the number three button
        $("#three").click();
        // verify the number two point six five three shows
        $("#display > div").shouldBe(text("2.653"));

        // click on the number five button
        $("#five").click();
        // verify the number two point six five three five shows
        $("#display > div").shouldBe(text("2.6535"));

        // click on the number four button
        $("#four").click();
        // verify the number two point six five three five four shows
        $("#display > div").shouldBe(text("2.65354"));

        // click on the equals sign
        $("#equals").click();
        // verify the number 3.15354 shows, as 0.5 + 2.65354
        $("#display > div").shouldBe(text("3.15354"));

        // 2. 1.25 - .25 / 0

        // clear our output
        $("#clear").click();
        // verify our calculator shows zero
        $("#display > div").shouldBe(text("0"));

        // click on the number one button
        $("#one").click();
        // verify the number one shows
        $("#display > div").shouldBe(text("1"));

        // click on the period button
        $("#panel > div.row-block > div:nth-child(4) > input.small").click();
        // verify the number one with a period shows
        $("#display > div").shouldBe(text("1."));

        // click on the number two button
        $("#two").click();
        // verify the number one point two shows
        $("#display > div").shouldBe(text("1.2"));

        // click on the number five button
        $("#five").click();
        // verify the number one point two five shows
        $("#display > div").shouldBe(text("1.25"));

        // click on the subtract button
        $("#subtract").click();
        // verify the number one point two five still shows (memory was empty)
        $("#display > div").shouldBe(text("1.25"));

        // click on the period button
        $("#panel > div.row-block > div:nth-child(4) > input.small").click();
        // verify the number zero with a period shows
        $("#display > div").shouldBe(text("0."));

        // click on the number two button
        $("#two").click();
        // verify the number zero point two shows
        $("#display > div").shouldBe(text("0.2"));

        // click on the number five button
        $("#five").click();
        // verify the number zero point two five shows
        $("#display > div").shouldBe(text("0.25"));

        // click on the equals sign
        $("#equals").click();
        // verify the number one shows, as 1.25 - .25 = 1
        $("#display > div").shouldBe(text("1"));

        // for some reason the divide and plus button
        // both use #divide selector, so we use index 0 to get division
        // click on the divide button
        $("#divide", 0).click();
        // verify the number one still shows (memory was correct)
        $("#display > div").shouldBe(text("1"));

        // click on the number zero button
        $("#zero").click();
        // verify the number zero shows
        $("#display > div").shouldBe(text("0"));

        // click on the equals sign
        $("#equals").click();
        // verify the number text "Error" shows, as 1.25 - .25 / 0 = Error
        $("#display > div").shouldBe(text("Error"));

        // left for debugging
//        sleep(3000);
    }
}
