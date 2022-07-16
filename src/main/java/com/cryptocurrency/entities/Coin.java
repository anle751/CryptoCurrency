package com.cryptocurrency.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "coins")
@JsonIgnoreProperties
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_Id")
    private Long dbId;

    private Long id;
    private String symbol;
    private String name;
    private String nameid;
    @Column(name = "rank_")
    private Long rank;
    private Float price_usd;
    private Float percent_change_24h;
    private Float percent_change_1h;
    private Float percent_change_7d;
    private Float market_cap_usd;
    private Float volume24;
    private Float volume24_native;
    private Float csupply;
    private Float price_btc;
    private Float tsupply;
    private Float msupply;

    @CreationTimestamp
    @Column(name = "`create`")
    private Date creation;
    @UpdateTimestamp
    @Column(name = "`update`")
    private Date lastUpdate;
}
