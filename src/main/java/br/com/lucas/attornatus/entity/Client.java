package br.com.lucas.attornatus.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name is required")
    @Size(min = 3)
    @NonNull
    private String name;

    @Column(name = "birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @NotBlank(message = "Birth date is required")
    @NonNull
    private String birthdate;

    @JsonIgnore
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Client(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }
    // getters and setters, constructors
}
