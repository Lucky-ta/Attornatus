package br.com.lucas.attornatus.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NonNull
    private LocalDate birthdate;

    @JsonIgnore
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    // getters and setters, constructors
}
