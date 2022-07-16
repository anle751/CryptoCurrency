package com.cryptocurrency.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_Id")
    private Long dbId;
    private String username;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserCoin> coinList;

    @CreationTimestamp
    @Column(name = "`create`")
    private Date creation;
    @UpdateTimestamp
    @Column(name = "`update`")
    private Date lastUpdate;
    @Version
    private Long version;
}
