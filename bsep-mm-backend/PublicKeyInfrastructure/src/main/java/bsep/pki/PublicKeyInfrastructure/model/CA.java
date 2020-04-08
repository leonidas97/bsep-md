package bsep.pki.PublicKeyInfrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Certificate certificate;

    @OneToMany(mappedBy = "issuedByCA", cascade = CascadeType.ALL)
    private Set<Certificate> issuedCertificates = new HashSet<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Set<CA> childs = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private CA parent;

    @Enumerated(value = EnumType.STRING)
    private CAType type;
}