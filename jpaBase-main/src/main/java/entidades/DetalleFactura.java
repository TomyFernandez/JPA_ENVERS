package entidades;
import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "detallefactura")
@Audited

public class DetalleFactura implements Serializable{
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="cantidad")
    private int cantidad;
    @Column(name="subtotal")
    private int subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST) //lo hacemos en cascadetype.PERSIST por que queremos persistir un cliente si es que no existe al generar una factura, pero si borro una factura o la modifico no se modifica el cliente
    @JoinColumn(name="fk_articulo")
    private Articulo articulo; 

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="fk_factura")
    private Factura factura;
}
