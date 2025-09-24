package Trimestre1;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ModificarMBMP {

    public static void main(String[] args) {
        String ficheroBmp = "\"C:/ruta/completa/a/imagen.bmp\"";

        try (RandomAccessFile raf = new RandomAccessFile(ficheroBmp, "rw")) {

            raf.seek(18);
            int ancho = Integer.reverseBytes(raf.readInt());
            System.out.println("Ancho original: " + ancho);


            raf.seek(22);
            int alto = Integer.reverseBytes(raf.readInt());
            System.out.println("Alto original: " + alto);


            int nuevoAncho = ancho / 2;
            int nuevoAlto = alto / 2;


            raf.seek(18);
            raf.writeInt(Integer.reverseBytes(nuevoAncho));


            raf.seek(22);
            raf.writeInt(Integer.reverseBytes(nuevoAlto));

            System.out.println("Modificado ancho a " + nuevoAncho + " y alto a " + nuevoAlto);

        }catch (IOException e) {
            System.out.println("Error manipulando el fichero BMP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
