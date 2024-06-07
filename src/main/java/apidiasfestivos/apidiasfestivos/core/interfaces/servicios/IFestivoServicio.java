package apidiasfestivos.apidiasfestivos.core.interfaces.servicios;
import java.util.List;

//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

//import org.springframework.data.jpa.repository.Query;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;
import apidiasfestivos.apidiasfestivos.core.dominio.FestivoCalculado;

public interface IFestivoServicio {

    public List<Festivo> listar();

    public List<FestivoCalculado> listarAno(int ano);

    public Festivo obtener(int dia, int mes);/*  */

    /* public Boolean esFestivo(int dia, int mes); */

    public Boolean esFestivo(int dia, int mes, int ano);
    
}

