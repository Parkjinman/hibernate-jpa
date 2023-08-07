/**
 * @fileName    : Member.java
 * @author      : exem_J
 * @since       : 2023-08-03
 * JPA - 객체와 테이블을 매핑하는 클래스
 */
package hellojpa.backup;

import hellojpa.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
public class Member_studyBackup2 {

    @Id
    private Long id;

    /*
    * updatable: 변경 여부
    * nullable: null값 허용 여부
    * unique: unique는 사용하는 경우 명칭이 이상하기 때문에 운영환경에서는 불필요함 / @Table(uniqueConstraints = ) 이 방식을 많이 사용함
    * columnDefinition: 직접 명시할 수 있음
    * */
    @Column(name = "name", updatable = false, nullable = false, unique = true, columnDefinition = "varchar(100) default") // 컬럼 매핑
    private String username;

    private int age;

    /*
    * @Enumerated의 옵션은 STRING과 ORDINAL이 존재하는데 ORDINAL은 순서를 저장하는 것이다.
    * 이때 enum타입의 변수를 추가하는 경우 순서가 꼬여서 데이터에 문제가 발생할 수 있다. 그래서 STRING을 권장한다.
    * */
    @Enumerated(EnumType.STRING) // enum 타입 매핑
    private RoleType roleType;

    @Temporal(TemporalType.DATE) // 날짜 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate localDate; // date 타입 = @Temporal의 TemporalType.DATE와 같음
    private LocalDateTime localDateTime; // timestamp 타입 = @Temporal의 TemporalType.TIMESTAMP와 같음

    @Lob // BLOB, CLOB 매핑
    private String description;

    @Transient // DB와 관련 없이 메모리에서만 사용
    private int temp;

    public Member_studyBackup2() {
    }
}
