package au.com.domain.demo.controller;

import au.com.domain.demo.entity.Issue;
import au.com.domain.demo.helper.SearchOption;
import au.com.domain.demo.repository.IssueRepository;
import au.com.domain.demo.specification.IssueSpecification;
import au.com.domain.demo.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by mseskander .
 */
@RestController
@RequestMapping(path = "/issue/")
public class IssueController {

    /**
     * Issue repository
     */
    @Autowired
    private IssueRepository issueRepository;

    // this API for a quick test in the start ...
//    @RequestMapping(value="hello", method= RequestMethod.GET)
//    public String sayHello() {
//        return "Hello from the Issue Tracker project ...";
//    }

    /**
     * list all issues and filter by date range
     *
     * @param startDate
     * @param endDate
     * @param pageRequest contains page, size, Direction.ASC/DESC, field for sorting
     * @return
     */
    @RequestMapping(value="list",
            method= RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public Page<Issue> list(@RequestParam(required = false) String startDate,
                            @RequestParam(required = false) String endDate,
                            Pageable pageRequest) {
        if (startDate != null) {
            // return the issue that are created between these dates
            return issueRepository.findAll(getDateSearchQuery(startDate, endDate), pageRequest);
        } else {
            // return all issues,
            return issueRepository.findAll(pageRequest);
        }
    }

    /**
     * Search issues
     *
     * @param searchOption
     * @param searchValue
     * @param startDate
     * @param endDate
     * @param pageRequest
     * @return
     */
    @RequestMapping(value="search",
            method= RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public Page<Issue> search(@RequestParam("searchOption") SearchOption searchOption,
                              @RequestParam String searchValue,
                              @RequestParam(required = false) String startDate,
                              @RequestParam(required = false) String endDate,
                              Pageable pageRequest) {

        String key = searchOption.getValue();
        if (startDate != null) {
            // return the issue that are created between these dates
            IssueSpecification issueSpecification = getFilterSearchQuery(key, searchValue, startDate, endDate);
            return issueRepository.findAll(issueSpecification, pageRequest);
        } else {
            // return all issues for the specified key,
            IssueSpecification issueSpecification = new IssueSpecification(key, searchValue);
            return issueRepository.findAll(issueSpecification, pageRequest);
        }
    }

    /**
     * Save new issue.
     *
     * @param issue
     * @return
     */
    @RequestMapping(value="save", method= RequestMethod.POST)
    public Issue create(@RequestBody Issue issue) {
        return issueRepository.saveAndFlush(issue);
    }

    /**
     * Get issue by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value="get/{id}", method = RequestMethod.GET)
    public Issue get(@PathVariable Long id) {
        return issueRepository.findOne(id);
    }

    /**
     * Update existing issue by id
     *
     * @param id
     * @param issue
     * @return
     */
    @RequestMapping(value="update/{id}", method= RequestMethod.PUT)
    public Issue update(@PathVariable Long id, @RequestBody Issue issue) {
        // next two lines to merge the existing and new record..
        Issue existingIssue = issueRepository.findOne(id);
        BeanUtils.copyProperties(issue, existingIssue);
        // note: Also save function check for existing record and merge
        return issueRepository.saveAndFlush(issue);
    }

    /**
     * Delete issue by id
     *
     * @param id
     */
    @RequestMapping(value="delete/{id}", method= RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        issueRepository.delete(id);
    }



    private IssueSpecification getDateSearchQuery(String creationStartDate, String creationEndDate) {
        // convert the date string to Date object
        Date startDate = Utils.getFormatedDate(creationStartDate);
        Date endDate = Utils.getFormatedDate(creationEndDate);
        // get the search query for date range
        return (startDate != null) ? new IssueSpecification(startDate, endDate) : null;
    }

    private IssueSpecification getFilterSearchQuery(String key, String value, String creationStartDate,
                                                 String creationEndDate) {
        // convert the date string to Date object
        Date startDate = Utils.getFormatedDate(creationStartDate);
        Date endDate = Utils.getFormatedDate(creationEndDate);

        return new IssueSpecification(key, value, startDate, endDate);
    }

}