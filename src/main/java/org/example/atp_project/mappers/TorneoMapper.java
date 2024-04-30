package org.example.atp_project.mappers;

import org.example.atp_project.dto.torneo.CreateTorneoDto;
import org.example.atp_project.dto.torneo.GetTorneoDto;
import org.example.atp_project.dto.torneo.UpdateTorneoDto;
import org.example.atp_project.models.Torneo;
import org.springframework.stereotype.Component;

@Component
public class TorneoMapper {

    public GetTorneoDto torneoToGetDto(Torneo torneo) {
        GetTorneoDto dto = new GetTorneoDto();
        dto.setId(torneo.getId());
        dto.setNombre(torneo.getNombre());
        dto.setUbicacion(torneo.getUbicacion());
        dto.setTipoTorneo(torneo.getTipoTorneo());
        dto.setCategoria(torneo.getCategoria());
        dto.setSuperficie(torneo.getSuperficie());
        dto.setEntradas(torneo.getEntradas());
        dto.setFecha_ini(torneo.getFecha_ini());
        dto.setFecha_fin(torneo.getFecha_fin());
        dto.setPremio(torneo.getPremio());
        dto.setImagen(torneo.getImagen());
        dto.setTenistas(torneo.getTenistas());

        return dto;
    }

    public Torneo createDtoToTorneo(CreateTorneoDto dto) {
        Torneo torneo = new Torneo();
        torneo.setNombre(dto.getNombre());
        torneo.setUbicacion(dto.getUbicacion());
        torneo.setTipoTorneo(dto.getTipoTorneo());
        torneo.setCategoria(dto.getCategoria());
        torneo.setSuperficie(dto.getSuperficie());
        torneo.setEntradas(dto.getEntradas());
        torneo.setFecha_ini(dto.getFecha_ini());
        torneo.setFecha_fin(dto.getFecha_fin());
        torneo.setPremio(dto.getPremio());
        torneo.setImagen(dto.getImagen());

        return torneo;
    }

    public Torneo updateDtoToTorneo(UpdateTorneoDto dto, Torneo torneo) {
        torneo.setNombre(dto.getNombre() != null ? dto.getNombre() : torneo.getNombre());
        torneo.setUbicacion(dto.getUbicacion() != null ? dto.getUbicacion() : torneo.getUbicacion());
        torneo.setTipoTorneo(dto.getTipoTorneo() != null ? dto.getTipoTorneo() : torneo.getTipoTorneo());
        torneo.setCategoria(dto.getCategoria() != null ? dto.getCategoria() : torneo.getCategoria());
        torneo.setSuperficie(dto.getSuperficie() != null ? dto.getSuperficie() : torneo.getSuperficie());
        torneo.setEntradas(dto.getEntradas() != null ? dto.getEntradas() : torneo.getEntradas());
        torneo.setFecha_ini(dto.getFecha_ini() != null ? dto.getFecha_ini() : torneo.getFecha_ini());
        torneo.setFecha_fin(dto.getFecha_fin() != null ? dto.getFecha_fin() : torneo.getFecha_fin());
        torneo.setPremio(dto.getPremio() != null ? dto.getPremio() : torneo.getPremio());
        torneo.setImagen(dto.getImagen() != null ? dto.getImagen() : torneo.getImagen());

        return torneo;
    }



}
