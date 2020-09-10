package project.school.springreacttemplate.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.school.springreacttemplate.Models.AuthRequest;
import project.school.springreacttemplate.Models.RegisterUser;
import project.school.springreacttemplate.Models.Users;
import project.school.springreacttemplate.Repositories.UserRepository;
import project.school.springreacttemplate.Security.Services.JwtUtil;

@RestController
public class DataController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public void register(@RequestBody RegisterUser registerUser) {
        Users user = new Users();
        user.setUserName(registerUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setRole("admin");
        userRepository.save(user);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("invalid"+" username/password");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @GetMapping("/api/user")
    public String getUser(){
        return "test";
    }
}
