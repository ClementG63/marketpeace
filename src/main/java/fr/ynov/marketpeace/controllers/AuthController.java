package fr.ynov.marketpeace.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import fr.ynov.marketpeace.entities.ERole;
import fr.ynov.marketpeace.entities.Role;
import fr.ynov.marketpeace.entities.User;
import fr.ynov.marketpeace.entities.UserDetailsImpl;
import fr.ynov.marketpeace.repositories.RoleRepository;
import fr.ynov.marketpeace.repositories.UserRepository;
import fr.ynov.marketpeace.request.LoginRequest;
import fr.ynov.marketpeace.request.SignupRequest;
import fr.ynov.marketpeace.response.JwtResponse;
import fr.ynov.marketpeace.response.MessageResponse;
import fr.ynov.marketpeace.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication controller
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    /**
     * All args constructor
     * @param authenticationManager Authentication manager
     * @param userRepository User repository
     * @param roleRepository Role repository
     * @param encoder Password encoder
     * @param jwtUtils Tool to manipulate JWT
     */
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    /**
     * Authenticate user with input request
     * @param loginRequest User request {@link LoginRequest}
     * @return Response to send
     */
    @Operation(description = "Sign in user with given credentials")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles)
        );
    }

    /**
     * Register user with incoming informations from request
     * @param signUpRequest User request {@link SignupRequest}
     * @return Response to send
     * Throw bad request if username/mail is already taken
     */
    @Operation(description = "Sign up new user with given credentials")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        } else if (userRepository.existsByMailAddress(signUpRequest.getMail_address())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        } else {
            User user = new User(
                    signUpRequest.getUsername(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getName(),
                    signUpRequest.getSurname(),
                    signUpRequest.getMail_address()
            );

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository
                        .findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

                roles.add(userRole);
            } else {
                for (String role : strRoles) {
                    if ("admin".equals(role)) {
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    } else {
                        Role userRole = roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            }

            user.setRoles(roles);
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        }
    }
}