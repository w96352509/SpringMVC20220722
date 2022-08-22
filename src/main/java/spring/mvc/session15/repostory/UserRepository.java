package spring.mvc.session15.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.mvc.session15.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
