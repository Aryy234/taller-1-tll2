package efectos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Imagen {

    public static void main(String[] args) {
        // Asegurarse de que exista la carpeta de imágenes o crearla si es necesario
        File dir = new File("src/imagenes");
        if (!dir.exists()) dir.mkdirs();

        int ancho = 800;
        int alto = 600;
        
        // Definir colores, como el morado oscuro solicitado por el usuario, degradando hacia un color más claro.
        Color moradoOscuro = new Color(48, 0, 81);
        Color moradoClaro = new Color(155, 89, 182);

        try {
            System.out.println("Generando imágenes puras de tamaño: " + ancho + "x" + alto);

            // 1. Izquierda a Derecha
            BufferedImage imgDer = GeneradorDegradados.izquierdaADerecha(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgDer, "jpg", new File("src/imagenes/1_izq_a_der.jpg"));

            // 2. Derecha a Izquierda
            BufferedImage imgIzq = GeneradorDegradados.derechaAIzquierda(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgIzq, "jpg", new File("src/imagenes/2_der_a_izq.jpg"));

            // 3. Arriba a Abajo
            BufferedImage imgAba = GeneradorDegradados.arribaAAbajo(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgAba, "jpg", new File("src/imagenes/3_arr_a_aba.jpg"));

            // 4. Abajo a Arriba
            BufferedImage imgArr = GeneradorDegradados.abajoAArriba(ancho, alto, moradoOscuro, moradoClaro);
            ImageIO.write(imgArr, "jpg", new File("src/imagenes/4_aba_a_arr.jpg"));

            // 5. Centro a Esquinas (Radial)
            BufferedImage imgRadial = GeneradorDegradados.centroAEsquinas(ancho, alto, moradoClaro, moradoOscuro);
            ImageIO.write(imgRadial, "jpg", new File("src/imagenes/5_centro_a_esquinas.jpg"));

            System.out.println("Las 5 imágenes con efectos de degradado en color morado se han generado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al escribir imagen: " + e.getMessage());
        }
    }
}