# Documentación: Generación de Efectos de Degradado

## ¿Qué se hizo?

Se refactorizó el código original para cumplir con los principios de diseño de software (como el Principio de Responsabilidad Única - SRP). Se separó la lógica de generación de imágenes de la lógica principal de la aplicación.

Se crearon las siguientes responsabilidades:

1.  **`GeneradorDegradados`**: Se creó una nueva clase independiente encargada exclusivamente de calcular los píxeles para generar degradados desde cero. Esto mantiene el código limpio y permite que los efectos sean reutilizados.
    
    ### Detalle de Funciones en `GeneradorDegradados`
    *   **`izquierdaADerecha`**: Genera un degradado horizontal. El recorrido sobre los píxeles determina un `radio` progresivo calculando la relación de la coordenada `x` respecto al `ancho` total (`x / ancho`). Esto hace que el color difumine desde el inicio en el borde izquierdo hasta el final en el borde derecho.
    *   **`derechaAIzquierda`**: Funciona igual que el anterior, pero invirtiendo la proporción del `radio` (`1.0 - (x / ancho)`). Como resultado, el color inicial se plasma en la derecha y fluye hacia la izquierda.
    *   **`arribaAAbajo`**: Genera un degradado vertical. Calcula la proporción en base a la coordenada `y` respecto al `alto` total (`y / alto`). Inicia el color de degradado en el límite superior y lo difumina hacia el inferior.
    *   **`abajoAArriba`**: Degradado vertical invertido. El cálculo del `radio` es `1.0 - (y / alto)`. El color de inicio se asienta en la parte inferior de la imagen.
    *   **`centroAEsquinas`**: Genera un degradado radial o circular. Identifica el centro de la imagen y calcula la distancia euclidiana de cada píxel hacia este punto usando el Teorema de Pitágoras. El `radio` de interpolación es la proporción de dicha distancia respecto a la distancia máxima posible hacia una esquina.
    *   **`interpolarColor`**: Es la función matemática de soporte. Toma el componente Red, Green y Blue de los dos colores configurados y, basándose en la proporción que va de `0.0` a `1.0` (el `radio`), calcula el tinte exacto mediante una combinación lineal, logrando así un cambio de tono imperceptible y fluido.

## ¿Qué es y cómo funciona `BufferedImage`?

La herramienta principal para la creación gráfica en este proyecto es la clase **`BufferedImage`** proporcionada por Java (`java.awt.image`). 
* **Un lienzo en memoria:** Funciona creando una cuadrícula bidimensional (como una matriz) que reside temporalmente en la memoria RAM (el *buffer*). Esta cuadrícula representa el espacio de la imagen definido por el `ancho` y `alto`.
* **Codificación de color:** Al instanciarla con `BufferedImage.TYPE_INT_RGB`, le instruimos a Java que asigne un bloque de espacio donde cada elemento de la cuadrícula (cada píxel) guardará su color como un único número entero (`int`), el cual agrupa los componentes primarios del color: Rojo (Red), Verde (Green) y Azul (Blue).
* **Control absoluto:** Lo que la hace ideal para generar degradados es que permite manipular directamente la información de color de cada punto usando el método `setRGB(x, y, color)`. Gracias a esto, mediante ciclos repetitivos (`for`), viajamos por cada coordenada matemática $(x, y)$ rellenando el lienzo dinámicamente según dicten los cálculos del degradado.

2.  **`Imagen` (Clase Principal)**: Ahora actúa como un orquestador. 
    *   Define el tamaño del lienzo (800x600 px).
    *   Llama secuencialmente a la clase `GeneradorDegradados` pasándole los tonos seleccionados.
    *   Guarda el resultado generado desde cero en 5 archivos separados en el directorio `src/imagenes/`.

## Características de la solución

*   **Modular:** Si se desean nuevos efectos, sólo hay que agregarlos a `GeneradorDegradados`.
*   **Simple y legible:** Se abstrajo matemática básica (interpolaciones lineales y cálculo de distancias por Pitágoras para el efecto radial) en operaciones puntuales, lo que hace que los ciclos de dibujado $(x, y)$ sean fáciles de comprender.
*   **Colores seleccionados:** Tal como fue solicitado, se configuró una paleta basada en tonos morados (desde un morado oscuro hacia uno claro) para dar uniformidad y elegancia a los degradados generados.