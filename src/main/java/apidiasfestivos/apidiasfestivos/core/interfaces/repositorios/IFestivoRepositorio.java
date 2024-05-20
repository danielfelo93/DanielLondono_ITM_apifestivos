package apidiasfestivos.apidiasfestivos.core.interfaces.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;

@Repository
public interface IFestivoRepositorio extends JpaRepository<Festivo, Integer>{

    @Query("SELECT s FROM Festivo s WHERE s.dia = :dia AND s.mes = :mes")
    //public List<Festivo> obtener(String nombre);
    Festivo obtener(int dia, int mes);

}
