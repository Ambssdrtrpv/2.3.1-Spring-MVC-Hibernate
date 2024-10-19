package kata.web.controller;
import kata.web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import kata.web.model.User;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }
    @GetMapping()
    public String show(Model model) throws SQLException {
        model.addAttribute("users", userService.show());
        return "show";
    }

    @GetMapping("/new")
    public String saveForm(@ModelAttribute("user")User user){
        return "save";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors())
            return "save";

        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping ("/update")
    public String updateForm(@RequestParam(value = "id") int id, Model model) {
        Optional<User> userById = userService.findById(id);

        if (userById.isPresent()) {
            model.addAttribute("user", userById.get());
            return "update";
        } else {
            return "redirect:/users";
        }
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult) throws SQLException {
        if (bindingResult.hasErrors()) {
            return "update";
        }

        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id", required = true) int id) throws SQLException {
        userService.delete(id);
        return "redirect:/users";
    }
}
