package com.nadarkanloev.riotapiservice.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RiotUser {
    @Id
    @Basic
    @Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long row_id;
    @Basic
    @Column(name = "accountId")
    private String accountId;
    @Basic
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "profileIconId")
    private Integer profileIconId;
    @Basic
    @Column(name = "puuid")
    private String puuid;
    @Basic
    @Column(name = "revisionDate")
    private String revisionDate;
    @Basic
    @Column(name = "summonerLevel")
    private Integer summonerLevel;
}
