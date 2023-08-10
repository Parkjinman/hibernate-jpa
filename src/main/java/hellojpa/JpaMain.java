package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMove = em.find(Movie.class, movie.getId());
            System.out.println("findMove = " + findMove);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    // 객체 지향 설계 했을때 테이블은 외래키를 참조 하지만
    // 객체는 객체 전체를 참조한다.
    static void selectObjectOrientedDesign(EntityManager em) {
        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member. setUsername("member1");
//            member.setTeamId(team.getId());
        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member.getId());

        Team findTeam = findMember.getTeam();
        System.out.println("findTeam = " + findTeam.getName());

        // 100번에 있는 팀정보를 가져온 후 찾는 멤버에 저장한다.
        Team newTeam = em.find(Team.class, 100L);
        findMember.setTeam(newTeam);
    }

    // 준영속 상태로 만드는 법
//    static void permanenceContextDetach(EntityManager em) {
//        Member member = em.find(Member.class, 150L);
//        member.setName("AAAAA");
//
//        // 영속성 컨텍스트에 있는 member 초기화
//        em.detach(member);
//
//        // 영속성 컨텍스트에 있는 모든 데이터 전체 초기화
//        em.clear();
//
//        // 영속성 컨텍스트 종료
//        em.close();
//    }
//
//    // 전체 커밋이 아닌 개별적으로 실행하는 방법
//    static void permanenceContextFlush(EntityManager em) {
//        Member member = new Member(200L, "member200", 15);
//        em.persist(member);
//
//        // 쓰기 지연 SQL 저장소에 있는 query를 DB에서 실행 시킨다.
//        // tx.commit(); → 이것과 기능은 같다.
//        em.flush();
//
//        System.out.println("=================");
//    }
//
//    // 영속성 컨텍스트 설명
//    static void permanenceContextExplanation(EntityManager em, EntityTransaction tx) {
//        Member member = new Member();
//        member.setId(101L);
//        member.setName("HelloJPA");
//        member.setAge(15);
//
//        System.out.println("=== BEFORE ===");
//        em.persist(member);
//        System.out.println("=== AFTER ===");
//
//        Member findMember = em.find(Member.class, 101L);
//
//        System.out.println("findMember.id = " + findMember.getId());
//        System.out.println("findMember.name = " + findMember.getName());
//
//        // 이때 insert 쿼리가 실행 하지만 select 쿼리는 실행하지 않음.
//        // 이유는 1차 캐시인 영속성 컨텍스트에서 101L에 해당하는 값을 가져오기 때문
//        // 만약 101L 값이 영속성 컨텍스트에 없는 경우 DB에서 값을 가져옴.
//        tx.commit();
//    }
//
//    // Member Table jpql로 query작성 후 select
//    static void selectJpqlMember(EntityManager em) {
//        List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                .setFirstResult(1)
//                .setMaxResults(8)
//                .getResultList();
//
//        for (Member member : result) {
//            System.out.println("member.name = " + member.getName());
//        }
//    }
//
//    // Member Table insert query execute
//    static void insertMember(EntityManager em) {
//        // 비영속
//        Member member = new Member();
//        member.setId(2L);
//        member.setName("HelloB");
//
//        // 영속
//        em.persist(member);
//
//        // 준영속 - member 엔티티를 영속성 컨텍스트에서 분리
//        /*em.detach(member);*/
//
//        // 삭제 - 객체를 삭제
//        /*em.remove(member);*/
//    }
//
//    // Member Table select query execute
//    static void selectMember(EntityManager em) {
//        Member findMember = em.find(Member.class, 1L);
//
//        System.out.println("findMember.id = " + findMember.getId());
//        System.out.println("findMember.name = " + findMember.getName());
//    }
//
//    // Member Table update query execute
//    static void updateMember(EntityManager em) {
//        Member findMember = em.find(Member.class, 1L);
//        findMember.setName("HelloJPA");
//
//        // 아래 persist없이도 자동으로 findMember에 대한 값을 database에서 바꿔줌
//        // em.persist(findMember);
//
//        System.out.println("findMember.id = " + findMember.getId());
//        System.out.println("findMember.name = " + findMember.getName());
//    }
}
