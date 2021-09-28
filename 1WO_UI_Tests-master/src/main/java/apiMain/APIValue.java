package apiMain;

public enum APIValue {

    ID("id"),
    OWO_WIDGET_CODE("widgetCode");

    private final String APIValue;

    APIValue(final String value) {
        this.APIValue = value;
    }

    @Override
    public String toString() {
        return APIValue;
    }
}
