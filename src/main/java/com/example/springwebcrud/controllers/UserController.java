package com.example.springwebcrud.controllers;

import com.example.springwebcrud.model.User;
import com.example.springwebcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Контроллер, обрабатывающий HTTP-запросы, связанные с сущностью "пользователь".
 */
@Controller
public class UserController {
    private final UserService userService;

    /**
     * Конструктор контроллера, внедряющий UserService через механизм внедрения зависимостей.
     *
     * @param userService Сервис для выполнения бизнес-логики с пользователями.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Обработчик HTTP GET-запроса для получения списка всех пользователей.
     *
     * @param model Модель, предоставляющая данные для представления.
     * @return Название представления "user-list".
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    /**
     * Обработчик HTTP GET-запроса для отображения формы создания нового пользователя.
     *
     * @param user Пустой объект пользователя для заполнения формы.
     * @return Название представления "user-create".
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    /**
     * Обработчик HTTP POST-запроса для создания нового пользователя.
     *
     * @param user Данные нового пользователя из формы.
     * @return Перенаправление на страницу со списком пользователей.
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Обработчик HTTP GET-запроса для отображения формы удаления пользователя.
     *
     * @param id Идентификатор пользователя для удаления.
     * @return Перенаправление на страницу со списком пользователей после удаления.
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Обработчик HTTP GET-запроса для отображения формы обновления данных пользователя.
     *
     * @param id    Идентификатор пользователя, чьи данные нужно обновить.
     * @param model Модель, предоставляющая данные для представления.
     * @return Название представления "user-update".
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Обработчик HTTP POST-запроса для обновления данных пользователя.
     *
     * @param user Данные пользователя для обновления.
     * @return Перенаправление на страницу со списком пользователей после обновления.
     */
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }
}
