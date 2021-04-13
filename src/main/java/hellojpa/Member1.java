package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name="NAME_AGE_UNIQUE" , columnNames = {"NAME"})})
public class Member1 {
    /*
    * @Column
    *
    * name : 필드와 매핑할 테이블의 컬럼이름
    * insertable , updatable : 등록 변경 가능 여부       default : true
    * nullable(DDL) null 값의 허용 여부를 설정한다 false 를 설정하면 DDL 생성시에 not null 제약조건이 붙는다 ex) nullable = false notnull 제약조건이 걸린다.
    * unique(DDL) @Table의 uniqueConstraints 와 같지만 한 컬럼에 간단히 유니크 제약조건을 걸때 사용한다.  실무에서는 잘사용하지 않음 왜냐면 제약조건이름이 지맘대로 나
    * columnDefinition(DDL) 데이터 베이스에 컬럼 정보를 직접 줄수 있따 varchar(100) default "EMPTY"
    * length(DDL) : 문자 길이 제약조건 String 타입에만 사용한다
    *
    * @Enumerated 무조건 String으로 사용하도록 할것!!
    * EnumType.ORDINAL : enum 순서를 데이터베이스에 저장
    * EnumType.STRING : enum 이름을 데이터 베이스에 저장
    *
    * @Temporal
    * 날짜 타입을 매핑할때 사용한다
    * 참고 : LocalDate, LocalDateTime을 사용 할때는 생략 가능 (최신 하이버네이트 지원)
    * */
    @Id     // pk 등록
    private Long id;

    // DDL 생성 기능은 DDL을 자동 생성할때만 사용되고 JPA 실행 로직에는 형향을 주지 않는다.
    @Column(unique = true, length = 10, name = "name")      // 컬럼매핑
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)                // enum 타입 매핑
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)      // 날짜 타입 매핑
    private Date createDate;               // 최초 생성시간

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;         // 최초 수정시간

    private LocalDate testLocalDate;        // 최신버전은 이렇게 사용한
    private LocalDateTime testLocalDateTime;

    @Lob                                // BLOB, CLOB 매
    private String description;

    @Transient              // 디비랑 연결 안되는 컬럼 메모리에서만 사용
    private int temp;

    public Member1(){

    }
}
