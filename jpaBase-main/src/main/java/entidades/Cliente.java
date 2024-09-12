package entidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity //va a ser una entidad y se va a guardar en la DB
@Table(name = "cliente") //nombre en tabla
@Audited

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id //para poder implementar un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que el ID sea autoincremental
    private Long id;

    @Column(name = "nombre") //para indicarle el nombre de la columna, si no esta toma el nombre de aca
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni", unique = true)
    private int dni;

    @OneToOne(cascade = CascadeType.ALL) //esto hace que cualquier cambio que realice en Cliente se ve reflejado en el domicilio 
    @JoinColumn(name ="fk_domicilio")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    @Builder.Default
    private final List<Factura> facturasCliente = new ArrayList<Factura>();

    
}
