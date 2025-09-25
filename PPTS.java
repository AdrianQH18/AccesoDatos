package Trimestre1;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PPTS {
	
	public static void main(String[] args) {
//////////////////////////////////////////////////
		Properties props = new Properties();

		// Guardo propiedades clave:valor en el objeto Properties
		props.setProperty("nombre", "Adrian");
		props.setProperty("email", "Adrian@gmail.com");
		props.setProperty("idioma", "español");
        
		
		////////////////////////////////////////////////////
		// aqui guardo las propiedades en un archivo en formato texto properties
        try (FileOutputStream fos = new FileOutputStream("config.properties")) {
            props.store(fos, "Configuración de ejemplo"); // Almaceno propiedades en archivo config.properties con un comentario
        } catch (IOException e) {
            System.out.println("Error al guardar en .properties: " + e.getMessage());
        }

////////////////////////////////////////////////////
		// Guardo las mismas propiedades en archivo en formato XML
        try (FileOutputStream fosXml = new FileOutputStream("config.xml")) {
            props.storeToXML(fosXml, "Configuración en XML"); // Guardo las propiedades en formato XML con un comentario
        } catch (IOException e) {
            System.out.println("Error al guardar en XML: " + e.getMessage());
        }
        
        /////////////////////////////////////////////////////////////////////
        Properties propsLeidos = new Properties();//Nuevo objeto Properties para lectura

        System.out.println("Contenido cargado desde config.properties:");
		// Lee el contenido del archivo properties y lo muestra
        try (FileInputStream fis = new FileInputStream("config.properties")) {
			// Cargar las propiedades del archivo config.properties
            propsLeidos.load(fis);
            for (String key : propsLeidos.stringPropertyNames()) {
                System.out.println(key + " = " + propsLeidos.getProperty(key));
            }
        } catch (IOException e) {
            System.out.println("Error leyendo config.properties: " + e.getMessage());
        }

        System.out.println("\nContenido cargado desde config.xml:");
        try (FileInputStream fisXml = new FileInputStream("config.xml")) {
            propsLeidos.loadFromXML(fisXml);
            for (String key : propsLeidos.stringPropertyNames()) {
                System.out.println(key + " = " + propsLeidos.getProperty(key));
            }
        } catch (IOException e) {
            System.out.println("Error leyendo config.xml: " + e.getMessage());
        }
        
	}
	
}

