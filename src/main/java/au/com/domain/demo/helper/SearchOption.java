package au.com.domain.demo.helper;

/**
 * Created by mseskander .
 */
public enum SearchOption {
    ASSIGNEE("assignee"),
    REPORTER("reporter"),
    STATUS("status");

    private String value;

    private SearchOption(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static SearchOption fromValue(String value) {
        for (SearchOption searchOption : values()){
            if (searchOption.value.equalsIgnoreCase(value)) {
                return searchOption;
            }
        }
        // TODO: throw exception
        return null;
    }
}