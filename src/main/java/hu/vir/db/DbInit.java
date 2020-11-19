package hu.vir.db;

import hu.vir.model.Image;
import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.lang.reflect.Array;
import java.nio.file.Files;
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

        Resource[] resources  = resourcePatternResolver.getResources("classpath:static/images/*");

        User user1 = new User("user1",passwordEncoder.encode("jelszo"),"USER","JPG,GIF");
        User user2 = new User("user2",passwordEncoder.encode("jelszo"),"USER","JPG");
        User admin = new User("admin",passwordEncoder.encode("jelszo"),"ADMIN","JPG,PNG,GIF");

       setImageListByUser(user1, resources);
       setImageListByUser(user2, resources);
       setImageListByUser(admin, resources);

        List<User> users = Arrays.asList(user1, user2, admin);

        userRepository.saveAll(users);
    }

    private void setImageListByUser(User user, Resource[] resources) {
        for(Resource resource : resources) {
            String fileName = resource.getFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
            if(user.getPermissionList().contains(fileExtension)) {
                user.getImageList().add(new Image("images/" + fileName));
            }

        }
    }
}
