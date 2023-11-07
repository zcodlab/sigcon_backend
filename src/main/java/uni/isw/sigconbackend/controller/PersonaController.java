package uni.isw.sigconbackend.controller;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.service.PersonaService;

@RestController
@RequestMapping(path="api/v1/personas")
public class PersonaController {
    private Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaService personaService;
    
    @RequestMapping(value="/listar",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> getPersonas(){
        logger.info("> getPersonas[Persona]");
        List<Persona> list=null;
        try{
            list=personaService.getPersonas();
            if (list==null)
                list=new ArrayList<>();            
        }
        catch(Exception e){
            logger.error("Excepcion inesperada al obtener la lista de personas",e);
            return new ResponseEntity<>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.info("> getPersonas[Persona]");        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
}
