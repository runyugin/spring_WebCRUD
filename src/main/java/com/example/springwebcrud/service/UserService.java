package com.example.springwebcrud.service;

import com.example.springwebcrud.model.User;
import com.example.springwebcrud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс, представляющий сервис для обработки бизнес-логики, связанной с сущностью "пользователь".
 */
@Service
public class UserService {
    private final UserRepository userRepository;


    /**
     * Конструктор класса, внедряющий UserRepository через механизм внедрения зависимостей.
     * @param userRepository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Метод для получения списка всех пользователей из базы данных.
     * @return Список пользователей
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Метод для сохранения нового пользователя в базе данных.
     * @param user Новый пользователь
     * @return Созданный пользователь
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Метод для удаления пользователя из базы данных по его идентификатору.
     * @param id Идентификатор пользователя для удаления
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Метод для получения пользователя по его идентификатору.
     * @param id Идентификатор пользователя
     * @return Найденный пользователь
     */
    public User getOne(int id) {
        return userRepository.findById(id);
    }

    /**
     * Метод для обновления данных пользователя в базе данных.
     * @param user Обновляемый пользователь
     * @return Обновленный пользователь
     */
    public User updateUser(User user) {
        return userRepository.update(user);
    }
}
