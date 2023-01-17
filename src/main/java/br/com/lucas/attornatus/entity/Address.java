package br.com.lucas.attornatus.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "publicPace")
    private String publicPace;

    @Column(name = "cep")
    private int cep;

    @Column(name = "number")
    private int number;

    @Column(name = "city")
    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Client user;
}
