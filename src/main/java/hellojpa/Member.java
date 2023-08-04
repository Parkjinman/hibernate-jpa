/**
 * @fileName    : Member.java
 * @author      : exem_J
 * @since       : 2023-08-03
 * JPA - 객체와 테이블을 매핑하는 클래스
 */
package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id
    // id값이 auto increament 방식이어서 id값을 DB에서 생성 후 알 수 있기 때문
    // 트랜잭션 커밋 시 insert하지 않고 persist를 하는 경우 바로 insert를 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }
}
