package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id
    private Long id;
    private String name;

    // jpa는 동적으로 객체를 생성해 주어야 한다. 그래서 기본 생성자가 필요함.
    // 기본 생성자가 존재하지 않으면 에러가 발생함.
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
