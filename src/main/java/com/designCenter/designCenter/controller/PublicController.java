package com.designCenter.designCenter.controller;

import com.designCenter.designCenter.dto.authUser.AuthenticationRequest;
import com.designCenter.designCenter.dto.authUser.AuthenticationResponse;
import com.designCenter.designCenter.dto.common.CommonResponse;
import com.designCenter.designCenter.service.MyUserDetailsService;
import com.designCenter.designCenter.util.JwlUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PublicController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwlUtil jwtTokenUtil;


    @GetMapping(value= "/open")
    public ResponseEntity<?> openApi(){
        log.info("Called Open API");
        return ResponseEntity.ok(new CommonResponse<>(true,"Application in Running State..!"));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authRequest)throws Exception{

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
            );
        }catch (BadCredentialsException exception){
            return ResponseEntity.ok(new CommonResponse<>(false, "Incorrect Username or Password"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new CommonResponse<>(true,new AuthenticationResponse(jwt)));

    }

}
