# 2020-vi-no-group-06


[Entrega 2 - Enunciado](https://drive.google.com/drive/folders/1wnyG9G2lMmhpYuLxhIwIOK93yC_bDp8K)

---

# ALTERNATIVAS

### 1. Comparar items de Presupuestos y Operaciones de egreso
Las listas de items de un Presupuesto y Operacion de egreso deben ser iguales para saber si el primero esta asociado al segundo, y tambien para saber si una Operacion de egreso se hizo en base a un Presupuesto. 

+ Ventajas

Es mas simple respetar esos 2 requerimientos. Punto 2 y 3.b

+ Desventajas

No sabemos si se ajusta a las necesdades del usuario
<br/>
<br/>


### 2. Configuracion de Presupuesto
Validar que la **lista** de items que se intenta usar al instanciar un Presupuesto, sea igual a una **lista** de items de alguna Compra pendiente en el repositorioCompras. Y que ademas se agregue ese Presupuesto (luego de validar la lista) en la lista de presupuestos de dicha Compra.

+ Ventajas

TODO

+ Desventajas

TODO
<br/>
<br/>


### 3. Atributos de un Presupuesto
Agregar como atributos de un Presupuesto:
* Documento comercial
* Medio de pago
* Organizacion
* Proveedor

+ Ventajas

TODO

+ Desventajas

TODO
<br/>
<br/>


### 4. Contenido de los Mensajes a Usuarios
Un Mensaje podria contener la lista de Compras aceptadas y una lista de Compras rechazadas

+ Ventajas

Los Usuarios revisores podrian conocer exactamente que Compras fueron aceptadas y cuales rechazadas

+ Desventajas

TODO
<br/>
<br/>


### 5. Requerimiento 3.c
Comparar Presupuestos en vez de valores totales en "seEligioElPresupuestoMasBarato()" en clase Operacion de egreso.

+ Ventajas

TODO

+ Desventajas

TODO
<br/>
<br/>


### 6. Setter para revisores de Compra
Agregar un setter para agregar revisores a una Compra

+ Ventajas

Se podrian agregar mas revisores a una Compra despues de que esta sea creada.

+ Desventajas

Podria generar problemas de seguridad

<br/>
<br/>

---

**Otras consideraciones:**

* Tomamos Operacion de Egreso como un sinonimo de Compra