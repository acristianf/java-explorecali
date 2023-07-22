package com.cristian.ec.explorecali.web;

import com.cristian.ec.explorecali.domain.Role;
import com.cristian.ec.explorecali.domain.User;
import com.cristian.ec.explorecali.repo.RoleRepository;
import com.cristian.ec.explorecali.repo.UserRepository;
import com.cristian.ec.explorecali.service.ExploreCaliUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final ExploreCaliUserDetailsService userDetailsService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserController(ExploreCaliUserDetailsService userDetailsService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<User> signup(@RequestBody @Validated UserDto body) {
        LOGGER.info("POST /users/registration body: {}, encoder: {}", body, passwordEncoder.toString());
        if (userRepository.findByUsername(body.getUsername()).isPresent()) return Optional.empty();

        Optional<Role> role = roleRepository.findRoleByRoleName("ROLE_CSR");
        return Optional.of(
                userRepository.save(
                        new User(
                                body.getUsername(),
                                passwordEncoder.encode(body.getPassword()),
                                role.get(),
                                body.getFirstname(),
                                body.getLastname()
                        )
                )
        );
    }

}
