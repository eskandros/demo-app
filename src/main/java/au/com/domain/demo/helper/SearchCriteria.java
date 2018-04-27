package au.com.domain.demo.helper;

/**
 * Created by mseskander .
 */
public class SearchCriteria {

    // TODO: This class is not used yet, full implentation is required  ...
    // TODO: use this object as @RequestParam for the search API
    //       , also use to build the repository specification.
    //this object will help to do search on multiple field such as status and reporter ...

    private SearchOption searchOption;
    private String status;
    private String reporter;
    private String assignee;
    private long created;
    private long completed;

    public SearchCriteria(){}

    public SearchCriteria(SearchOption searchOption, String status, String reporter,
                          String assignee, long created, long completed){
        this.searchOption = searchOption;
        this.status = status;
        this.reporter = reporter;
        this.assignee = assignee;
        this.created = created;
        this.completed = completed;
    }

    public SearchOption getSearchOption() {
        return searchOption;
    }

    public void setSearchOption(SearchOption searchOption) {
        this.searchOption = searchOption;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }
}