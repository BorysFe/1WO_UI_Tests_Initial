package base.enums;

public enum GeneralLocators {

    OPTION_BY_CONTAINS_TEXT(".//option[contains(text(), '%s')]"),
    SPAN_BY_CONTAINS_TEXT(".//span[contains(text(), '%s')]"),
    A_BY_CONTAINS_TEXT(".//a[contains(text(), '%s')]"),
    A_BY_TEXT(".//a[text()= '%s']"),
    A_BY_CONTAINS_CLASS(".//a[contains(class(), '%s')]"),
    SELECT_BY_CONTAINS_NAME(".//select[contains(@name, '%s')]"),
    SELECT_BY_CONTAINS_CLASS(".//select[contains(@class, '%s')]"),
    INPUT_BY_CONTAINS_CLASS(".//input[contains(@class, '%s')]"),
    INPUT_BY_ID(".//input[@id='%s']"),
    INPUT_BY_CONTAINS_NAME(".//input[contains(@name, '%s')]"),
    INPUT_BY_CONTAINS_VALUE(".//input[contains(@value, '%s')]"),
    SPAN_BY_CONTAINS_CLASS(".//span[contains(@class, '%s')]"),
    LABEL_BY_ID(".//label[@id='%s']"),
    DIV_BY_CONTAINS_CLASS(".//div[contains(@class, '%s')]"),
    BUTTON_BY_CONTAINS_CLASS(".//button[contains(@class, '%s')]"),
    INPUT_BY_PLACEHOLDER(".//input[@placeholder= '%s']"),
    DIV_BY_ID(".//div[@id='%s']");

    private String xPath;

    GeneralLocators(String xPath) {
        this.xPath = xPath;
    }

    @Override
    public String toString() {
        return xPath;
    }
}
