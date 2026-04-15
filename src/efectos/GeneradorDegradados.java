package efectos;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Clase encargada de generar diferentes tipos de degradados (gradientes) de color
 * para imágenes. Provee métodos estáticos para gradientes lineales y radiales.
 */
public class GeneradorDegradados {

    /**
     * Genera un degradado horizontal de izquierda a derecha.
     * @param ancho Ancho de la imagen en píxeles.
     * @param alto Alto de la imagen en píxeles.
     * @param colorInicio Color que inicia en el extremo izquierdo.
     * @param colorFin Color que termina en el extremo derecho.
     * @return BufferedImage con el efecto aplicado.
     */
    public static BufferedImage izquierdaADerecha(int ancho, int alto, Color colorInicio, Color colorFin) {
        // Se instancia un 'lienzo' en memoria capaz de almacenar cada punto de la imagen
        // bajo una codificación de color numérico donde cada píxel tiene R(rojo), G(verde) y B(azul).
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        // Recorrer todos los píxeles de la imagen iterando el plano coordenado (x, y)
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                // Calcular la proporción (radio) basada en la coordenada x
                float radio = (float) x / ancho;
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    /**
     * Genera un degradado horizontal de derecha a izquierda.
     * @param ancho Ancho de la imagen en píxeles.
     * @param alto Alto de la imagen en píxeles.
     * @param colorInicio Color que inicia en el extremo derecho.
     * @param colorFin Color que termina en el extremo izquierdo.
     * @return BufferedImage con el efecto aplicado.
     */
    public static BufferedImage derechaAIzquierda(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        // Recorrer todos los píxeles de la imagen
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                // Calcular la proporción inversa (radio) basada en la coordenada x
                float radio = 1.0f - ((float) x / ancho);
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    /**
     * Genera un degradado vertical de arriba hacia abajo.
     * @param ancho Ancho de la imagen en píxeles.
     * @param alto Alto de la imagen en píxeles.
     * @param colorInicio Color que inicia en el borde superior.
     * @param colorFin Color que termina en el borde inferior.
     * @return BufferedImage con el efecto aplicado.
     */
    public static BufferedImage arribaAAbajo(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        // Recorrer todos los píxeles de la imagen
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                // Calcular la proporción (radio) basada en la coordenada y
                float radio = (float) y / alto;
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    /**
     * Genera un degradado vertical de abajo hacia arriba.
     * @param ancho Ancho de la imagen en píxeles.
     * @param alto Alto de la imagen en píxeles.
     * @param colorInicio Color que inicia en el borde inferior.
     * @param colorFin Color que termina en el borde superior.
     * @return BufferedImage con el efecto aplicado.
     */
    public static BufferedImage abajoAArriba(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        // Recorrer todos los píxeles de la imagen
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                // Calcular la proporción inversa (radio) basada en la coordenada y
                float radio = 1.0f - ((float) y / alto);
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    /**
     * Genera un degradado radial, es decir, del centro de la imagen hacia los bordes (esquinas).
     * @param ancho Ancho de la imagen en píxeles.
     * @param alto Alto de la imagen en píxeles.
     * @param colorCentro Color que inicia en el centro.
     * @param colorBorde Color que finaliza en el borde más lejano (esquinas).
     * @return BufferedImage con el efecto radial aplicado.
     */
    public static BufferedImage centroAEsquinas(int ancho, int alto, Color colorCentro, Color colorBorde) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        // Identificar el centro del recuadro
        int centroX = ancho / 2;
        int centroY = alto / 2;
        // Calcular la distancia máxima (del centro a una esquina) aplicando Pitágoras
        float maximaDistancia = (float) Math.sqrt(centroX * centroX + centroY * centroY);

        // Recorrer todos los píxeles de la imagen
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                // Calcular la distancia del píxel actual al centro
                float distancia = (float) Math.sqrt(Math.pow(x - centroX, 2) + Math.pow(y - centroY, 2));
                // Obtener el radio/proporción para la interpolación del color (nunca superior a 1)
                float radio = Math.min(distancia / maximaDistancia, 1.0f);
                resultado.setRGB(x, y, interpolarColor(colorCentro, colorBorde, radio));
            }
        }
        return resultado;
    }

    /**
     * Método auxiliar (utilería) que calcula el color intermedio entre dos colores
     * basándose en una proporción (radio).
     * @param c1 Color inicial (cuando radio es 0.0).
     * @param c2 Color final (cuando radio es 1.0).
     * @param radio Factor de interpolación (0.0 a 1.0).
     * @return El valor numérico RGB del color resultante.
     */
    private static int interpolarColor(Color c1, Color c2, float radio) {
        // Calcular el valor interpolado de cada componente de color (R, G, B)
        int rDegradado = (int) (c1.getRed() * (1 - radio) + c2.getRed() * radio);
        int gDegradado = (int) (c1.getGreen() * (1 - radio) + c2.getGreen() * radio);
        int bDegradado = (int) (c1.getBlue() * (1 - radio) + c2.getBlue() * radio);

        return new Color(rDegradado, gDegradado, bDegradado).getRGB();
    }
}