package br.com.lucas.attornatus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "street is required")
    private String street;

    @Column(name = "zipcode")
    @NonNull
    @NotBlank(message = "zip code is required")
    private String zipcode;

    @Column(name = "number")
    @NonNull
    @NotBlank(message = "number is required")
    private Integer number;

    @Column(name = "city")
    @NonNull
    @NotBlank(message = "city is required")
    private String city;

    @Column(name = "main_address")
    @NonNull
    @NotBlank(message = "main address is required")
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