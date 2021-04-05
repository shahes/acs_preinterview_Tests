package acs_interview_tasks.tests;

import acs_interview_tasks.pages.DonatePage;
import acs_interview_tasks.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.*;
/*
1.Make sure the donate home page comes up
2.Back ground image and text is correct
3.One Time and Monthly values are correct – Different values
4.Click on Continue and credit card shows the key required field  - Required error message should be captured
5.Calculation logic to make sure Service Fee is shown the correct value based on different values
(with and without Service fee)

link: https://qa.donate.cancer.org/
 */
public class DonateHomePage_Tests extends TestBase{

    DonatePage donatePage = new DonatePage();

@Test
public void homePage_Verification(){

    assertEquals(Driver.getDriver().getTitle() ,"Donate Today | The American Cancer Society");
}


@Test
public void backgroundImage_And_Text_Verification(){


    assertTrue(donatePage.backgroundImage.isEnabled());
    assertTrue(donatePage.text.getText().equals("Give the gift of cancer research and patient support."));

}

@Test
public void oneTime_And_MonthlyDonation_Values_Verification(){


    if(!(donatePage.oneTimeButton.isSelected())){
        donatePage.oneTimeButton.click();
    }

    List<String> oneTimeDonationValuesAsString = new LinkedList<>();


    for(int i=0; i<=3 ;i++){

        oneTimeDonationValuesAsString.add(donatePage.oneTimeDonationValues.get(i).getText());

    }
    System.out.println(oneTimeDonationValuesAsString);

    assertTrue(oneTimeDonationValuesAsString.get(0).equals("$50"));
    assertTrue(oneTimeDonationValuesAsString.get(1).equals("$75"));
    assertTrue(oneTimeDonationValuesAsString.get(2).equals("$100"));
    assertTrue(oneTimeDonationValuesAsString.get(3).equals("$250"));

    donatePage.monthlyButton.click();

    List<String> monthlyDonationValuesAsString = new LinkedList<>();

    for(int i =0; i<=3; i++){

        monthlyDonationValuesAsString.add(donatePage.monthlyDonationValues.get(i).getText()) ;

    }
    System.out.println(monthlyDonationValuesAsString);
    assertTrue(monthlyDonationValuesAsString.get(0).equals("$21"));
    assertTrue(monthlyDonationValuesAsString.get(1).equals("$50"));
    assertTrue(monthlyDonationValuesAsString.get(2).equals("$100"));
    assertTrue(monthlyDonationValuesAsString.get(3).equals("$250"));

    assertFalse(oneTimeDonationValuesAsString.get(0).equals(monthlyDonationValuesAsString.get(0)));
    assertFalse(oneTimeDonationValuesAsString.get(1).equals(monthlyDonationValuesAsString.get(1)));

}

@Test
    public void creditCard_keyRequiredField_Verification(){

    donatePage.continueButton.click();

    Driver.getDriver().switchTo().frame(0);

    assertTrue(donatePage.creditCardNumberInput.isDisplayed());

    Driver.getDriver().switchTo().defaultContent();

    assertTrue(donatePage.expirationMonthInput.isDisplayed());
    assertTrue(donatePage.expirationYearInput.isDisplayed());

    Driver.getDriver().switchTo().frame(1);

    assertTrue(donatePage.cvvInput.isDisplayed());

    Driver.getDriver().switchTo().defaultContent();
    donatePage.continueButton.click();

    assertTrue(donatePage.creditCardRequiredErrorMessage.isDisplayed());
    assertTrue(donatePage.expirationDateRequiredErrorMessage.isDisplayed());
    assertTrue(donatePage.cvvRequiredErrorMessage.isDisplayed());


}

@Test

public void calculation_withServiceFee_and_withoutServiceFee_verification(){
    Faker faker = new Faker();
    Select monthDropdown = new Select(donatePage.expirationMonthInput);
    Select yearDropdown = new Select(donatePage.expirationYearInput);



    donatePage.oneTimeDonationValues.get(0).click();
   String firstDonationValue = donatePage.oneTimeDonationValues.get(0).getAttribute("data-paymentamount");
     String serviceFeeForFirstDonationValue = donatePage.calculateServiceFee(Double.valueOf(firstDonationValue));

     assertTrue(("$"+serviceFeeForFirstDonationValue).equals(donatePage.serviceFeeAmount.getText()));


    donatePage.oneTimeDonationValues.get(0).click();
    donatePage.continueButton.click();
    Driver.getDriver().switchTo().frame(0);
    donatePage.creditCardNumberInput.sendKeys(faker.finance().creditCard());
    Driver.getDriver().switchTo().defaultContent();
    monthDropdown.selectByIndex(faker.number().numberBetween(0,11));
    yearDropdown.selectByIndex(faker.number().numberBetween(0,18));
    Driver.getDriver().switchTo().frame(1);
    donatePage.cvvInput.sendKeys(faker.number().digits(3));
    Driver.getDriver().switchTo().defaultContent();
    donatePage.continueButton.click();

    assertTrue(donatePage.amountReview.isDisplayed());


}



}






