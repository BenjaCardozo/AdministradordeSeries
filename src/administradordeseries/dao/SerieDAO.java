package administradordeseries.dao;

import administradordeseries.modelo.SerieModelo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SerieDAO {

    protected SerieModelo serieModelo;

    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("AdministradorDeSeriesPU");

    protected EntityManager em = emf.createEntityManager();

    protected boolean registrar(SerieModelo serieModelo) throws Exception {

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

    protected boolean modificarDB(SerieModelo serieModelo) throws Exception {
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

    protected boolean eliminar(SerieModelo serieModelo) throws Exception {
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

}
