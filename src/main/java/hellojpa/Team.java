package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column( name = "TEAM_ID")
    private Long id;

    private String name;

    // 연관관계의 주인과 mappedBy
    // 객체와 테이블간의 연관관계를 맺는 차이를 이해해야된다.
    // 객체의 양방향 관계
    // 객체의 양방향 관계는 사싱 양방향 관계가가 아니라 서로 다른 단방향 관계 2개다.
    // 객체를 양방향으로 참조하려면 단방향 연관관계르르 2개 만들어야 한다.

    // 테이블의 양방향 관계
    // 테이블은 왜래키 하나로 두 테이블의 연관관계를 가짐
    // TEAM_ID 왜래키 하나로 양방향 연관관계를 가질수있다.
    /*
    * 연관관계의 주인은
    * 양방향 매핑 규칙
    *
    * 객체의 두 관계중 하나를 연관관계의 주인으로 지정
    * 연관관계의 주인만이 왜래키를 관리
    * 주인이 아닌쪽은 읽기만 가능
    * 주인은 mappedBy 속성 사용 x
    * 주인이 아니면 mappedBy 속성의로 주인 지정
    *
    * 누구를 주인으로
    * 왜래키가 있는 곳을 주인으로 정해라라.
    * 
    * 여기서는 Memeber.team이 주인
    * */


   @OneToMany(mappedBy = "team")       // 모랑 연결되 있지
    private List<Member3> members = new ArrayList<>();

    public List<Member3> getMembers() {
        return members;
    }

    public void setMembers(List<Member3> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
