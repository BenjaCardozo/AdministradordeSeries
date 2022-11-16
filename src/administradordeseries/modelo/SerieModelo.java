package administradordeseries.modelo;

import administradordeseries.enums.Genero;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class SerieModelo implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    @Column (name = "fecha_estreno")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar fechaEstreno;
    
    private Integer estrellas;
    
    @Enumerated (EnumType.ORDINAL)
    private Genero genero;
    
    @Column (name = "precio_alquiler")
    private Double precioAlquiler;

    private Boolean atp;

    private String estado;

    //constructores
    public SerieModelo() {
    }

    public SerieModelo(Long id, String titulo, String descripcion, Calendar fechaEstreno, Integer estrellas, Genero genero, Long id_genero, Double precioAlquiler, Boolean atp, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaEstreno = fechaEstreno;
        this.estrellas = estrellas;
        this.genero = genero;
        this.precioAlquiler = precioAlquiler;
        this.atp = atp;
        this.estado = estado;
    }

    //get&set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
    
    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    
    public Double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }
    
    

    public Boolean isAtp() {
        return atp;
    }

    public void setAtp(Boolean atp) {
        this.atp = atp;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SerieModelo{" + "id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fechaEstreno=" + fechaEstreno + ", estrellas=" + estrellas + ", genero=" + genero + ", precioAlquiler=" + precioAlquiler + ", atp=" + atp + ", estado=" + estado + '}';
    }

}
