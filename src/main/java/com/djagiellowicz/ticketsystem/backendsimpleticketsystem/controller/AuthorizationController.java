package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AuthenticationDto;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.LoginDto;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service.AppUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.djagiellowicz.ticketsystem.backendsimpleticketsystem.configuration.JWTFilter.AUTHORITIES_KEY;
import static com.djagiellowicz.ticketsystem.backendsimpleticketsystem.configuration.JWTFilter.SECRET_KEY;

@RestController
@CrossOrigin
public class AuthorizationController {

    @Autowired
    private AppUserService appUserService;

        @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
        public ResponseEntity<AuthenticationDto> authenticate(@RequestBody LoginDto dto) {
            Optional<AppUser> appUserOptional = null;
            try {
                appUserOptional = appUserService.getUserWithLoginAndPassword(dto);
                AppUser user = appUserOptional.get();
                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
                //We will sign our JWT with our ApiKey secret_key
                byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
                Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
                String token = Jwts.builder()
                        .setSubject(user.getLogin())
                        .setIssuedAt(new Date())
                        .claim(AUTHORITIES_KEY, translateRoles(user.getUserRoleSet()))
                        .signWith(signatureAlgorithm, signingKey)
                        .compact();
                return ResponseFactory.result(new AuthenticationDto(token, user));
            } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
                return ResponseFactory.badRequest();
            }
        }

        private Set<String> translateRoles(Set<UserRole> roles) {
            return roles.stream().map(role -> role.getName()).collect(Collectors.toSet());
        }

}
