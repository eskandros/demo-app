package au.com.domain.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by mseskander.
 */
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;;
    private Long assignee;
    private Long reporter;
    private Date created;
    private Date completed;

    public Issue() {
    }

    public Issue(Long id, String title, String description, String status, Long reporter, Long assignee,
                 Date created, Date completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.reporter = (reporter == null) ? 0 : reporter;
        this.assignee = (assignee == null) ? 0 : assignee;
        this.created = created;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Long getReporter() {
        return reporter;
    }

    public void setReporter(Long reporter) {
        this.reporter =  (reporter != null) ? reporter : 0;
    }

    public Long getAssignee() {
        return assignee ;
    }

    public void setAssignee(Long assignee) {
        if (assignee != null) {
            this.assignee = assignee;
        }
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Issue [id=" + id + ", title=" + title +
                ", description=" + description + ", reporter=" + reporter +
                ", assignee=" + assignee + ", created=" + created +
                ", completed=" + completed;
    }

    // We don't need to "implements Comparable" and override "compareTo"
    // because the JPA does it for us with Pagination support

//    @Override
//    public int compareTo(Issue o) {
//
//        // ascending order
//        return this.created.after(o.created) ? 1 : this.created.before(o.created) ? -1 : 0;
//        // disascending order
//        //return this.created.before(o.created) ? 1 : this.created.after(o.created) ? -1 : 0;
//    }

}