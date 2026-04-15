package efectos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Clase principal que actúa como punto de entrada de la aplicación.
 * Orquesta la creación de los distintos efectos de degradado y
 * los almacena como archivos de imagen en el disco.
 */
public class Imagen {

    public static void main(String[] args) {
        // Asegurarse de que exista el directorio contenedor de las imágenes o crearla si es necesario
        File dir = new File("src/imagenes");
        if (!dir.exists()) dir.mkdirs();

        // Resolución predeterminada de las imágenes a generar
        int ancho = 800;
        int alto = 600;
        
        // Definir la paleta de colores. En este caso un morado oscuro degradando hacia un color más claro.
        Color moradoOscuro = new Color(48, 0, 81);
        Color moradoClaro = new Color(155, 89, 182);

        try {
            System.out.println("Generando imágenes puras de tamaño: " + ancho + "x" + alto);

            // 1. Degradado lineal: Izquierda a Derecha. Color inicial en la izquierda.
            BufferedImage imgDer = GeneradorDegradados.izquierdaADerecha(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgDer, "jpg", new File("src/imagenes/1_izq_a_der.jpg"));

            // 2. Degradado lineal: Derecha a Izquierda. Color inicial en la derecha.
            BufferedImage imgIzq = GeneradorDegradados.derechaAIzquierda(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgIzq, "jpg", new File("src/imagenes/2_der_a_izq.jpg"));

            // 3. Degradado lineal: Arriba a Abajo. Color inicial arriba.
            BufferedImage imgAba = GeneradorDegradados.arribaAAbajo(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgAba, "jpg", new File("src/imagenes/3_arr_a_aba.jpg"));

            // 4. Degradado lineal: Abajo a Arriba. Color inicial abajo.
            BufferedImage imgArr = GeneradorDegradados.abajoAArriba(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgArr, "jpg", new File("src/imagenes/4_aba_a_arr.jpg"));

            // 5. Degradado radial: Centro a Esquinas. Color oscuro como borde exterior.
            BufferedImage imgRadial = GeneradorDegradados.centroAEsquinas(ancho, alto, moradoClaro, moradoOscuro);
            ImageIO.write(imgRadial, "jpg", new File("src/imagenes/5_centro_a_esquinas.jpg"));

            System.out.println("Las 5 imágenes con efectos de degradado en color morado se han generado correctamente.");

        } catch (Exception e) {
            // Manejo de errores por problemas de escritura en disco
            System.out.println("Error al escribir imagen: " + e.getMessage());
        }
    }
}