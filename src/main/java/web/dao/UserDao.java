package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    public List<User> index();
    public void addUser(User user);
    public void deleteUser(int id);
    public User getUserById(int id);
    public void updateUser(User user);


}
