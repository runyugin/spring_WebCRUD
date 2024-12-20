package com.example.springwebcrud.repositories;

import com.example.springwebcrud.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс-репозиторий для взаимодействия с базой данных в контексте сущности "пользователь".
 * Использует JdbcTemplate для выполнения SQL-запросов.
 */
@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    /**
     * Конструктор класса UserRepository.
     *
     * @param jdbc Объект JdbcTemplate, внедряемый Spring Framework для взаимодействия с базой данных.
     */
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Получает всех пользователей из базы данных.
     *
     * @return Список пользователей
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";
        return jdbc.query(sql, getUserRowMapper());
    }

    /**
     * Ищет пользователя по заданному идентификатору.
     *
     * @param id Идентификатор пользователя
     * @return Пользователь с заданным идентификатором или null, если не найден
     */
    public User findById(int id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        return jdbc.queryForObject(sql, getUserRowMapper(), id);
    }

    /**
     * Получает RowMapper для сопоставления данных из ResultSet с объектом User.
     *
     * @return RowMapper для User
     */
    private RowMapper<User> getUserRowMapper() {
        return (resultSet, i) -> mapUser(resultSet);
    }

    /**
     * Сопоставляет данные из ResultSet с объектом User.
     *
     * @param resultSet ResultSet с данными пользователя
     * @return Объект User с данными из ResultSet
     * @throws SQLException
     */
    private User mapUser(ResultSet resultSet) throws SQLException {
        User rowObject = new User();
        rowObject.setId(resultSet.getInt("id"));
        rowObject.setFirstName(resultSet.getString("firstName"));
        rowObject.setLastName(resultSet.getString("lastName"));
        return rowObject;
    }

    /**
     * Сохраняет нового пользователя в базе данных.
     *
     * @param user Пользователь для сохранения
     * @return Сохраненный пользователь
     */
    public User save(User user) {
//        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        String sql = "INSERT INTO userTable (firstName, lastName) VALUES (?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return user;
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param id Идентификатор пользователя для удаления
     */
    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    /**
     * Обновляет данные пользователя в базе данных.
     *
     * @param user Пользователь для обновления
     * @return Обновленный пользователь
     */
    public User update(User user) {
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
        return user;
    }

}
