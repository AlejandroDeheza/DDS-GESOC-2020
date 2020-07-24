# 2020-vi-no-group-06

[Entrega 3 - Enunciado](https://docs.google.com/document/d/1mtYaUC0X5NH-vHnJxd1mKQrZOiyMTVpM0VnbK05cD3g/edit)

---

Requerimientos y soluciones planteadas:

1. **Ampliar el comportamiento de las entidades mediante categorias, a las cuales en cualquier momento se les puede agregar, modificar o quitar comportamiento**

    **Solución planteada:** la categoría contiene una lista de validaciones, que son elegidas al momento de crearse la categoría. Al ejecutarse una acción en la entidad, esta delega el comportamiento a la categoria, la cual realiza los cambios, corre todas las validaciones, y hace rollback si las mismas no pasan. Por otro lado, hay un repositorio de categorias para que se asignen solo categorias presentes en este repositorio a las entidades.
    
    **Ventajas:** la solución funciona para los comportamientos definidos actualmente y admite realizar modificaciones a las categorias en tiempo de ejecución.
    
    **Desventajas:** es difícil adaptar la solución a posibles comportamientos que no sean restricciones o chequeos.
    
    **Otras alternativas:** usar composición permite delegar cualquier tipo de comportamiento en objetos intercambiables, pero no se puede reemplazar el comportamiento de los objetos en ejecución (el comportamiento se define a nivel código). El patrón command permite ejecutar distintas acciones, pero ¿cómo elijo que comportamiento ejecutar ante determinada acción?
    
2. **Agregar etiquetas a las operaciones de egreso para poder filtrarlas en los reportes**

    **Solución planteada:** se cuenta con un repositorio de etiquetas, y cada operación de egreso cuenta con una lista de etiquetas que elige de este repositorio (puede tener 0 o muchas). La etiqueta está compuesta por texto en mayúscula (se realiza la conversión al crearse) para evitar problemas con el string. Cada entidad puede realizar un conteo de gastos por etiqueta filtrando con este parametro las operaciones de egreso que tiene asignadas.
    
    **Ventajas:** es simple y cumple los requerimientos. 
    
    **Desventajas:** si bien se toma la precaución de convertir todo a mayuscula y por el comportamiento del sistema no debería traer problemas, usar como identificador un string no es nunca la mejor idea. 
    
    **Otras alternativas:** asignar algun ID de etiqueta. 
    
3. **Ejecutar una tarea programada que realice la validación de los egresos cada cierta cantidad de tiempo**

    **Solución planteada:** se utilizó un scheduler de la biblioteca utils de Java, que ejecuta la tarea cada 5 segundos (a fines demostrativos). 
    
    **Ventajas:** es simple, y ahorra el overhead de utilizar un framework de scheduling como Quartz, que para el objetivo de esta entrega no hace falta. 
    
    **Desventajas:** requiere que el programa esté en ejecución todo el tiempo, es una solución limitada. 
    
    **Otras alternativas:** utilizar alguna tarea programada a nivel SO (muy probablemente se opte por esta alternativa a futuro) o algún framework dedicado. 
    
    
