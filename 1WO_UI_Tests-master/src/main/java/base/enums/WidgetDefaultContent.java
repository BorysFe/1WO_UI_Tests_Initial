package base.enums;

public enum WidgetDefaultContent {

    DEFAULT_WIDGET_NAME("PollerWidget %s - " + System.currentTimeMillis()),
    POLL_QUESTION_TEXT("Poll question %s - " + System.currentTimeMillis()),
    POLL_ANSWER_TEXT("Poll answer %s - " + System.currentTimeMillis());

    private String widgetContent;

    WidgetDefaultContent(String widgetContent) {
        this.widgetContent = widgetContent;
    }

    @Override
    public String toString() {
        return widgetContent;
    }
}
