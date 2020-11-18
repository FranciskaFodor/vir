package hu.vir.db;

import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        this.userRepository.deleteAll();

        User dan = new User("user1",passwordEncoder.encode("user123"),"USER","");
        User manager = new User("user2",passwordEncoder.encode("user123"),"USER","");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","");

        List<User> users = Arrays.asList(dan,admin,manager);

        userRepository.saveAll(users);
    }
}
