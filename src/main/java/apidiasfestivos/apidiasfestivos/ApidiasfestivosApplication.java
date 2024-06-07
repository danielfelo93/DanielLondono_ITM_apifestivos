package apidiasfestivos.apidiasfestivos;

//import org.apache.catalina.filters.CorsFilter;
//import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import apidiasfestivos.apidiasfestivos.aplicacion.FestivoServicio;

@SpringBootApplication
public class ApidiasfestivosApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApidiasfestivosApplication.class, args);

		//para preguntar en postmann es asi: http://localhost:8080/api/festivos/esFestivo/2/4?ano=2021
		//									 donde: servidor		direccion	metodo	 dia/mes parametro año asi: ?ano=
	}

	@Bean
	public CorsFilter corsFilter() {

		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");// Permitir solo solicitudes desde "http://localhost:4200"
		config.addAllowedMethod("*");// Permitir todos los métodos (GET,POST,PUT,DELETE, etc..)
		config.addAllowedHeader("*");// Permitir todos los encabezados

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
