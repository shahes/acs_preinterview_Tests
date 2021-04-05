package acs_interview_tasks.pages;

import acs_interview_tasks.utilities.Driver;
import com.google.common.math.DoubleMath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;
import java.util.List;

import static java.lang.Math.*;

public class DonatePage {

    public DonatePage(){

        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy(xpath = "//div[@id='step-title']/div[2]")
    public WebElement backgroundImage;

    @FindBy(css = "div.title")
    public WebElement text;

    @FindBy(xpath = "//div[@id='frequency_onetime']")
    public WebElement oneTimeButton;

    @FindBy(xpath = "//div[@class='amount-container'][1]/div")
    public List<WebElement> oneTimeDonationValues;

    @FindBy(xpath = "//div[@class='amount-container'][2]/div")
    public List<WebElement> monthlyDonationValues;


    @FindBy(xpath = "//div[@id='frequency_monthly']")
    public WebElement monthlyButton;

    @FindBy(css = "button.continue-next")
    public WebElement continueButton;

    @FindBy(css="input[name='number']")
    public WebElement creditCardNumberInput;

    @FindBy(id = "payment_card_expire_month")
    public WebElement expirationMonthInput;

    @FindBy(id = "payment_card_expire_year")
    public WebElement expirationYearInput;

    @FindBy(name = "securityCode")
    public WebElement cvvInput;

    @FindBy(xpath= "(//span[@class='error'])[1]")
    public WebElement creditCardRequiredErrorMessage;

    @FindBy(xpath= "(//span[@class='error'])[2]")
    public WebElement expirationDateRequiredErrorMessage;

    @FindBy(xpath= "(//span[@class='error'])[3]")
    public WebElement cvvRequiredErrorMessage;

    @FindBy(id= "fee_amount")
    public WebElement serviceFeeAmount;

    @FindBy(id="amount-review")
    public WebElement amountReview;

    public String calculateServiceFee(double amount){

        DecimalFormat df = new DecimalFormat("0.00");

     Double serviceFee = (amount*5.5)/100 ;

        return  df.format(serviceFee);
    }



}
