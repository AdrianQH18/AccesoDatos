package Trimestre1;
import java.util.Properties;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PPTS {
	
	public static void main(String[] args) {
//////////////////////////////////////////////////
		Properties props = new Properties();
		props.setProperty("nombre", "Adrian");
		props.setProperty("email", "Adrian@gmail.com");
		props.setProperty("idioma", "español");
        
		
		////////////////////////////////////////////////////
        try (FileOutputStream fos = new FileOutputStream("config.properties")) {
            props.store(fos, "Configuración de ejemplo");
        } catch (IOException e) {
            System.out.println("Error al guardar en .properties: " + e.getMessage());
        }

////////////////////////////////////////////////////
        try (FileOutputStream fosXml = new FileOutputStream("config.xml")) {
            props.storeToXML(fosXml, "Configuración en XML");
        } catch (IOException e) {
            System.out.println("Error al guardar en XML: " + e.getMessage());
        }
        
        /////////////////////////////////////////////////////////////////////
        Properties propsLeidos = new Properties();

        System.out.println("Contenido cargado desde config.properties:");
        try (FileInputStream fis = new FileInputStream("config.properties")) {
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
