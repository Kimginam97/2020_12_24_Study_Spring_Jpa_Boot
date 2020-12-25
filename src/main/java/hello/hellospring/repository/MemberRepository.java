package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);  // Member 엔티티를 DB에 저장한다
    Optional<Member> findById(Long id);  // 엔티티 값의 id를 찾는다  where id =?
    Optional<Member> findByName(String name);  // 엔티티 값의 name을 찾는다 where name=?
    List<Member> findAll(); //엔티티의 값을 모두 조회  select * from Member

}
