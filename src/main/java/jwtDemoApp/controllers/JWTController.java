package jwtDemoApp.controllers;

import jwtDemoApp.helper.JWTUtil;
import jwtDemoApp.model.JWTRequest;
import jwtDemoApp.model.JWTResponse;
import jwtDemoApp.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            throw new Exception("Bad Credentials");
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println(token);
        return ResponseEntity.ok(new JWTResponse(token));
    }
}
