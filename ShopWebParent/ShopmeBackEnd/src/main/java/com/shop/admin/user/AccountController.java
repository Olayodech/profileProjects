package com.shop.admin.user;

import com.shop.admin.security.ShopMeUserDetails;
import com.shop.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/users/accounts")
    public String updateAccount(@AuthenticationPrincipal ShopMeUserDetails loggedUser, Model model){
        String email = loggedUser.getUsername();
        User getUserEmail = userServices.getUserByEmail(email);
        model.addAttribute("users", getUserEmail);
        return "account_form";
    }

    @PostMapping("/users/accounts/update")
    public String saveUpdateAccount(User user, RedirectAttributes redirectAttributes, @AuthenticationPrincipal ShopMeUserDetails loggedUser){
        User user1 = userServices.updateAccount(user);
        redirectAttributes.addFlashAttribute("message", "Account of" + user1.getEmail() + "is updated");
        loggedUser.setFirstName(user.getFirstName());
        loggedUser.setLastName(user.getLastName());
        return "redirect:/users/accounts";
    }
}
