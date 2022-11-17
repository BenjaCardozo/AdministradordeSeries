package administradordeseries.controlador;

import administradordeseries.modelo.SerieModelo;
import administradordeseries.enums.Genero;
import administradordeseries.servicio.SerieServicio;
import java.util.List;

public class SerieControlador {
    
    private SerieServicio serieServicio;
    
    public SerieControlador() {
        
        this.serieServicio = new SerieServicio();
    }
    
    public boolean registrar(SerieModelo serieModelo) throws Exception {
        return this.serieServicio.registrarSerie(serieModelo);
    }
    
    public boolean modificar(SerieModelo serieModelo) throws Exception {
        return this.serieServicio.modificar(serieModelo);
    }
    
    public boolean eliminar(Long id) throws Exception {
        return this.serieServicio.eliminarSerie(id);
    }
    
    public boolean anular(Long id) throws Exception {
        return this.serieServicio.anularSerie(id);
    }

    public List<SerieModelo> listar() {
        return this.serieServicio.listarSeries();
    }
    
   public List<SerieModelo> consultar(String titulo, Genero genero, Integer estrellas,
            boolean atp, boolean menorCien, boolean menorQuinientos,
            boolean menorMil, boolean mayorMil){
        return this.serieServicio.consultar(titulo, genero, estrellas, atp, menorCien, menorQuinientos, menorMil, mayorMil);
    }
}
