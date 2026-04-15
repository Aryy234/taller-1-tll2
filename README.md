# Documentación: Generación de Efectos de Degradado

## ¿Qué se hizo?

Se refactorizó el código original para cumplir con los principios de diseño de software (como el Principio de Responsabilidad Única - SRP). Se separó la lógica de generación de imágenes de la lógica principal de la aplicación.

Se crearon las siguientes responsabilidades:

1.  **`GeneradorDegradados`**: Se creó una nueva clase independiente encargada exclusivamente de calcular los píxeles para generar degradados desde cero. Esto mantiene el código limpio y permite que los efectos sean reutilizados.
    *   Se definieron métodos para crear gradientes lineales (izquierda a derecha, derecha a izquierda, arriba a abajo, abajo a arriba) y un degradado radial (centro a esquinas).
    *   Se implementó el método `interpolarColor` que calcula la transición entre dos colores dependiendo de una relación (radio 0.0 a 1.0) que indica la posición del píxel en el lienzo, generando así el efecto visual de difuminado continuo.

2.  **`Imagen` (Clase Principal)**: Ahora actúa como un orquestador. 
    *   Define el tamaño del lienzo (800x600 px).
    *   Llama secuencialmente a la clase `GeneradorDegradados` pasándole los tonos seleccionados.
    *   Guarda el resultado generado desde cero en 5 archivos separados en el directorio `src/imagenes/`.

## Características de la solución

*   **Modular:** Si se desean nuevos efectos, sólo hay que agregarlos a `GeneradorDegradados`.
*   **Simple y legible:** Se abstrajo matemática básica (interpolaciones lineales y cálculo de distancias por Pitágoras para el efecto radial) en operaciones puntuales, lo que hace que los ciclos de dibujado $(x, y)$ sean fáciles de comprender.
*   **Colores seleccionados:** Tal como fue solicitado, se configuró una paleta basada en tonos morados (desde un morado oscuro hacia uno claro) para dar uniformidad y elegancia a los degradados generados.