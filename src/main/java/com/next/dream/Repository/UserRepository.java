package com.next.dream.Repository;

import com.next.dream.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述：〈用户处理〉
 *
 * @author liyaohua
 * @create 2018/2/11
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

}
