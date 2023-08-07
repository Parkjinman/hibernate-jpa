package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Transient
    private Member member; //관계형 DB를 객체 지향적으로 설계할 때 꼭 필요한 변수 세팅

    private LocalDateTime orderDate; // boot에서는 orderDate를 create할 때 자동으로 order_date로 변환해서 저장

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
