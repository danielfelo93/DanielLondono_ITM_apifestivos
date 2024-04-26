package apidiasfestivos.apidiasfestivos.core.interfaces.servicios;

import java.util.List;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;

public interface IFestivoServicio {
    
    public List<Festivo> listar();

    public Festivo verificar(Long id);

}
