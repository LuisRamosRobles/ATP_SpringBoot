package org.example.atp_project.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Torneo {

    public static String  IMAGE_DEFAULT = "https://via.placeholder.com/150";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String ubicacion;

    @Column(name = "tipo_torneo")
    private TipoTorneo tipoTorneo;

    @Column
    private Categoria categoria;

    @Column
    private Superficie superficie;

    @Column
    private int entradas;

    @Column
    private LocalDate fecha_ini;

    @Column
    private LocalDate fecha_fin;

    @Column
    private double premio;

    @Column
    @Builder.Default
    private String imagen = IMAGE_DEFAULT;

    @JsonManagedReference
    @OneToMany(mappedBy = "torneo", cascade = CascadeType.ALL)
    private List<Tenista> tenistas;

    public enum TipoTorneo {
        INDIVIDUAL_DOBLES,
        INDIVIDUAL,
        DOBLES

    }

    public enum Categoria {
        MASTERS_1000,
        MASTERS_500,
        MASTERS_250
    }

    public enum Superficie {
        PISTA_DURA,
        HIERBA,
        TIERRA_BATIDA
    }

}
