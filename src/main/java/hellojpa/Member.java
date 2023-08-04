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
import java.util.Date;

@Entity
@Getter @Setter
public class Member {

    @Id
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member() {
    }
}
