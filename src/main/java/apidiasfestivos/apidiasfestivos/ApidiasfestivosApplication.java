package apidiasfestivos.apidiasfestivos;

//import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import apidiasfestivos.apidiasfestivos.aplicacion.FestivoServicio;

@SpringBootApplication
public class ApidiasfestivosApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApidiasfestivosApplication.class, args);

		/* Date domingoRamos = FestivoServicio.getDomingoRamos(año);
		Date domingoPascua = FestivoServicio.agregarDias(domingoRamos, 7);
		Date juevesSanto = FestivoServicio.agregarDias(domingoPascua,-3);
		Date viernesSanto = FestivoServicio.agregarDias(domingoPascua,-2);
		Date ascencionSeñor = FestivoServicio.siguienteLunes(FestivoServicio.agregarDias(domingoPascua, 40));
		Date corpusChristi = FestivoServicio.siguienteLunes(FestivoServicio.agregarDias(domingoPascua, 61));
		Date sagradoCorazon = FestivoServicio.siguienteLunes(FestivoServicio.agregarDias(domingoPascua, 68)); */

	}

}
