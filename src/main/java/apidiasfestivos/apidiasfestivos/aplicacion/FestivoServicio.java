package apidiasfestivos.apidiasfestivos.aplicacion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;
import apidiasfestivos.apidiasfestivos.core.interfaces.repositorios.IFestivoRepositorio;
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

    public static Date getDomingoRamos(int año){
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19*a+24) % 30;
        int dias = d + (2*b+4*c+6*d+5) % 7;

        int dia = 15+dias;
        int mes = 3;

        if(dia>31){
            dias=dia-31;
            mes=4;
        }
        return new Date(año-1900, mes-1, dia);
    }

    public static Date agregarDias(Date fecha, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, dias);

        return calendario.getTime();
    }

    public static Date siguienteLunes(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        if(calendario.get(Calendar.DAY_OF_WEEK)>Calendar.MONDAY){          
            fecha = agregarDias(fecha, 9-calendario.get(Calendar.DAY_OF_WEEK));
        } else {
            fecha = agregarDias(fecha, 1);
        }
        System.out.println(9-calendario.get(Calendar.DAY_OF_WEEK));
        return fecha;
    }

    @Override
    public Festivo obtener(int dia, int mes) {  
        return repositorio.obtener(dia, mes);
        
    }


    public Boolean esFestivo(int dia, int mes, int ano) {
        Festivo festivo = repositorio.obtener(dia, mes);
        
        if (festivo != null && festivo.getIdtipo().getId() == 1) {
            return true; // Es un festivo fijo
        } else {
            // Verificar si hay algún festivo tipo 2 que se traslade al lunes
            List<Festivo> festivos = repositorio.findAll(); // Método para obtener todos los festivos
            for (Festivo f : festivos) {
                if (f.getIdtipo().getId() == 2) {
                    Date fechaFestivo = createDate(f.getDia(), f.getMes(), ano);
                    Date siguienteLunes = siguienteLunes(fechaFestivo);
    
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(siguienteLunes);
                    int diaLunes = calendario.get(Calendar.DAY_OF_MONTH);
                    int mesLunes = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
                    if (diaLunes == dia && mesLunes == mes) {
                        return true;
                    }
                } else if (f.getIdtipo().getId() == 3) {
                    Date domingoPascua = agregarDias(getDomingoRamos(ano), 7);
                    Date fechaFestivo = agregarDias(domingoPascua, f.getDiasPascua());
    
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(fechaFestivo);
                    int diaFestivo = calendario.get(Calendar.DAY_OF_MONTH);
                    int mesFestivo = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
                    if (diaFestivo == dia && mesFestivo == mes) {
                        return true;
                    }
                } else if (f.getIdtipo().getId() == 4) {
                    Date domingoPascua = agregarDias(getDomingoRamos(ano), 7);
                    Date fechaFestivo = siguienteLunes(agregarDias(domingoPascua, f.getDiasPascua()));
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(fechaFestivo);
                    int diaFestivo = calendario.get(Calendar.DAY_OF_MONTH);
                    int mesFestivo = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
                    if (diaFestivo == dia && mesFestivo == mes) {
                        return true;
                    } 
                }
            }
        }
        
        return false; // No es festivo
    }




    //
    //metodo es festivo original sin pedir el año
    //
    /* @Override
    public Boolean esFestivo(int dia, int mes) {
        //validarFecha(dia, mes); // Validar la fecha antes de continuar
        Festivo festivo = repositorio.obtener(dia, mes);
    
        if (festivo != null && festivo.getIdtipo().getId() == 1) {
            return true; // Es un festivo fijo
        } else {
            // Verificar si hay algún festivo tipo 2 que se traslade al lunes
            List<Festivo> festivos = repositorio.findAll(); // Método para obtener todos los festivos
            for (Festivo f : festivos) {
                if (f.getIdtipo().getId() == 2) {
                    
                    Date fechaFestivo = createDate(f.getDia(), f.getMes());
                    Date siguienteLunes = siguienteLunes(fechaFestivo);

                    // Crear una instancia de Calendar para obtener el año actual
                    Calendar calendario = Calendar.getInstance();
                    calendario.setTime(siguienteLunes);
                    int diaLunes = calendario.get(Calendar.DAY_OF_MONTH);
                    int mesLunes = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
                    if (diaLunes == dia && mesLunes == mes) {
                        return true;
                    }
                } else if (f.getIdtipo().getId() == 3) {

                    // Crear una instancia de Calendar para obtener el año actual
                    Calendar calendario = Calendar.getInstance();
                    int año = calendario.get(Calendar.YEAR);
                    Date domingoPascua = agregarDias(getDomingoRamos(año), 7);
                    Date fechaFestivo = agregarDias(domingoPascua, f.getDiasPascua());

                    calendario.setTime(fechaFestivo);
                    int diaFestivo = calendario.get(Calendar.DAY_OF_MONTH);
                    int mesFestivo = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
                    if (diaFestivo == dia && mesFestivo == mes) {
                        return true;
                    }
                } else if (f.getIdtipo().getId() == 4) {

                    // Crear una instancia de Calendar para obtener el año actual
                    Calendar calendario = Calendar.getInstance();
                    int año = calendario.get(Calendar.YEAR);
                    Date domingoPascua = agregarDias(getDomingoRamos(año), 7);
                    Date fechaFestivo = siguienteLunes(agregarDias(domingoPascua, f.getDiasPascua()));
                    calendario.setTime(fechaFestivo);
                    int diaFestivo = calendario.get(Calendar.DAY_OF_MONTH);
                    int mesFestivo = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
                    if (diaFestivo == dia && mesFestivo == mes) {
                        return true;
                    } 
                }
            }
        }
    
    return false; // No es festivo
    } */


    public Date createDate(int dia, int mes, int ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, ano);
        calendario.set(Calendar.MONTH, mes - 1); // Restar 1 porque los meses son base 0 en Calendar
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario.getTime();
    }


    //
    //metodo crear date original sin pedir el año
    //
    /* public Date createDate(int dia, int mes) {
        // Crear una instancia de Calendar para obtener el año actual
        Calendar calendario = Calendar.getInstance();

        // Establecer la fecha según los parámetros
        calendario.set(Calendar.MONTH, mes - 1); // Restar 1 porque los meses son base 0 en Calendar
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        
        // Obtener un objeto Date a partir del Calendar
        return calendario.getTime();
    } */

    //
    //intento fallido de try catch para fechas no validas
    //
    /* public static void validarFecha(int dia, int mes) throws FechaNoValidaExcepcion {
        if (mes < 1 || mes > 12) {
            throw new FechaNoValidaExcepcion("Fecha no válida");
        }

        Calendar calendario = Calendar.getInstance();
        calendario.setLenient(false); // Establece el modo estricto de validación de fechas
        try {
            calendario.set(Calendar.MONTH, mes - 1); // Restar 1 porque los meses son base 0 en Calendar
            calendario.set(Calendar.DAY_OF_MONTH, dia);
            calendario.getTime(); // Intentar obtener la fecha para activar la validación
        } catch (Exception e) {
            throw new FechaNoValidaExcepcion("Fecha no válida");
        }
    } */


} 

   