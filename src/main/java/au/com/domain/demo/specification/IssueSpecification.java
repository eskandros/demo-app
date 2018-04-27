package au.com.domain.demo.specification;

import au.com.domain.demo.entity.Issue;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * Created by mseskander .
 */
public class IssueSpecification implements Specification<Issue> {
    // TODO: extend this class to use
    // the "Search criteria" object that is created but it is not used ...
    // that will help to do search on multiple field such as status and reporter plus the dates ... ,

    private static final String CREATED_DATE_KEY = "created";

    private String key;
    private String value;
    private Date startDate;
    private Date endDate;

    public IssueSpecification() {}

    public IssueSpecification(String key, String value) {
        setup(key, value, null, null);
    }

    public IssueSpecification(Date startDate, Date endDate) {
        setup(null, null, startDate, endDate);
    }

    public IssueSpecification(String key, String value, Date startDate, Date endDate) {
        setup(key, value, startDate, endDate);
    }

    @Override
    public Predicate toPredicate(Root<Issue> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // TODO: refacto, add logging and throw exception

        Predicate keyQuery = null;

        if (this.key != null && this.value !=null) {
            if (root.<String> get(key).getJavaType() == String.class) {
                // String type search
                if (startDate != null) {
                    keyQuery = cb.like(root.<String>get(key), "%" + value + "%");
                } else {
                    return cb.like(root.<String>get(key), "%" + value + "%");
                }
            } else {
                // other types search
                if (startDate != null) {
                    keyQuery = cb.equal(root.<String>get(key), value);
                } else {
                    return cb.equal(root.<String>get(key), value);
                }
            }
        }

        if (startDate != null) {
            if (endDate == null) {
                if (keyQuery != null) {
                    return cb.and(keyQuery, cb.greaterThanOrEqualTo(root.<Date>get(CREATED_DATE_KEY), startDate));
                } else {
                    return cb.greaterThanOrEqualTo(root.<Date>get(CREATED_DATE_KEY), startDate);
                }
            } else {
                Predicate startDatePredicate = cb.greaterThanOrEqualTo(root.<Date>get(CREATED_DATE_KEY), startDate);
                Predicate endDatePredicate = cb.lessThanOrEqualTo(root.<Date>get(CREATED_DATE_KEY), startDate);
                if (keyQuery != null) {
                    return cb.and(keyQuery, startDatePredicate, endDatePredicate);
                } else {
                    return cb.and(startDatePredicate, endDatePredicate);
                }
            }
        }

        return null;
    }

    private void setup(String key, String value, Date startDate, Date endDate) {
        this.key = key;
        this.value = value;
        if (startDate!= null) {
            this.startDate = startDate;
        }
        if (endDate != null) {
            this.endDate = endDate;
        }
    }
}