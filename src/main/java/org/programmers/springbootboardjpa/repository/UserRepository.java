package org.programmers.springbootboardjpa.repository;

import org.programmers.springbootboardjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    //@TODO Exist 성능 이슈 체크
    boolean existsByNicknameIgnoreCase(@Param("nickname") String nickname);
}