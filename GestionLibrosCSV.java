package Trimestre1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionLibrosCSV {

    static class Libro {
        private String titulo;
        private String autor;
        private int anio;

        public Libro(String titulo, String autor, int anio) {
            this.titulo = titulo;
            this.autor = autor;
            this.anio = anio;
        }

        public String getTitulo() { return titulo; }
        public String getAutor() { return autor; }
        public int getAnio() { return anio; }

        public String toString() {
            return titulo + " | " + autor + " | " + anio;
        }
    }

 // Método que guarda una lista de libros en un fichero CSV
    public static void guardarLibrosCSV(List<Libro> libros, String fichero) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fichero), "UTF-8"))) {
            pw.println("titulo;autor;anio"); // Encabezado con nombres campos separados por ;
            for (Libro libro : libros) {
                String titulo = escaparCSV(libro.getTitulo());
                String autor = escaparCSV(libro.getAutor());
                pw.println(titulo + ";" + autor + ";" + libro.getAnio());// Escribe cada libro en una línea con campos separados por punto y coma
            }
        } catch (IOException e) {
            System.out.println("Error guardando CSV: " + e.getMessage());
        }
    }

    //Método que lee y devuelve una lista de objetos libro
    public static List<Libro> leerLibrosCSV(String fichero) {
        List<Libro> libros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), "UTF-8"))) {
            String linea;
            br.readLine();// lee y descarta la línea de cabecera
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(";");// Divide lineas en campos por ;
                if (campos.length == 3) {
                    // Quita comillas dobles
                    String titulo = campos[0].replace("\"", "");
                    String autor = campos[1].replace("\"", "");
                    int anio = Integer.parseInt(campos[2]);
                    libros.add(new Libro(titulo, autor, anio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo CSV: " + e.getMessage());
        }
        return libros;
    }

// Método auxiliar que trata los caracteres especiales
    private static String escaparCSV(String campo) {
        if (campo.contains(";") || campo.contains(",") || campo.contains("\"")) {
            campo = campo.replace("\"", "\"\"");
            return "\"" + campo + "\"";
        } else {
            return campo;
        }
    }

    public static void main(String[] args) {
        List<Libro> libros = Arrays.asList(
            new Libro("Travesuras de la niña mala", "Mario Vargas Llosa", 2001),
            new Libro("Cien años de soledad", "Gabriel García Márquez", 1967),
            new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 1985)
        );

        String ficheroCSV = "libros.csv";//nombre del archivo csv

        guardarLibrosCSV(libros, ficheroCSV);

        List<Libro> librosLeidos = leerLibrosCSV(ficheroCSV);// Lee libros
        System.out.println("Libros leídos del CSV:");
        for (Libro libro : librosLeidos) {
            System.out.println(libro);//muestra los libros
        }
    }
}

