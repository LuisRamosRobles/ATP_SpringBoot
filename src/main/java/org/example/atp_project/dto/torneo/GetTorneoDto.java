package org.example.atp_project.dto.torneo;

import lombok.Data;
import org.example.atp_project.models.Tenista;
import org.example.atp_project.models.Torneo;

import java.time.LocalDate;
import java.util.List;

@Data
public class GetTorneoDto {

    private Long id;

    private String nombre;

    private String ubicacion;

    private Torneo.TipoTorneo tipoTorneo;

    private Torneo.Categoria categoria;

    private Torneo.Superficie superficie;

    private Integer entradas;

    private LocalDate fecha_ini;

    private LocalDate fecha_fin;

    private double premio;

    private String imagen;

    private List<Tenista> tenistas;

}
