# Práctica 2: Prevención de Deadlocks (Filósofos Comensales)

Implementación del problema clásico de los Filósofos Comensales para estudiar y prevenir el interbloqueo (*deadlock*) en sistemas concurrentes.

### Soluciones Implementadas contra el Interbloqueo:
* **Asimetría de Recursos:** Configuración donde los filósofos pares e impares recogen los cubiertos en distinto orden para romper la **espera circular**.
* **Capacidad Limitada:** Implementación de una mesa con gestión de aforo para asegurar que siempre haya al menos un recurso libre.
* **Análisis de Condiciones de Coffman:** Estudio técnico sobre cómo romper las condiciones de "retención y espera" y "espera circular".
* **Pruebas de Estrés:** Análisis de la probabilidad de interbloqueo según el retardo (`N`) introducido entre acciones.
