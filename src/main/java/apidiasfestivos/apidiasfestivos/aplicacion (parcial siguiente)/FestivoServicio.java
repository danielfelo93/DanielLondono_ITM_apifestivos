/* 

package apidiasfestivos.apidiasfestivos.aplicacion

import java.util.List;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;
import apidiasfestivos.apidiasfestivos.core.interfaces.servicios.IFestivoServicio;

@Service
public class FestivoServicio implements IFestivoServicio{

    private IFestivoRepositorio repositorio;

    public FestivoServicio(IFestivoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Festivo> listar() {
        return repositorio.findAll();
    }

    @Override
    public Festivo verificar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verificar'");
    }
    
} 

*/
