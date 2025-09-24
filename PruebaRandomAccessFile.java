package Trimestre1;
import java.io.*;
import java.util.Scanner;

public class PruebaRandomAccessFile {
    static final int TAMANIO = 20;
    static final String FICHERO = "datos.bin";

    public static void main(String[] args) {
        int[] datos = new int[TAMANIO];


        File file = new File(FICHERO);
        if (file.exists()) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                for (int i = 0; i < TAMANIO; i++) {
                    datos[i] = dis.readInt();
                }
            } catch (IOException e) {
                System.out.println("Error leyendo el fichero: " + e.getMessage());
            }
        } else {

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
                for (int i = 0; i < TAMANIO; i++) {
                    dos.writeInt(0);
                    datos[i] = 0;
                }
            } catch (IOException e) {
                System.out.println("Error creando y escribiendo fichero: " + e.getMessage());
            }
        }

        try (RandomAccessFile raf = new RandomAccessFile(FICHERO, "rw");
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("Contenido actual:");
                for (int i = 0; i < TAMANIO; i++) {
                    System.out.print(datos[i] + " ");
                }
                System.out.println("\nIntroduce posición a modificar (negativo para salir): ");
                int pos = sc.nextInt();
                if (pos < 0) break;
                if (pos >= TAMANIO) {
                    System.out.println("Posición inválida.");
                    continue;
                }
                System.out.println("Introduce nuevo valor: ");
                int valor = sc.nextInt();


                datos[pos] = valor;

                raf.seek(pos * 4L);
                raf.writeInt(valor);
            }

        } catch (IOException e) {
            System.out.println("Error con RandomAccessFile: " + e.getMessage());
        }
    }
}