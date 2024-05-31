package apidiasfestivos.apidiasfestivos.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;
import apidiasfestivos.apidiasfestivos.core.interfaces.servicios.IFestivoServicio;


@RestController
@RequestMapping("api/festivos")
public class FestivoControlador {
    
    private IFestivoServicio servicio;

    public FestivoControlador(IFestivoServicio servicio) {
        this.servicio = servicio;
    }

    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public List<Festivo> listar(){
        return servicio.listar();
    }

    @RequestMapping(value = "/obtener/{dia}/{mes}", method = RequestMethod.GET)
    public Festivo obtener(@PathVariable int dia, @PathVariable int mes) {
        return servicio.obtener(dia, mes);
    }

    
    /* @RequestMapping(value = "/esFestivo/{dia}/{mes}", method = RequestMethod.GET)
    public boolean esFestivo(@PathVariable int dia, @PathVariable int mes) {
        return servicio.esFestivo(dia, mes);
    } */

    @RequestMapping(value = "/esFestivo/{dia}/{mes}", method = RequestMethod.GET)
public boolean esFestivo(@PathVariable int dia, @PathVariable int mes, @RequestParam int ano) {
    return servicio.esFestivo(dia, mes, ano);
}
} 


