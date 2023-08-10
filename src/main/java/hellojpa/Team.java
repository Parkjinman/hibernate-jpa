package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    // 연관관계의 주인을 정하는 기준
    // 연관관계의 주인은 외래 키의 위치를 기준으로 정하며 addMember와 같이 표시
    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
}
