package org.partssale.controller;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.partssale.common.JsonData;
import org.partssale.domain.dto.User;
import org.partssale.domain.vo.UserReturnVo;
import org.partssale.service.UserService;
import org.partssale.utils.SecretKeyGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;

    private final String secretKey;

    public LoginController() {
        // Generate a secret key
        secretKey = SecretKeyGenerator.generateSecretKey(24);
    }

    @PostMapping
    public ResponseEntity login(@RequestBody User user) {
        User dbUser = userService.findByUsername(user.getUsername());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(dbUser.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 Hour validity
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
            UserReturnVo userReturnVo = new UserReturnVo();
            BeanUtils.copyProperties(dbUser, userReturnVo);
            userReturnVo.setToken(token);
            userReturnVo.setId(String.valueOf(dbUser.getId()));
            return ResponseEntity.ok(userReturnVo);
        } else {
            return ResponseEntity.ok("Invalid credentials");
        }
    }
}

