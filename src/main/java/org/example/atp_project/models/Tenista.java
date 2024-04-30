package org.example.atp_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor(force = true) @AllArgsConstructor @Builder
@Entity
public class Tenista {

    public static String  IMAGE_DEFAULT = "https://via.placeholder.com/150";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer ranking;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column
    private String pais;

    @Column(name = "fecha_nac")
    private LocalDate fechaNac;

    @Column
    private Integer edad;

    @Column
    private Double altura;

    @Column
    private Double peso;

    @Column(name = "profesional_desde")
    private LocalDate profesionalDesde;

    @Column(name = "mano_pref")
    private Mano manoPref;

    @Column
    private Reves reves;

    @Column
    private String entrenador;

    @Column(name = "price_money")
    private Double priceMoney;

    @Column(name = "best_ranking")
    private Integer bestRanking;

    @Column
    private Integer win;

    @Column
    private Integer lost;

    @Column
    private String win_rate;

    @Column
    @Builder.Default //ESTA ANOTACION SIRVE PARA PONER UN VALOR POR DEFECTO
    private String imagen = IMAGE_DEFAULT;

    @Column
    private Integer puntos;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "torneo_id")
    private Torneo torneo;




    public enum Mano {
        DIESTRO,
        ZURDO
    }

    public enum Reves {
        UNA,
        DOS
    }



}


