package Trimestre1;
import java.io.*;
import java.util.Scanner;

public class PruebaRandomAccessFile {
    static final int TAMANIO = 20;// Define el tamaño del array
    static final String FICHERO = "datos.bin";

    public static void main(String[] args) {
        int[] datos = new int[TAMANIO];


        File file = new File(FICHERO);
        
        if (file.exists()) {
            // leemos los datos enteros almacenados secuencialmente solo si el fichero existe
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                for (int i = 0; i < TAMANIO; i++) {
                    datos[i] = dis.readInt(); //Lee entero a entero
                }
            } catch (IOException e) {
                System.out.println("Error leyendo el fichero: " + e.getMessage());
            }
        } else {
            // Si no existe el fichero se crea con 20 enteros
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                for (int i = 0; i < TAMANIO; i++) {
                    dos.writeInt(0);
                    datos[i] = 0;
                }
            } catch (IOException e) {
                System.out.println("Error creando y escribiendo fichero: " + e.getMessage());
            }
        }
        // Abrimos el fichero para lectura y escritura aleatoria
        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw");
             Scanner sc = new Scanner(System.in)) {
            // Muestra el contenido actual
            while (true) {
                System.out.println("Contenido actual:");
                for (int i = 0; i < TAMANIO; i++) {
                    System.out.print(datos[i] + " ");
                }
                System.out.println("\nIntroduce posición a modificar (negativo para salir): ");
                int pos = sc.nextInt();
                if (pos < 0) break;// si la posicion es negativa sale
                if (pos >= TAMANIO) {
                    System.out.println("Posición inválida.");
                    continue;// Pide otra posición válida
                }
                System.out.println("Introduce nuevo valor: ");
                int valor = sc.nextInt();

                // Actualiza el array
                datos[pos] = valor;

                // Posiciona el cursor en el fichero en byte (4 bytes por int)
                raf.seek(pos * 4L);
                // Escribe el nuevo valor en la posición indicada
                raf.writeInt(valor);
            }

        } catch (IOException e) {
            System.out.println("Error con RandomAccessFile: " + e.getMessage());
        }
    }

}
