package hu.vir.controller;

import hu.vir.db.UserRepository;
import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ImageViewerController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("users")
    public List<User> users(){
        return userRepository.findAll();
    }
}
