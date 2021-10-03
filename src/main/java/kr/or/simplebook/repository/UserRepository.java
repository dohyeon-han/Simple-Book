package kr.or.simplebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import kr.or.simplebook.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByEmailAndPw(String email, String pw);
	Optional<UserInfo> findByEmail(String email);

}
