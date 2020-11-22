package hu.vir.controller;

import hu.vir.db.DbInit;
import hu.vir.db.UserRepository;
import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DbInit dbInit;

    @GetMapping("index")
    public String index(Model model){

        List<User> userList = new ArrayList<>();
        try {
            userList = userRepository.findAll();
        } catch (Exception e) {
            return "redirect:/index";
        }

        model.addAttribute("users", userList);

        return "admin/index";
    }

    @GetMapping("findOne")
    @ResponseBody
    public User findOne(long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("saveUser")
    public String saveUser(User user) throws IOException {

        User modifiedUser = new User();
        if(userRepository.findById(user.getId()).isPresent()){
            modifiedUser = userRepository.findById(user.getId()).get();
        } else {
            return "redirect:/admin/index";
        }
        modifiedUser.setPermissions(user.getPermissions().toUpperCase());

        dbInit.setImageListByUser(modifiedUser);

        userRepository.save(modifiedUser);

        return "redirect:/admin/index";
    }

    @GetMapping("users")
    @ResponseBody
    public List<User> users() {
        return userRepository.findAll();
    }
}
