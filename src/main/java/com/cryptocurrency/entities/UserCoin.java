package com.cryptocurrency.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_coins")
public class UserCoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_Id")
    private Long dbId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;

    private Long id;
    private String symbol;
    private Float price_usd;

    @CreationTimestamp
    @Column(name = "`create`")
    private Date creation;
    @UpdateTimestamp
    @Column(name = "`update`")
    private Date lastUpdate;
    @Version
    private Long version;
}
