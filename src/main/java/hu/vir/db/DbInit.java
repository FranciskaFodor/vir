package hu.vir.db;

import hu.vir.model.Image;
import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;


    @Override
    public void run(String... args) throws Exception {

        this.userRepository.deleteAll();

        User user1 = new User("user1",passwordEncoder.encode("jelszo1"),"USER","JPG,GIF");
        User user2 = new User("user2",passwordEncoder.encode("jelszo2"),"USER","JPG");
        User admin = new User("admin",passwordEncoder.encode("jelszo"),"ADMIN","JPG,PNG,GIF");

       setImageListByUser(user1);
       setImageListByUser(user2);
       setImageListByUser(admin);

        List<User> users = Arrays.asList(user1, user2, admin);

        userRepository.saveAll(users);
    }

    public void setImageListByUser(User user) throws IOException {
        Resource[] resources  = resourcePatternResolver.getResources("classpath:static/images/*");
        user.getImageList().clear();
        for(Resource resource : resources) {
            String fileName = resource.getFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
            if(user.getPermissionList().contains(fileExtension)) {
                user.getImageList().add(new Image("images/" + fileName));
            }

        }
    }
}
