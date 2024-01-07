package uni.isw.sigconbackend.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.service.PersonaService;

@RestController
@RequestMapping(path="api/v1/personas")
public class PersonaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
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
    @RequestMapping(value="/search",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> getPersona(@RequestBody Optional<Persona> persona){
        logger.info(">getPersona" + persona.toString());        
        try{
            persona=personaService.getPersona(persona.get().getId_persona());
        }
        catch(Exception e){
            logger.error("Excepcion inesperada al obtener la persona",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona.get(), HttpStatus.OK);
    }
    @RequestMapping(value="/agregar",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> agregar(@RequestBody Persona persona){
        logger.info(">agregar:"+persona.toString());
        try{
            personaService.saveOrUpdate(persona);
        }catch(Exception e){
            logger.error("Excepcion inesperada al agregar persona",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona, HttpStatus.OK);        
     }
    
    @RequestMapping(value="/actualizar",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> actualizar(@RequestBody Persona persona){
        logger.info(">actualizar:"+persona.toString());
        try{
            personaService.saveOrUpdate(persona);
        }catch(Exception e){
            logger.error("Excepcion inesperada al actualizar persona",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona, HttpStatus.OK);  
        
     }
    
    @RequestMapping(value="/eliminar",method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> eliminar(@RequestBody Optional<Persona> persona){
        logger.info(">eliminar:"+persona.toString());
        try{
            persona=personaService.getPersona(persona.get().getId_persona());
            if(persona.isPresent())
                personaService.delete(persona.get().getId_persona());
        }catch(Exception e){
            logger.error("Excepcion inesperada al actualizar persona",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persona.get(), HttpStatus.OK);        
     }
    
    
    
}
