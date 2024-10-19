package kata.web.dao;

import org.springframework.stereotype.Repository;
import kata.web.model.User;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> show() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void update(User user) {

        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {//TODO
        Optional<User> userById = findById(id);
        userById.ifPresent(user -> entityManager.remove(user));

    }
}
