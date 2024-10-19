package kata.web.Service;

import kata.web.dao.UserDao;
import org.springframework.stereotype.Service;
import kata.web.model.User;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> show() throws SQLException {
        return userDao.show();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void save(User user) throws SQLException {
        userDao.save(user);
    }

    @Override
    public void update(User user) throws SQLException {
        userDao.update(user);
    }

    @Override
    public void delete(int id) throws SQLException {
        userDao.delete(id);
    }
}
