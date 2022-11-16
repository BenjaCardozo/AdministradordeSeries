package administradordeseries.modelo;

import administradordeseries.enums.Genero;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-11-16T18:50:24")
@StaticMetamodel(SerieModelo.class)
public class SerieModelo_ { 

    public static volatile SingularAttribute<SerieModelo, String> descripcion;
    public static volatile SingularAttribute<SerieModelo, Double> precioAlquiler;
    public static volatile SingularAttribute<SerieModelo, String> estado;
    public static volatile SingularAttribute<SerieModelo, Calendar> fechaEstreno;
    public static volatile SingularAttribute<SerieModelo, Genero> genero;
    public static volatile SingularAttribute<SerieModelo, String> titulo;
    public static volatile SingularAttribute<SerieModelo, Long> id;
    public static volatile SingularAttribute<SerieModelo, Boolean> atp;
    public static volatile SingularAttribute<SerieModelo, Integer> estrellas;

}