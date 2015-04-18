
public class tarea {
	
	public enum estado{activo, pausado, terminado}
	private String nombre;
	private fecha fi;
	private fecha ff;
	private hora hi;
	private hora hf;
	private String descripcion;
	private estado estado;
	private int color;
	
	/*posibilidades de creación de tareas:
	 -Con fecha final, nombre
	 -Con fecha final, nombre, descripción
	 */
	public tarea(String nombre, fecha ff, int color)
	{
		this.nombre = nombre;
		this.ff = ff;
		this.color = color;
		this.estado = estado.activo;
	}
	public tarea(String nombre, fecha ff, String descripcion)
	{
		this.nombre = nombre;
		this.ff = ff;
		this.color = color;
		this.descripcion = descripcion;
		this.estado = estado.activo;
	}	
}