package efectos;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GeneradorDegradados {

    public static BufferedImage izquierdaADerecha(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                float radio = (float) x / ancho;
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    public static BufferedImage derechaAIzquierda(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                float radio = 1.0f - ((float) x / ancho);
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    public static BufferedImage arribaAAbajo(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                float radio = (float) y / alto;
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    public static BufferedImage abajoAArriba(int ancho, int alto, Color colorInicio, Color colorFin) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                float radio = 1.0f - ((float) y / alto);
                resultado.setRGB(x, y, interpolarColor(colorInicio, colorFin, radio));
            }
        }
        return resultado;
    }

    public static BufferedImage centroAEsquinas(int ancho, int alto, Color colorCentro, Color colorBorde) {
        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        int centroX = ancho / 2;
        int centroY = alto / 2;
        float maximaDistancia = (float) Math.sqrt(centroX * centroX + centroY * centroY);

        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                float distancia = (float) Math.sqrt(Math.pow(x - centroX, 2) + Math.pow(y - centroY, 2));
                float radio = Math.min(distancia / maximaDistancia, 1.0f);
                resultado.setRGB(x, y, interpolarColor(colorCentro, colorBorde, radio));
            }
        }
        return resultado;
    }

    private static int interpolarColor(Color c1, Color c2, float radio) {
        int rDegradado = (int) (c1.getRed() * (1 - radio) + c2.getRed() * radio);
        int gDegradado = (int) (c1.getGreen() * (1 - radio) + c2.getGreen() * radio);
        int bDegradado = (int) (c1.getBlue() * (1 - radio) + c2.getBlue() * radio);

        return new Color(rDegradado, gDegradado, bDegradado).getRGB();
    }
}