package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    // Member Table insert query execute
    static void insertMember(EntityManager em) {
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");

        em.persist(member);
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

        System.out.println("findMember.id = " + findMember.getId());
        System.out.println("findMember.name = " + findMember.getName());
    }
}
