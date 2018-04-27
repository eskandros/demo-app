package au.com.domain.demo.controller;

import au.com.domain.demo.entity.Comment;
import au.com.domain.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mseskander .
 */
@RestController
@RequestMapping(path = "/comment/")
public class CommentController {

    /**
     * comment repository
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * list of issue comments.
     *
     * @param issueid
     * @param pageRequest
     * @return
     */
    @RequestMapping(value="list/{issueid}", method= RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public Page<Comment> list(@PathVariable Long issueid, Pageable pageRequest) {
        //TODO: implementation
        return null;
    }

    /**
     * Save a new comment.
     *
     * @param comment
     * @return
     */
    @RequestMapping(value="save", method= RequestMethod.POST)
    public Comment create(@RequestBody Comment comment) {
        //TODO: implementation
        return null;
    }

    /**
     * get specific comment by the comment id.
     *
     * @param id
     * @return
     */
    @RequestMapping(value="get/{id}", method = RequestMethod.GET)
    public Comment get(@PathVariable Long id) {
        //TODO: implementation
        return null;
    }

    /**
     * delete the comment by id
     *
     * @param id
     */
    @RequestMapping(value="delete/{id}", method= RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //TODO: implementation
    }
}