package org.example.atp_project.dto.tenista;


import lombok.Data;
import org.example.atp_project.models.Tenista;
import org.example.atp_project.models.Torneo;

import java.time.LocalDate;

@Data
public class GetTenistaDto {

    private Long id;

    private Integer ranking;

    private String nombreCompleto;

    private String pais;

    private LocalDate fechaNac;

    private Integer edad;

    private Double altura;

    private Double peso;

    private LocalDate profesionalDesde;

    private Tenista.Mano manoPref;

    private Tenista.Reves reves;

    private String entrenador;

    private Double priceMoney;

    private Integer bestRanking;

    private Integer win;

    private Integer lost;

    private String win_rate;

    private String imagen;

    private Integer puntos;

    private Torneo torneo;

}
