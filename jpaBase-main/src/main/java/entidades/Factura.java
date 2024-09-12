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
@Entity
@Table(name="factura")
@Audited

public class Factura implements Serializable{
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="fecha")
    private String fecha;
    @Column(name="numero")
    private int numero;
    @Column(name="total")
    private int total;

    @ManyToOne(cascade = CascadeType.PERSIST) //lo hacemos en cascadetype.PERSIST por que queremos persistir un cliente si es que no existe al generar una factura, pero si borro una factura o la modifico no se modifica el cliente
    @JoinColumn(name="fk_cliente")
    private Cliente cliente; 
    
    //UNIDIRECCIONAL
   // @OneToMany(cascade = CascadeType.ALL,//si elimino una factura se eliminan todos los detalles
   // orphanRemoval = true) //esto elimina todos los detalles al eliminar una factura

    //BIDIRECCIONAL
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private final List<DetalleFactura> detalleFactura = new ArrayList<DetalleFactura>();
    
}
