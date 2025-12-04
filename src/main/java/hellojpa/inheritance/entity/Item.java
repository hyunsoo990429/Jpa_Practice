package hellojpa.inheritance.entity;

import jakarta.persistence.*;

@Entity
//SINGLE_TABLE이 기본 전략이다
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED) //조인 전략 사용
@Inheritance
@DiscriminatorColumn //dtype 설정, 컬럼에 들어가는 값의 기본 전략이 엔티티 명이다.
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
