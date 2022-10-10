package com.xdavide9.turbobuy.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Sale")
@Table(name = "sale")
@Data
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
}
