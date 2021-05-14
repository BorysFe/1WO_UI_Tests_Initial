package portalPages.publisher.polls;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

@Getter
@Setter
public class PollsPage extends BaseComponent {

    WaitUtils waitUtils;
    private NewPollManagerPage newPollFirstPage;
    private SelectPollLanguageModalPage selectPollLanguageModalPage;

    private final String pollDetails = ".//span[contains(text(), '%s')]";

    @FindBy(xpath = ".//div[@id='create-poll']")
    private WebElement newPollButton;

    @FindBy(xpath = ".//div[@id='create-chain']")
    private WebElement newWidgetButton;

    public PollsPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return newPollButton;
    }

    private SelectPollLanguageModalPage openNewPollWindowAndSelectRegionWithLanguage(String region, String language) {
        selectPollLanguageModalPage = new SelectPollLanguageModalPage(driver);

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(newPollButton);
        selectPollLanguageModalPage.selectDropdownAndLanguage(region, language);

        return selectPollLanguageModalPage;
    }

    public SelectPollLanguageModalPage startNewPollCreating() {
        waitUtils.waitForLoading();

        selectPollLanguageModalPage = new SelectPollLanguageModalPage(driver);

        waitUtils.clickWhenReadyAfterShortWait(newPollButton);
        return selectPollLanguageModalPage;
    }

    public boolean isPollTitleDisplayed(String pollTitle) {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(pollDetails, pollTitle)));
    }
}
