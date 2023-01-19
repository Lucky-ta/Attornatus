package br.com.lucas.attornatus.entity;

import jakarta.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "street")
    @NonNull
    private String street;

    @Column(name = "zipcode")
    @NonNull
    private String zipcode;

    @Column(name = "number")
    @NonNull
    private Integer number;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "main_address")
    @NonNull
    private Boolean mainAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Address(String street, String zipcode,
            Integer number, String city, Boolean mainAddress, long testAddressId) {
        this.street = street;
        this.zipcode = zipcode;
        this.number = number;
        this.city = city;
        this.mainAddress = mainAddress;
        this.id = testAddressId;
    }

    // getters and setters, constructors
}