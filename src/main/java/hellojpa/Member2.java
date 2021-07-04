/*
package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name="NAME_AGE_UNIQUE" , columnNames = {"NAME"})})
*/
/*
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR" ,
        table = "MY_SEQUENCES" ,
        pkColumnValue = "MEMBER_SEQ" , allocationSize = 1
)
 *//*

public class Member2 {
    */
/*
    *   집접할당 : @Id
    *   자동생성 : @GeneratedValue
    *    - IDENTITY : 데이터베이스 위임 MYSQL
    *    - SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용, ORACLE  @SequenceGenerator 필요
    *    - TABLE : 키 생성용 테이블 사용, 모든 DB 에서 사용 @TableGenerator 필요
    *    - AUTO : 방언에 따라 자동 지
    *
    *   IDENTITY 전략 특징
    *  - 기본 키를 데이터 베이스에 위임
    *  - JPA는 보통 트랜잭션 커밋 시점에 INSERT SQL 실행
    *  - AUTO_INCREMENT 는 데이터베이스에 INSERT SQL을 실행한 이후에 ID값을 알 수 있음
    *  - IDENTITY 전략은 em.persist() 시점에 INSERT SQL 실행하고 DB에서 식별자 조회
    *
    *   Sequence 전략 특징
    *  - 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터 베이스 오브젝트 (주로 오라클 이용
    *
    *   TABLE 전략
    *  - 키 생성 전용 테이블을 하나를 만들어서 데이터베이스 시퀀스를 흉내내는 전략
    *
    *  - 장점 : 모든 데이터베이스에 적용 가능
    *  - 단점 : 성능
    *
    *  권장하는 식별자 전략
    *  - 기본 키 제약 조건 : null 아님, 유일 , 변하면 안된다.
    *  - 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키를 사용하자
    *  - 예를들어 주민등록번호도 기본 키로 적절하지 않다
    *  - 권장 : Long형 + 대체키 + 키 생성전략 사용
    *
    *
    * *//*

    @Id     // pk 등록
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    */
/*
    @GeneratedValue(strategy = GenerationType.TABLE ,
                        generator = "MEMBER_SEQ_GENERATOR")

     *//*

    private Long id;

    // DDL 생성 기능은 DDL을 자동 생성할때만 사용되고 JPA 실행 로직에는 형향을 주지 않는다.
    @Column(length = 10, name = "name", nullable = false)      // 컬럼매핑
    private String name;

    public Member2(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
*/
