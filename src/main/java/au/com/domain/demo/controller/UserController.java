package au.com.domain.demo.controller;

import au.com.domain.demo.entity.User;
import au.com.domain.demo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mseskander .
 */
@RestController
@RequestMapping(path = "/user/")
public class UserController {

    /**
     * User repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * List users
     *
     * @param pageRequest
     * @return
     */
    @RequestMapping(value="list",
            method= RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public Page<User> list(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    /**
     * Save user.
     *
     * @param user
     * @return
     */
    @RequestMapping(value="save", method= RequestMethod.POST)
    public User create(@RequestBody User user) {
        return userRepository.saveAndFlush(user);
    }

    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value="get/{id}", method = RequestMethod.GET)
    public User get(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    /**
     * Update user by id
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value="update/{id}", method= RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existingIssue = userRepository.findOne(id);
        BeanUtils.copyProperties(user, existingIssue);
        return userRepository.saveAndFlush(existingIssue);
    }

    /**
     * Delete user by id
     *
     * @param id
     */
    @RequestMapping(value="delete/{id}", method= RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        userRepository.delete(id);
    }
}