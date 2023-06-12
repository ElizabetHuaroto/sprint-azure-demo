package com.upc.simulacro.Business;

import com.upc.simulacro.dtos.ApplicantDTO;
import com.upc.simulacro.entitys.Applicant;
import com.upc.simulacro.entitys.Status;
import com.upc.simulacro.repository.RepositoryApplicant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service //IMPORTANTE
public class BusinessApplicant {
    //le estamos inyectando el repositorio
    @Autowired
    private RepositoryApplicant repositoryApplicant;

    public double calcularCalification(ApplicantDTO applicantDTO){
        Double civilFactor=0.0;
        Integer bagFactor = 0;
        switch (applicantDTO.getStatus()){
            case soltero: civilFactor= 0.5; break;
            case casado: civilFactor= 1.0; break;
            case conviviente: civilFactor= 1.0; break;
            case divorciado: civilFactor= 0.5; break;
            case viudo: civilFactor= 0.5; break;

        }
        if (applicantDTO.isBag()) {
            bagFactor = 20;
        } else {
            bagFactor = 0;
        }

        return ( (applicantDTO.getAge()*civilFactor)+
                (applicantDTO.getSalary()/1000.00) + bagFactor);
    }
public String CalculatioFinalResult(ApplicantDTO applicantDTO){
        if(calcularCalification(applicantDTO)>60){
            return "Es elegible";
        }else{
            return "No es elegible";
        }

    }

    //creo que lo que hace este es recibir un código y el te devuelve el double de calcular
public double calcularCalification(Long codigo){
     Applicant applicant = repositoryApplicant.findById(codigo).get();
     ApplicantDTO applicantDTO = convertToDto(applicant);
     return calcularCalification(applicantDTO);
}

public String ShowDecitionOfCalculation(Long codigo){
        Applicant applicant = repositoryApplicant.findById(codigo).get();
        ApplicantDTO applicantDTO = convertToDto(applicant);
        return CalculatioFinalResult(applicantDTO);
}
public Applicant register(Applicant applicant){
    Applicant appli = repositoryApplicant.save(applicant);
    return appli;
}

public List<Applicant>FindFiltered(){return repositoryApplicant.findApplicantByStatus(Status.soltero);}

    public List<Applicant> obtnerListaCompleta(){ return repositoryApplicant.findAll();}
    public List<ApplicantDTO> obtenerResultCompletos(){
        List<Applicant> applicants;
        applicants = obtnerListaCompleta();
        List<ApplicantDTO> applicantDTOS;
        applicantDTOS =convertToLisDto(applicants);

        for(ApplicantDTO p:applicantDTOS){
            p.setCalification(calcularCalification(p));
        }
        Collections.sort(applicantDTOS, Comparator.comparing(ApplicantDTO::getName));
        return applicantDTOS;
    }
    public Applicant actualization(Long id, Applicant applicant){
        Applicant appliDesactual =repositoryApplicant.findById(id).get();
        applicant.setDni(id);
        return repositoryApplicant.save(applicant);
    }

    //SIEMPRE
    private ApplicantDTO convertToDto(Applicant aplicant) {
        ModelMapper modelMapper = new ModelMapper();
        ApplicantDTO applicantDTO = modelMapper.map(aplicant, ApplicantDTO.class);
        return applicantDTO;
    }

    private List<ApplicantDTO> convertToLisDto(List<Applicant> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}
