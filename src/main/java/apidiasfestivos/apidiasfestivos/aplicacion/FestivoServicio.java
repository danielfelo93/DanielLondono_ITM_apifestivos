package apidiasfestivos.apidiasfestivos.aplicacion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import apidiasfestivos.apidiasfestivos.core.dominio.Festivo;
import apidiasfestivos.apidiasfestivos.core.dominio.FestivoCalculado;
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


    public Date createDate(int dia, int mes, int ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, ano);
        calendario.set(Calendar.MONTH, mes - 1); // Restar 1 porque los meses son base 0 en Calendar
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario.getTime();
    }


    //
    //metodo listar por año
    //
    public List<FestivoCalculado> listarAno(int ano) {
        List<FestivoCalculado> festivosAno = new ArrayList<>();
        List<Festivo> festivos = repositorio.findAll(); // Método para obtener todos los festivos
        
        for (Festivo f : festivos) {
            String nombreFestivo = f.getNombre(); // Nombre del festivo
            
            int diaCalculado = -1; // Día calculado
            int mesCalculado = -1; // Mes calculado
            
            if (f.getIdtipo().getId() == 1) {
                // Festivo fijo
                diaCalculado = f.getDia();
                mesCalculado = f.getMes();
            } else if (f.getIdtipo().getId() == 2) {
                // Festivo tipo 2 que se traslada al lunes
                Date fechaFestivo = createDate(f.getDia(), f.getMes(), ano);
                Date siguienteLunes = siguienteLunes(fechaFestivo);
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(siguienteLunes);
                diaCalculado = calendario.get(Calendar.DAY_OF_MONTH);
                mesCalculado = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
            } else if (f.getIdtipo().getId() == 3) {
                // Festivo tipo 3 basado en Pascua
                Date domingoPascua = agregarDias(getDomingoRamos(ano), 7);
                Date fechaFestivo = agregarDias(domingoPascua, f.getDiasPascua());
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(fechaFestivo);
                diaCalculado = calendario.get(Calendar.DAY_OF_MONTH);
                mesCalculado = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
            } else if (f.getIdtipo().getId() == 4) {
                // Festivo tipo 4 basado en Pascua que se traslada al lunes
                Date domingoPascua = agregarDias(getDomingoRamos(ano), 7);
                Date fechaFestivo = siguienteLunes(agregarDias(domingoPascua, f.getDiasPascua()));
                Calendar calendario = Calendar.getInstance();
                calendario.setTime(fechaFestivo);
                diaCalculado = calendario.get(Calendar.DAY_OF_MONTH);
                mesCalculado = calendario.get(Calendar.MONTH) + 1; // Mes en Calendar es 0-indexed
            }
            
            // Agregar la información del festivo a la lista
            festivosAno.add(new FestivoCalculado(nombreFestivo, diaCalculado, mesCalculado));
        }
        
        return festivosAno;
    }
} 

   