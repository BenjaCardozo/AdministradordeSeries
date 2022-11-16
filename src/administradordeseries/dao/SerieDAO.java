package administradordeseries.dao;

import administradordeseries.enums.Genero;
import administradordeseries.modelo.SerieModelo;
import static administradordeseries.modelo.SerieModelo_.estrellas;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;

public class SerieDAO {

    SerieModelo serieModelo;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdministradorDeSeriesPU");

    EntityManager em = emf.createEntityManager();

    public boolean registrar(SerieModelo serieModelo) throws Exception {

        if (serieModelo == null) {
            throw new Exception("Debe indicar una serie");
        }

        try {

            em.getTransaction().begin();
            em.persist(serieModelo);
            em.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No fue posible registrar al usuario");
            return false;
        }
    }

    public boolean modificarDB(SerieModelo serieModelo) throws Exception {
        if (serieModelo == null) {
            throw new Exception("Debe indicar una serie");
        }

        try {

            em.getTransaction().begin();
            em.merge(serieModelo);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("No se ha podido modificar");
        }
        return true;
    }

    public boolean eliminar(SerieModelo serieModelo) throws Exception {
        if (serieModelo == null) {
            throw new Exception("Debe indicar una serie");
        }

        try {

            em.getTransaction().begin();
            em.remove(serieModelo);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("No se ha podido modificar");
        }
        return true;
    }

    public List<SerieModelo> listarSeries() {

        return em.createQuery("SELECT s "
                + "FROM SerieModelo s").getResultList();
    }

    public SerieModelo buscarPorId(Long id) {
        return (SerieModelo) em.createQuery("SELECT s "
                + "FROM SerieModelo s"
                + " WHERE s.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public void modificar(SerieModelo serieModelo) throws Exception {

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
        }
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

    /*public List<SerieModelo> consultar(String titulo, Genero genero, Integer estrellas,
            boolean atp, boolean menorCien, boolean menorQuinientos,
            boolean menorMil, boolean mayorMil) {

        String sql = "SELECT s "
                + "FROM SerieModelo s "
                + "WHERE upper(s.titulo) "
                + "LIKE upper('%:titulo%') ";

        if (atp) {
            sql = sql + ("AND s.atp = true ");
        }
        if (genero != null) {
            sql = sql + ("AND s.genero = :genero ");
        }
        if (estrellas != null) {
            sql = sql + ("AND s.estrellas = :estrellas ");
        }
        if (menorCien) {
            sql = sql + ("AND s.precioAlquiler <= 100 ");
        } else if (menorQuinientos) {
            sql = sql + ("AND s.precioAlquiler <= 500 ");
        } else if (menorMil) {
            sql = sql + ("AND s.precioAlquiler <= 1000 ");
        } else if (mayorMil) {
            sql = sql + ("AND s.precioAlquiler > 1000 ");
        }
        
        return em.createQuery(sql)
                .setParameter("titulo", titulo)
                .setParameter("genero", genero)
                .setParameter("estrellas", estrellas)
                .getResultList();
    }*/
}
