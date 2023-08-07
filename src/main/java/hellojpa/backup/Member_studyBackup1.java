/**
 * @fileName    : Member.java
 * @author      : exem_J
 * @since       : 2023-08-03
 * JPA - 객체와 테이블을 매핑하는 클래스
 */
package hellojpa.backup;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

// class명과 다른 table명과 매핑할 경우 사용
//@Table(name = "Member")
// JPA가 관리하기 위해 붙음.
// name 속성도 제공함 개체명이 같은 경우 다르게 하기 위해 사용 하지만 헷갈려서 사용 권장 X
@Entity
@Getter @Setter
public class Member_studyBackup1 {

    @Id // 테이블 키 컬럼에 붙임.
    private Long id;

    // option중 name을 변경하는 것은 runtime에 영향을 주지만 unique나 length는 영향 X
    @Column(unique = true, length = 10)
    private String name;

    private int age;

    // jpa는 동적으로 객체를 생성해 주어야 한다. 그래서 기본 생성자가 필요함.
    // 기본 생성자가 존재하지 않으면 에러가 발생함.
    public Member_studyBackup1() {
    }

    public Member_studyBackup1(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
