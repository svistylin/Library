package dao;

import model.User;

public interface UserDao {

    void update(User user);

    void registerUser(User user);

    User getUserByEmail(String email);
}
