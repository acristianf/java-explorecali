package com.cristian.ec.explorecali.web;

import com.cristian.ec.explorecali.service.ExploreCaliUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    ExploreCaliUserDetailsService userDetailsService;

    @Autowired
    UserController(ExploreCaliUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
