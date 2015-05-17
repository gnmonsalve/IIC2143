package Model;

import java.io.Serializable;
import java.util.Calendar;


public class Tarea implements Serializable, Comparable<Tarea> {
	
	//public enum estado implements Serializable{activo, pausado, terminado}
	private int id;
	private String nombre;
	private Fecha fi;
	private Fecha ff;
	private Hora hi;
	private Hora hf;
	private String descripcion;
	private Estado estado;
	private int color;
	private Contexto contexto;

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

	public Fecha getFi() {
		return fi;
	}

	public void setFi(Fecha fi) {
		this.fi = fi;
	}

	public Fecha getFf() {
		return ff;
	}

	public void setFf(Fecha ff) {
		this.ff = ff;
	}

	public Hora getHi() {
		return hi;
	}

	public void setHi(Hora hi) {
		this.hi = hi;
	}

	public Hora getHf() {
		return hf;
	}

	public void setHf(Hora hf) {
		this.hf = hf;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void siguienteEstado() {
		if (estado.equals(Estado.activo)) {
			estado = Estado.pausado;
		} else if (estado.equals(Estado.pausado)) {
			estado = Estado.terminado;
		} else if (estado.equals(Estado.terminado)) {
			estado = Estado.activo;
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}


	
	//Creación: se crea completa o vacía y se modifican los datos individualmente con los setters

	public Tarea(int id, String nombre, Fecha fi, Fecha ff, Hora hi, Hora hf, String descripcion, int color, Contexto contexto)
	{
		this.id = id;
		this.nombre = nombre;
		this.fi = fi;
		this.ff = ff;
		this.hi = hi;
		this.hf = hf;
		this.descripcion = descripcion;
		this.estado = estado.activo;
		this.color = color;
		this.contexto = contexto;
	}
	
	public Tarea(int id)
	{
		this.id = id;
		this.nombre = "Tarea"+Integer.toString(id);
		this.fi = null;
		this.ff = null;
		this.hi = null;
		this.hf = null;
		this.descripcion = null;
		this.estado = estado.pausado;
		this.color = 0;
		this.contexto = null;
	}

	public Tarea(int id, String nombre)
	{
		this.id = id;
		this.nombre = nombre;
		this.fi = null;
		this.ff = null;
		this.hi = null;
		this.hf = null;
		this.descripcion = null;
		this.estado = estado.pausado;
		this.color = 0;
		this.contexto = null;
	}

	
	public Tarea(Tarea t)
	{
		this.nombre = t.getNombre();
		this.fi = t.getFi();
		this.ff = t.getFf();
		this.hi = t.getHi();
		this.hf = t.getHf();
		this.descripcion = t.getDescripcion();
		this.estado = t.getEstado();
		this.color = t.getColor();
		this.contexto = t.getContexto();
	}

	@Override
	public int compareTo(Tarea o) {
		/** debe ser una relacion siemtrica, reflexiva y transitiva **/
		Calendar calendario1 = getFf().getCalendario();
		Calendar calendario2 = o.getFf().getCalendario();
		/** ajustar a la hora final **/
		calendario1.set(Calendar.SECOND,getHf().getSegundos());
		calendario2.set(Calendar.SECOND,o.getHf().getSegundos());

		/** hasta el momento solo ordena por dia del año **/
		int i = calendario1.get(Calendar.DAY_OF_YEAR);
		int j = calendario2.get(Calendar.DAY_OF_YEAR);
		return i-j;
	}
}
