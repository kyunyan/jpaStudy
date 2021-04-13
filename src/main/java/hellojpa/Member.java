package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name="NAME_AGE_UNIQUE" , columnNames = {"NAME"})})
public class  Member {
    
    @Id     // pk 등록
    private Long id;

    // DDL 생성 기능은 DDL을 자동 생성할때만 사용되고 JPA 실행 로직에는 형향을 주지 않는다.
    @Column(unique = true, length = 10, name = "name")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member(){

    }
}
