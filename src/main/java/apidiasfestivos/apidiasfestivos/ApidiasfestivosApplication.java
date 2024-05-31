package apidiasfestivos.apidiasfestivos;

//import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import apidiasfestivos.apidiasfestivos.aplicacion.FestivoServicio;

@SpringBootApplication
public class ApidiasfestivosApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApidiasfestivosApplication.class, args);

		//para preguntar en postmann es asi: http://localhost:8080/api/festivos/esFestivo/2/4?ano=2021
		//									 donde: servidor		direccion	metodo	 dia/mes parametro año asi: ?ano=

	}

}
