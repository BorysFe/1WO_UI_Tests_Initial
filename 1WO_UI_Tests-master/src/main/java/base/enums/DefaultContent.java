package base.enums;

public enum DefaultContent {

    RANDOM_WIDGET_NAME_TEXT("PollerWidget %s - " + System.currentTimeMillis()),
    RANDOM_POLL_QUESTION_TEXT("Poll question %s - " + System.currentTimeMillis()),
    RANDOM_POLL_ANSWER_TEXT("Poll answer %s - " + System.currentTimeMillis()),

    DEFAULT_TEXT("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas feugiat consequat diam. Maecenas metus. Vivamus diam purus, cursus a, commodo non, facilisis vitae, nulla. Aenean dictum lacinia tortor. Nunc iaculis, nibh non iaculis aliquam, orci felis euismod neque, sed ornare massa mauris sed velit. Nulla pretium mi et risus. Fusce mi pede, tempor id, cursus ac, ullamcorper nec, enim. Sed tortor. Curabitur molestie. Duis velit augue, condimentum at, ultrices a, luctus ut, orci. Donec pellentesque egestas eros. Integer cursus, augue in cursus faucibus, eros pede bibendum sem, in tempus tellus justo quis ligula. Etiam eget tortor. Vestibulum rutrum, est ut placerat elementum, lectus nisl aliquam velit, tempor aliquam eros nunc nonummy metus. In eros metus, gravida a, gravida sed, lobortis id, turpis. Ut ultrices, ipsum at venenatis fringilla, sem nulla lacinia tellus, eget aliquet turpis mauris non enim. Nam turpis. Suspendisse lacinia. Curabitur ac tortor ut ipsum egestas elementum. Nunc imperdiet gravida mauris.\n");

    private String widgetContent;

    DefaultContent(String widgetContent) {
        this.widgetContent = widgetContent;
    }

    @Override
    public String toString() {
        return widgetContent;
    }
}
