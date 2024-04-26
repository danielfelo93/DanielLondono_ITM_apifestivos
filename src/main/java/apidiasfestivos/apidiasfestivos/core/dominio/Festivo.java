package apidiasfestivos.apidiasfestivos.core.dominio;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="festivo")
public class Festivo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivo")
    @GenericGenerator(name = "secuencia_festivo", strategy = "increment")
    @Column(name = "id")
    private int id;
    
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "día")
    private int dia;

    @Column(name = "mes")
    private int mes;

    @Column(name = "días de pascua")
    private int diasPascua;

    //elegimos la cardinalidad, en este caso muchos tipos de festivo para un dia festivo, pues se puede dar la situacion
    @ManyToOne
    //usamos joinColumn por ser llave foranea de la tabla tipo
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    private Tipo idtipo;

    public Festivo() {
    }
    
    public Festivo(int id, String nombre, int dia, int mes, int diasPascua, Tipo idtipo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.idtipo = idtipo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

    public Tipo getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Tipo idtipo) {
        this.idtipo = idtipo;
    }

    
    
    


}
