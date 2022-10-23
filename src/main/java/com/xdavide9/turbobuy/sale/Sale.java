package com.xdavide9.turbobuy.sale;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Sale")
@Table(name = "sale")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sale_generator"
    )
    @SequenceGenerator(
            name = "sale_generator",
            sequenceName = "sale_generator",
            allocationSize = 1
    )
    private Integer saleId;
    @Column(name = "app_user_id")
    private Integer appUserId;
    @Column(unique = true)
    private String title;
    private String description;

    public Sale(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return saleId != null && Objects.equals(saleId, sale.saleId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
