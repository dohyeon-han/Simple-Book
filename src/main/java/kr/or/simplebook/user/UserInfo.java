package kr.or.simplebook.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)//�⺻ ������ ����
@Entity //JPA�� �����ϴ� ��
@Getter
public class UserInfo implements UserDetails {

  @Id // primary key
  @Column(name = "code")// code column
  @GeneratedValue(strategy= GenerationType.IDENTITY) // �⺻ Ű ������ DB�� �ñ�
  private Long code;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "auth")
  private String auth;

  @Builder // ��� ������ ���ڷ� �޴� ������
  public UserInfo(String email, String password, String auth) {
    this.email = email;
    this.password = password;
    this.auth = auth;
  }

  // ������� ������ �ݷ��� ���·� ��ȯ
  // ��, Ŭ���� �ڷ����� GrantedAuthority�� �����ؾ���
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> roles = new HashSet<>();
    for (String role : auth.split(",")) {
      roles.add(new SimpleGrantedAuthority(role));
    }
    return roles;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  // ���� ���� ���� ��ȯ
  @Override
  public boolean isAccountNonExpired() {
    // ����Ǿ����� Ȯ���ϴ� ����
    return true; // true -> ������� �ʾ���
  }

  // ���� ��� ���� ��ȯ
  @Override
  public boolean isAccountNonLocked() {
    // ���� ��ݵǾ����� Ȯ���ϴ� ����
    return true; // true -> ��ݵ��� �ʾ���
  }

  // �н������� ���� ���� ��ȯ
  @Override
  public boolean isCredentialsNonExpired() {
    // �н����尡 ����Ǿ����� Ȯ���ϴ� ����
    return true; // true -> ������� �ʾ���
  }

  // ���� ��� ���� ���� ��ȯ
  @Override
  public boolean isEnabled() {
    // ������ ��� �������� Ȯ���ϴ� ����
    return true; // true -> ��� ����
  }
}