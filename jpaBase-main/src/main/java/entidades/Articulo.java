package entidades;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity 
@Table(name = "articulo") 
@Audited



public class Articulo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id //para poder implementar un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para que el ID sea autoincremental
    private Long id;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name="denominacion")
    private String denominacion;
    @Column(name="precio")
    private int precio;

    @OneToMany(mappedBy = "articulo")
    @Builder.Default
    private  List<DetalleFactura> articulosDetalleFactura = new ArrayList<>();
    

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //usamos persist y merge por que se persiste las categorias o se actualicen pero si se elimina un articulo NO SE ELIMINAN LAS CATEGORIAS
    @JoinTable(
        name="articulo_categoria",
        joinColumns = @JoinColumn(name = "articuloID"),
        inverseJoinColumns = @JoinColumn(name = "categoriaID") //esta jointable genera la tabla de la relacion
    )
    @Builder.Default
    private List<Categoria> categorias = new ArrayList<>();

}
