package administradordeseries.dao;

import administradordeseries.enums.Genero;
import administradordeseries.modelo.SerieModelo;
import static administradordeseries.modelo.SerieModelo_.estrellas;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    /*public List<SerieModelo> consultar(String titulo, Genero genero, Integer estrellas,
            boolean atp, boolean menorCien, boolean menorQuinientos,
            boolean menorMil, boolean mayorMil) {

        String sql = "SELECT s "
                + "FROM SerieModelo s "
                + "WHERE s.titulo "
                + " LIKE '%' :titulo ";

        if (atp) {
            sql = sql + " AND s.atp = true ";
        }
        if (genero != null) {
            sql = sql + "AND s.genero = :genero ";
        }
        if (estrellas != null) {
            sql = sql + "AND s.estrellas = :estrellas ";
        }
        if (menorCien) {
            sql = sql + " AND s.precioAlquiler <= 100 ";
        } else if (menorQuinientos) {
            sql = sql + " AND s.precioAlquiler <= 500 ";
        } else if (menorMil) {
            sql = sql + " AND s.precioAlquiler <= 1000 ";
        } else if (mayorMil) {
            sql = sql + " AND s.precioAlquiler > 1000 ";
        }
        System.out.println(sql);
        return (List<SerieModelo>) em.createQuery(sql)
                .setParameter("titulo", titulo)
                .setParameter("genero", genero)
                .setParameter("estrellas", estrellas)
                .getResultList();
    }*/
}
