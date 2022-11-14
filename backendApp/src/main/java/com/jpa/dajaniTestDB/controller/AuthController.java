package com.jpa.dajaniTestDB.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.service.serviceInterface.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:4200")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final JavaMailSender mailSender;

    @PostMapping("/login")
    public ResponseEntity<UserTokens> userLogin(@RequestBody UserLogin userLogin) {
        String username = userLogin.getUsername();
        String password = userLogin.getPassword();

        log.info("Username is {}", username);
        log.info("Password is {}", password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication attempt = authenticationManager.authenticate(authenticationToken);
        UserTokens userTokens = new UserTokens();
        ResponseEntity<UserTokens> response = ResponseEntity.ok(userTokens);

        if(attempt.isAuthenticated()){
            User user = (User)attempt.getPrincipal();
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            String access_token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer("http://localhost:9000/api/login")
                    .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
            String refresh_token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                    .withIssuer("http://localhost:9000/api/login")
                    .sign(algorithm);

            userTokens = new UserTokens(access_token,refresh_token);
            response = ResponseEntity.ok(userTokens);
        } else {
            userTokens = new UserTokens("User not Authenticated", "User not Authenticated");
        }
        return response;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserEntity> newUserRegistration(@RequestBody UserEntity tempUserModel) {
        String username = tempUserModel.getUsername();
        String password = tempUserModel.getPassword();

        //log.info("Username is {}", username);
        //log.info("Password is {}", password);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(tempUserModel));
    }

/*    @PostMapping("/auth/forgot_password/{email}")
    public String processForgotPassword(@PathVariable("email") String email)
            throws MessagingException, UnsupportedEncodingException {
        String token = RandomString.make(30);
        userService.updateResetPasswordToken(token, email);
        sendEmail(email, "http://localhost:4200/newPassword");
        return null;
    }*/

    @PostMapping("/auth/forgotPassword/{email}")
    public ResponseEntity<String> processForgotPassword(@PathVariable String email)
            throws MessagingException, UnsupportedEncodingException {
        log.info("forgot_password REST function reached");
        String token = RandomString.make(30);
        String confirmation = "Password successfully changed";

        try {
            userService.updateResetPasswordToken(token, email);
            sendEmail(email, "http://localhost:4200/newPassword");
            log.info("updateResetPasswordToken reached");
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/auth/forgot_password/{email}").toUriString());
            return ResponseEntity.created(uri).body(confirmation);
        }
        catch(UsernameNotFoundException e){
            log.info("error while sending email");
            return null;
        }
    }

    @PostMapping("/auth/reset_password/{token}/{password}")
    public String processResetPassword(@PathVariable("token")String token, @PathVariable("password") String password){
        UserEntity tempUser = userService.getByResetPasswordToken(token);
        if(tempUser != null){
            userService.updatePassword(tempUser, password);
        }
        return "Password successfully changed";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("support@dajaniCo.com", "DajaniCo Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserTokens {
    private String access_token;
    private String refresh_token;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class UserLogin {
    private String username;
    private String password;
}

class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}