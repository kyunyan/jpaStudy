package hellojpa;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
public class Member3 {

    @Id @GeneratedValue
    @Column( name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn( name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;

        team.getMembers().add(this);
        // 양방향 연관관계 주의
        /*
        * 순수 객체 상태를 고려하여 항상 양쪽에 값을 설정
        * 연관관계 편의 메소드를 생성
        * 양방향 매핑시에 무한 루프를 조심
        * */
    }
}
