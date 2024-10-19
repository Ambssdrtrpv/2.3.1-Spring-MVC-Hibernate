package kata.web.Service;

import org.springframework.stereotype.Component;
import kata.web.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
public interface UserService {

    List<User> show() throws SQLException;

    void save(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(int id) throws SQLException;

    Optional<User> findById(Integer id);

}
