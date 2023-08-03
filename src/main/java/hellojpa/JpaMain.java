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

            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("=================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    // 영속성 컨텍스트 설명
    static void permanenceContextExplanation(EntityManager em, EntityTransaction tx) {
        Member member = new Member();
        member.setId(101L);
        member.setName("HelloJPA");

        System.out.println("=== BEFORE ===");
        em.persist(member);
        System.out.println("=== AFTER ===");

        Member findMember = em.find(Member.class, 101L);

        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());

        // 이때 insert 쿼리가 실행 하지만 select 쿼리는 실행하지 않음.
        // 이유는 1차 캐시인 영속성 컨텍스트에서 101L에 해당하는 값을 가져오기 때문
        // 만약 101L 값이 영속성 컨텍스트에 없는 경우 DB에서 값을 가져옴.
        tx.commit();
    }

    // Member Table jpql로 query작성 후 select
    static void selectJpqlMember(EntityManager em) {
        List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .setFirstResult(1)
                .setMaxResults(8)
                .getResultList();

        for (Member member : result) {
            System.out.println("member.name = " + member.getName());
        }
    }

    // Member Table insert query execute
    static void insertMember(EntityManager em) {
        // 비영속
        Member member = new Member();
        member.setId(2L);
        member.setName("HelloB");

        // 영속
        em.persist(member);

        // 준영속 - member 엔티티를 영속성 컨텍스트에서 분리
        /*em.detach(member);*/

        // 삭제 - 객체를 삭제
        /*em.remove(member);*/
    }

    // Member Table select query execute
    static void selectMember(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);

        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());
    }

    // Member Table update query execute
    static void updateMember(EntityManager em) {
        Member findMember = em.find(Member.class, 1L);
        findMember.setName("HelloJPA");

        // 아래 persist없이도 자동으로 findMember에 대한 값을 database에서 바꿔줌
        // em.persist(findMember);

        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());
    }
}
