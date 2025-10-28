package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //выводим пользователей на стр
    @GetMapping()
    public String index(Model model) {
        //получим всех людей из БД и передадим на отображение в представление
        model.addAttribute("users", userService.index());
        return "user";
    }

    @GetMapping("/new")
    public String showAddUserForm() {
        return "addUser";
    }

    // Метод для обработки формы добавления пользователя
    @PostMapping("/save")
    public String saveUser(@RequestParam("age") int age,
                           @RequestParam("name") String name,
                           @RequestParam("surname") String surname) {
        User user = new User(age, name, surname);
        userService.addUser(user);
        return "redirect:/users"; // перенаправление на страницу со списком
    }

    // Метод для удаления пользователя
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }


    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser"; // название HTML файла
    }

    // метод для изменения
    @PostMapping("/update")
    public String updateUser(@RequestParam("id") int id,
                             @RequestParam("age") int age,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname) {
        User user = new User(age, name, surname);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }


}
