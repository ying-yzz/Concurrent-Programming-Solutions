# Práctica 3: Sistemas de Movimiento Concurrente (Territorio de Hormigas)

Simulación de un territorio $N \times N$ donde múltiples hilos (hormigas) compiten por celdas individuales, utilizando herramientas avanzadas de la biblioteca `java.util.concurrent`.

### Conceptos Avanzados Aplicados:
* **Locks y Conditions:** Uso de `ReentrantLock` y múltiples variables `Condition` (una por celda) para una sincronización quirúrgica y eficiente.
* **Estrategia de Detección y Recuperación:** Implementación de un sistema donde las hormigas cambian de dirección si detectan un posible bloqueo tras un *timeout* (`await(300, TimeUnit.MILLISECONDS)`).
* **Optimización de Notificaciones:** Uso de variables de condición específicas para evitar el problema de "reintentos masivos" y mejorar el rendimiento del sistema.
* **Análisis de Estrategias:** Clasificación de soluciones bajo los criterios de prevención y evitación de interbloqueos.
