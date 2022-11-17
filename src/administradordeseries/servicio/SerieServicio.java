package administradordeseries.servicio;

import administradordeseries.dao.SerieDAO;
import administradordeseries.enums.Genero;
import administradordeseries.modelo.SerieModelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class SerieServicio extends SerieDAO {
    
    
    public boolean registrarSerie(SerieModelo serieModelo) throws Exception {
        return registrar(serieModelo);
    }
    
    public List<SerieModelo> listarSeries() {

        return (List<SerieModelo>)em.createQuery("SELECT s "
                + "FROM SerieModelo s").getResultList();
    }

    public SerieModelo buscarPorId(Long id) {
        return (SerieModelo) em.createQuery("SELECT s "
                + "FROM SerieModelo s"
                + " WHERE s.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public boolean modificar(SerieModelo serieModelo) throws Exception {

        if (serieModelo != null) {
            SerieModelo serieModelo1 = buscarPorId(serieModelo.getId());

            serieModelo1.setDescripcion(serieModelo.getDescripcion());
            serieModelo1.setTitulo(serieModelo.getTitulo());
            serieModelo1.setEstrellas(serieModelo.getEstrellas());
            serieModelo1.setPrecioAlquiler(serieModelo.getPrecioAlquiler());
            serieModelo1.setAtp(serieModelo.isAtp());
            serieModelo1.setFechaEstreno(serieModelo.getFechaEstreno());
            serieModelo1.setGenero(serieModelo.getGenero());
            modificarDB(serieModelo1);
            return true;
            
        }
        return false;
    }

    public boolean eliminarSerie(Long id) throws Exception {
        if (id != null) {
            SerieModelo serieModelo1 = buscarPorId(id);
            eliminar(serieModelo1);
            return true;
        }
        return false;
    }

    public boolean anularSerie(Long id) throws Exception {
        if (id != null) {
            SerieModelo serieModelo1 = buscarPorId(id);
            serieModelo1.setEstado("AN");
            modificarDB(serieModelo1);
            return true;
        }
        return false;
    }

    public List<SerieModelo> consultar(String titulo, Genero genero, Integer estrellas,
            boolean atp, boolean menorCien, boolean menorQuinientos,
            boolean menorMil, boolean mayorMil) {

        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereCause = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("SELECT s FROM SerieModelo s");

        if (titulo != null && !titulo.isEmpty() && titulo.trim().length() > 0) {
            whereCause.add("upper(s.titulo) "
                    + "LIKE upper(concat('%', :titulo , '%')) ");
            paramaterMap.put("titulo", titulo);
        }

        if (atp) {
            whereCause.add("s.atp = true ");
        }
        if (genero != null && !genero.toString().isEmpty()) {
            whereCause.add("s.genero = :genero ");
            paramaterMap.put("genero", genero);
        }
        if (estrellas != null && !estrellas.toString().isEmpty()) {
            whereCause.add("s.estrellas = :estrellas ");
            paramaterMap.put("estrellas", estrellas);
        }
        if (menorCien) {
            whereCause.add("s.precioAlquiler <= 100 ");
        } else if (menorQuinientos) {
            whereCause.add("s.precioAlquiler <= 500 ");
        } else if (menorMil) {
            whereCause.add("s.precioAlquiler <= 1000 ");
        } else if (mayorMil) {
            whereCause.add("s.precioAlquiler > 1000 ");
        }

        queryBuilder.append(!whereCause.isEmpty() ? " WHERE " + String.join("AND ", whereCause) : "");

        Query jpaQuery = em.createQuery(queryBuilder.toString());

        for (String key : paramaterMap.keySet()) {
            jpaQuery.setParameter(key, paramaterMap.get(key));
        }

        System.out.println(jpaQuery.toString());

        System.out.println("Tamaño: " + jpaQuery.getResultList().size());
        return (List<SerieModelo>) jpaQuery.getResultList();

    }

}
