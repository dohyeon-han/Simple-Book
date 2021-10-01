package kr.or.simplebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.or.simplebook.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByUserName(String username);
}
