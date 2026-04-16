# Práctica 1: Gestión de Recursos con Monitores (Pool de Natación)

Este proyecto resuelve el problema de sincronización de una piscina compartida por hilos de tipo `Kid` e `Instructor` utilizando monitores en Java.

### Desafíos Técnicos Resueltos:
* **Sincronización de Hilos:** Implementación de exclusión mutua mediante `synchronized`.
* **Comunicación entre Hilos:** Uso de `wait()` y `notifyAll()` para gestionar estados complejos (ej: un niño no puede nadar si no hay instructores suficientes).
* **Evolución de Restricciones:** Desarrollo de 5 versiones (`Pool0` a `Pool4`) que incrementan la complejidad, desde acceso libre hasta gestión de aforo máximo y ratios niño/instructor.
* **Lógica de Estado:** Control de variables críticas como `kids`, `instructors` y `waitingins` para prevenir condiciones de carrera.
