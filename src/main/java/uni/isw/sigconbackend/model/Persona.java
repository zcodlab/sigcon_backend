package uni.isw.sigconbackend.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_persona;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombres;

    public Persona() {
    }

    public Persona(Long id_persona, String nombres) {
        this.id_persona = id_persona;
        this.nombres = nombres;
    }

    public Persona(String apellido_paterno, String apellido_materno, String nombres) {
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombres = nombres;
    }
    
    
            
    
}
