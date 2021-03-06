package hu.vir.controller;

import hu.vir.db.UserRepository;
import hu.vir.model.Image;
import hu.vir.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashMap;

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
            return "redirect:/index";
        }

        HashMap<String, String> userImages = new HashMap<>();
        for (Image img : user.getImageList()) {
            String imagePath = img.getName();
            String imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
            userImages.put(imagePath, imageName);
        }


        model.addAttribute("files", userImages);
        model.addAttribute("userPermission", user.getPermissionList());

        return "images";

    }
}
