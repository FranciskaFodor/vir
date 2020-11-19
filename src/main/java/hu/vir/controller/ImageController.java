package hu.vir.controller;

import hu.vir.db.UserRepository;
import hu.vir.model.Image;
import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ImageController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("images")
    public String findImages(Model model, Principal principal)  {

        if (principal == null) {
            return "redirect:/index";
        }
        User user;
        try {
            user = userRepository.findByUsername(principal.getName());
        } catch (Exception e) {
            return "redirect:/find";
        }

        List<String> userImages = new ArrayList();
        for (Image img : user.getImageList()) {
            userImages.add(img.getName());
        }


        model.addAttribute("files", userImages);
        model.addAttribute("userPermission", user.getPermissionList());

        return "images";

    }
}
