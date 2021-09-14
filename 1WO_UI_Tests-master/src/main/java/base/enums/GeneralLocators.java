package base.enums;

public enum GeneralLocators {

    OPTION_BY_TEXT(".//option[contains(text(), '%s')]"),
    SPAN_BY_TEXT(".//span[contains(text(), '%s')]"),
    A_BY_TEXT(".//a[contains(text(), '%s')]"),
    SELECT_BY_NAME(".//select[contains(@name, '%s')]"),
    SELECT_BY_CLASS(".//select[contains(@class, '%s')]"),
    INPUT_BY_CLASS(".//input[contains(@class, '%s')]"),
    INPUT_BY_ID(".//input[@id='%s']"),
    INPUT_BY_NAME(".//input[contains(@name, '%s')]"),
    INPUT_BY_VALUE(".//input[contains(@value, '%s')]"),
    SPAN_BY_CLASS(".//span[contains(@class, '%s')]"),
    LABEL_BY_ID(".//label[@id='%s']"),
    DIV_BY_CLASS(".//div[contains(@class, '%s')]"),

    DIV_BY_ID(".//div[@id='%s']");

    private final String xPath;

    GeneralLocators(final String xPath) {
        this.xPath = xPath;
    }

    @Override
    public String toString() {
        return xPath;
    }
}
