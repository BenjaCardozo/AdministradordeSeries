package administradordeseries.controlador;

import administradordeseries.modelo.SerieModelo;
import administradordeseries.dao.SerieDAO;
import administradordeseries.enums.Genero;
import java.util.List;

public class SerieControlador {
    
    private SerieDAO serieRepository;
    
    public SerieControlador() {
        
        this.serieRepository = new SerieDAO();
    }
    
    public boolean registrar(SerieModelo serieModelo) throws Exception {
        return this.serieRepository.registrar(serieModelo);
    }
    
    public void modificar(SerieModelo serieModelo) throws Exception {
        this.serieRepository.modificar(serieModelo);
    }
    
    public boolean eliminar(Long id) throws Exception {
        return this.serieRepository.eliminarSerie(id);
    }
    
    public boolean anular(Long id) throws Exception {
        return this.serieRepository.anularSerie(id);
    }

    public List<SerieModelo> listar() {
        return this.serieRepository.listarSeries();
    }
    
   /* public List<SerieModelo> consultar(String titulo, Genero genero, Integer estrellas,
            boolean atp, boolean menorCien, boolean menorQuinientos,
            boolean menorMil, boolean mayorMil){
        return this.serieRepository.consultar(titulo, genero, estrellas, atp, menorCien, menorQuinientos, menorMil, mayorMil);
    }*/
}
