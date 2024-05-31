package apidiasfestivos.apidiasfestivos.core.interfaces.servicios;
//import java.util.Optional;
import java.util.List;

//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import org.springframework.data.jpa.repository.Query;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;

public interface IFestivoServicio {

    public List<Festivo> listar();

    public Festivo obtener(int dia, int mes);

    /* public Boolean esFestivo(int dia, int mes); */

    public Boolean esFestivo(int dia, int mes, int ano);
    
}

