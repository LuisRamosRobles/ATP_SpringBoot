package org.example.atp_project.mappers;

import org.example.atp_project.dto.tenista.CreateTenistaDto;
import org.example.atp_project.dto.tenista.GetTenistaDto;
import org.example.atp_project.dto.tenista.UpdateTenistaDto;
import org.example.atp_project.models.Tenista;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class TenistaMapper {

    public GetTenistaDto tenistaToGetDto(Tenista tenista) {
        GetTenistaDto dto = new GetTenistaDto();
        dto.setId(tenista.getId());
        dto.setRanking(tenista.getRanking());
        dto.setNombreCompleto(tenista.getNombreCompleto());
        dto.setPais(tenista.getPais());
        dto.setFechaNac(tenista.getFechaNac());
        dto.setEdad(Period.between(dto.getFechaNac(), LocalDate.now()).getYears());
        dto.setAltura(tenista.getAltura());
        dto.setPeso(tenista.getPeso());
        dto.setProfesionalDesde(tenista.getProfesionalDesde());
        dto.setManoPref(tenista.getManoPref());
        dto.setReves(tenista.getReves());
        dto.setEntrenador(tenista.getEntrenador());
        dto.setPriceMoney(tenista.getPriceMoney());
        dto.setBestRanking(tenista.getBestRanking());
        dto.setWin(tenista.getWin());
        dto.setLost(tenista.getLost());
        dto.setWin_rate(tenista.getWin_rate());
        dto.setImagen(tenista.getImagen());
        dto.setPuntos(tenista.getPuntos());
        dto.setTorneo(tenista.getTorneo());

        return dto;
    }

    public Tenista createDtoToTenista(CreateTenistaDto dto) {
        Tenista tenista = new Tenista();
        tenista.setRanking(dto.getRanking());
        tenista.setNombreCompleto(dto.getNombreCompleto());
        tenista.setPais(dto.getPais());
        tenista.setFechaNac(dto.getFechaNac());
        tenista.setEdad(Period.between(dto.getFechaNac(), LocalDate.now()).getYears());
        tenista.setAltura(dto.getAltura());
        tenista.setPeso(dto.getPeso());
        tenista.setProfesionalDesde(dto.getProfesionalDesde());
        tenista.setManoPref(dto.getManoPref());
        tenista.setReves(dto.getReves());
        tenista.setEntrenador(dto.getEntrenador());
        tenista.setPriceMoney(dto.getPriceMoney());
        tenista.setWin(dto.getWin());
        tenista.setLost(dto.getLost());
        tenista.setWin_rate(dto.getWin_rate());
        tenista.setImagen(dto.getImagen());
        tenista.setPuntos(dto.getPuntos());

        return tenista;
    }

    public Tenista updateDtoToTenista(UpdateTenistaDto dto, Tenista tenista) {
        tenista.setRanking(dto.getRanking() != null ? dto.getRanking() : tenista.getRanking());
        tenista.setNombreCompleto(dto.getNombreCompleto() != null ? dto.getNombreCompleto() : tenista.getNombreCompleto());
        tenista.setPais(dto.getPais() != null ? dto.getPais() : tenista.getPais());
        tenista.setFechaNac(dto.getFechaNac() != null ? dto.getFechaNac() : tenista.getFechaNac());
        tenista.setEdad(dto.getEdad() != null ? dto.getEdad() : tenista.getEdad());
        tenista.setAltura(dto.getAltura() != null ? dto.getAltura() : tenista.getAltura());
        tenista.setPeso(dto.getPeso() != null ? dto.getPeso() : tenista.getPeso());
        tenista.setProfesionalDesde(dto.getProfesionalDesde() != null ? dto.getProfesionalDesde() : tenista.getProfesionalDesde());
        tenista.setManoPref(dto.getManoPref() != null ? dto.getManoPref() : tenista.getManoPref());
        tenista.setReves(dto.getReves() != null ? dto.getReves() : tenista.getReves());
        tenista.setEntrenador(dto.getEntrenador() != null ? dto.getEntrenador() : tenista.getEntrenador());
        tenista.setPriceMoney(dto.getPriceMoney() != null ? dto.getPriceMoney() : tenista.getPriceMoney());
        tenista.setWin(dto.getWin() != null ? dto.getWin() : tenista.getWin());
        tenista.setLost(dto.getLost() != null ? dto.getLost() : tenista.getLost());
        tenista.setWin_rate(dto.getWin_rate() != null ? dto.getWin_rate() : tenista.getWin_rate());
        tenista.setImagen(dto.getImagen() != null ? dto.getImagen() : tenista.getImagen());
        tenista.setPuntos(dto.getPuntos() != null ? dto.getPuntos() : tenista.getPuntos());

        return tenista;

    }

}
