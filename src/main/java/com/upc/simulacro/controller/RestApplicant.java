package com.upc.simulacro.controller;

import com.upc.simulacro.Business.BusinessApplicant;
import com.upc.simulacro.dtos.ApplicantDTO;
import com.upc.simulacro.entitys.Applicant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestApplicant {
    @Autowired
    private BusinessApplicant businessApplicant;

    @PostMapping("/applicant")//1.-
    public ApplicantDTO register(@RequestBody ApplicantDTO applicantDTO) {
        Applicant applicant;
        try {
            applicant = convertToEntity(applicantDTO);
            applicant = businessApplicant.register(applicant);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No fue posible registrarlo");
        }
        return convertToDto(applicant);

    }

    @GetMapping("/applicant") //2.-
    public ResponseEntity<List<ApplicantDTO>>obtenerReporte(){
        List<Applicant> applicants;
List<ApplicantDTO> applicantDTOS;
try{
    applicants = businessApplicant.FindFiltered();
    applicantDTOS = convertToLisDto(applicants);
}catch (Exception e)
{
    throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible obtener listado");
}
return new ResponseEntity<>(applicantDTOS, HttpStatus.OK);
}

    @GetMapping("/applicantResultado") //5.-
    public List<ApplicantDTO>obtenerTotalReport(){
        List<ApplicantDTO> applicantDTOS;
        try{
            applicantDTOS = businessApplicant.obtenerResultCompletos();
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible obtener listado");
        }
        return applicantDTOS;
    }
    @GetMapping("/applicant/{xcodigo}")
    public ResponseEntity<String> IndicatorIFisSelected(@PathVariable (value = "xcodigo")
                                                      Long xcodigo) throws Exception

        {
            String evaluado;
            try {
                evaluado = businessApplicant.ShowDecitionOfCalculation(xcodigo);
            } catch(Exception e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible encontrar su registro");
            }
            return new ResponseEntity<String>(evaluado, HttpStatus.OK);
        }

    @PutMapping("/applicant/{codigo}")
    public ResponseEntity<ApplicantDTO> actualization(@PathVariable (value = "codigo")
                                                      Long codigo,  @RequestBody ApplicantDTO applicantDTO){
        Applicant applicant;
        Applicant applicantActualizated;
        try{
            applicant = convertToEntity(applicantDTO);
            applicantActualizated = businessApplicant.actualization(codigo, applicant);
            applicantDTO = convertToDto(applicantActualizated);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar el centro de salud");
        }
        return  new ResponseEntity<ApplicantDTO>(applicantDTO,HttpStatus.OK);
        }

        //IMPORNTANTES
    private ApplicantDTO convertToDto(Applicant applicant) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicantDTO applicantDTO  = modelMapper.map(applicant, ApplicantDTO.class);
        return applicantDTO;
    }

    private Applicant convertToEntity(ApplicantDTO applicantDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Applicant post = modelMapper.map(applicantDTO, Applicant.class);
        return post;
    }

    private List<ApplicantDTO> convertToLisDto(List<Applicant> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
