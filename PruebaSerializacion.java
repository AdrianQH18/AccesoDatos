package Trimestre1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PruebaSerializacion {

    static class Libro implements Serializable {
        private static final long serialVersionUID = 1L;
        private String titulo;
        private String autor;
        private int anio;

        public Libro(String titulo, String autor, int anio) {
            this.titulo = titulo;
            this.autor = autor;
            this.anio = anio;
        }

        @Override
        public String toString() {
            return "Libro{titulo='" + titulo + "', autor='" + autor + "', anio=" + anio + '}';
        }
    }
//Método que guardar la lista de libros serializando y escribiendo en un fichero
    public static void guardarLibros(List<Libro> libros, String fichero) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero))) {
            oos.writeObject(libros); // Escribe el objeto de forma serializada en el archivo
            System.out.println("Lista de libros guardada en " + fichero);
        } catch (IOException e) {
            System.out.println("Error al guardar libros: " + e.getMessage());
        }
    }

  // Método que carga la lista deserializando desde el fichero
    public static List<Libro> cargarLibros(String fichero) {
        List<Libro> libros = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            // Lee la lista de libros deserializando el objeto
            libros = (List<Libro>) ois.readObject();
            System.out.println("Lista de libros cargada desde " + fichero);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar libros: " + e.getMessage());
        }
        return libros;
    }

    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro("Travesuras de la niña Mala", "Mario Vargas Llosa", 2001));
        libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", 1967));
        libros.add(new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 1985));

        String fichero = "libros.bin";

        guardarLibros(libros, fichero);


        List<Libro> librosRecuperados = cargarLibros(fichero);

        // Si la carga fue exitosa, mostrar los libros
        if (librosRecuperados != null) {
            for (Libro libro : librosRecuperados) {
                System.out.println(libro);
            }
        }
    }

}
