package administradordeseries.database;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionDataBase {

    EntityManagerFactory managerFactory = null;
    Map<String, String> persistenceMap = new HashMap<String, String>();

    public boolean conectar(String servidor, String puerto, String db, String user, String password) {

        try {
            String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + db + "?zeroDateTimeBehavior=convertToNull";

            persistenceMap.put("javax.persistence.jdbc.url", url);
            persistenceMap.put("javax.persistence.jdbc.user", user);
            persistenceMap.put("javax.persistence.jdbc.password", password);
            persistenceMap.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");

            managerFactory = Persistence.createEntityManagerFactory("AdministradorDeSeriesPU", persistenceMap);
            EntityManager manager = managerFactory.createEntityManager();

            System.out.println("Conexion exitosa");
            
            return true;
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            return false;
        }

    }

}
