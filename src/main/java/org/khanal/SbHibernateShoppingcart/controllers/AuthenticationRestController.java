package org.khanal.SbHibernateShoppingcart.controllers;

import org.khanal.SbHibernateShoppingcart.services.security.UserDetailsImpl;
import org.khanal.SbHibernateShoppingcart.services.security.jwt.AuthenticationDetails;
import org.khanal.SbHibernateShoppingcart.services.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenUtil
            jwtTokenUtil, UserDetailsService userDetailsService) {
        this.tokenHeader = tokenHeader;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationDetails authDetails) throws AuthenticationException {
        authenticate(authDetails.getUsername(), authDetails.getPassword());
        //authenticate(username, password);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authDetails.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(token);
    }


    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.GET)
    public ResponseEntity<String> getcreateAuthenticationToken() throws AuthenticationException {

        return ResponseEntity.ok("test");
    }


    public ResponseEntity<String> refreshAndGetAuthenticationToken(HttpServletRequest request){
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);

        String username = jwtTokenUtil.getUsernameFromToken(token);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

        if(jwtTokenUtil.canTokenBeRefreshed(token, userDetails.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(refreshedToken);
        }
        return ResponseEntity.badRequest().body(null);
    }

    private void authenticate(String username, String password){
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e){
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e){
            throw new AuthenticationException("Bad credentials!", e);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
