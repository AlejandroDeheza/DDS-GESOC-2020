# 2020-vi-no-group-06


[Entrega 1](https://drive.google.com/drive/folders/1wnyG9G2lMmhpYuLxhIwIOK93yC_bDp8K)

---

<p align="center"> 

# ALTERNATIVAS
</p>

### 1. Clase DocumentoComercial
Crear una clase para representar un documento comercial. Que tenga de atributos “tipoDocumentoComercial” y “numeroDocumento”

+ Ventajas

Delegamos el conocimiento de esos 2 atributos a otra clase y no a la clase “OperaciónDeEgreso”

+ Desventajas

Estaríamos creando objetos sin funcionalidades que solo almacenan datos, algo que se puede hacer en una base de datos.
<br/>
<br/>


### 2. Proveedores de una operación de egreso
Crear una lista para representar un conjunto de Proveedores para una misma operación de egreso. Cambiando en clase “OperacionDeEgreso” el atributo “Proveedor proveedor” por “List<EntidadBase> proveedores”.

+ Ventajas

Nos da más flexibilidad a la hora de generar una OperacionDeEgreso. Podemos relacionar uno o varios proveedores con una misma operación. 

+ Desventajas

Podríamos estar modelando algo que no se vaya a usar.
<br/>
<br/>


### 3. DNI en vez de CUIL
Usar número de DNI en vez de CUIL como atributo para un Proveedor.

+ Ventajas

Podemos usar una variable int en vez de long 

+ Desventajas

Puede haber colisiones entre DNIs. Es más confiable usar un CUIL

**Cualquiera de estos datos se puede representar también como un string, siendo que no se va a operar numericamente con el.**
<br/>
<br/>


### 4. Más tipos de documentos comerciales
Añadir al enum “TipoDocumentoComercial” más tipos como: **remito, nota de débito, nota de crédito**

Según lo [referenciado](http://www.mundoit.com.ar/documentos-comerciales-de-una-empresa/) en el enunciado del TP.
<br/>

Y: **Presupuesto, nota de venta y resumen de cuenta.**

Tal como se puede apreciar en la siguiente captura del libro “La organización y sus sistemas de información” de la profesora Pollo Cattaneo. Ya que, en una operación de egreso de una organización, se pueden recibir esos tipos de documentos:

<p align="center"> 
<img src="TP GeSoc/Imagenes/documentosComerciales.png">
</p>

+ Ventajas

Nos da más flexibilidad para adaptar el proyecto a varias organizaciones

+ Desventajas

Podríamos estar generando tipos que no vayamos a usar. Podría estar fuera del alcance del proyecto.
<br/>
<br/>


### 5. Método de pago con Cheque
Agregar un método de pago por cheque, tal como se ejemplifica en el punto 5 del enunciado

+ Ventajas

Se agrega flexibilidad a la hora de generar operaciones de egreso.

+ Desventajas

No respetan los medios de pago de mercado libre.
<br/>
<br/>


### 6. Relación bidireccional entre Entidad Jurídica y Entidad Base
Generar una relación bidireccional entre ambas entidades. Se podría inicializar una referencia de Entidad base a Entidad Jurídica cuando las primeras son agregadas a la lista de entidades asociadas a la Entidad Jurídica. Así una Entidad base conoce a la única entidad jurídica a la que está asociada; a la vez que una entidad jurídica conoce a todas las entidades base que tiene asociadas (en su lista). Un ejemplo grafico de lo que se trata de decir extraído de “Módulo 02: Referencias. Estado. Compartir objetos. Identidad.” del prof. Dodino:

<p align="center"> 
<img src="TP GeSoc/Imagenes/relacionBidireccional.png">
</p>

+ Ventajas

Permite que se puedan mandar mensajes entre las instancias de las 2 clases

+ Desventajas

Puede que por el momento no sea muy útil, ya que no conocemos el comportamiento de ambas entidades.
Puede ocurrir que, por una mala sincronización, quede una referencia libre o vacía.

<p align="center"> 
<img src="TP GeSoc/Imagenes/relacionBidireccionalDesventaja.png" width=500>
</p>

<br/>
<br/>
 

### 7. Clasificación de Empresas en tiempo de ejecución
Calcular el tipo de empresa según lo [estipulado por la AFIP](https://pymes.afip.gob.ar/estiloAFIP/pymes/ayuda/default.asp)

+ Ventajas

No hace falta actualizar el tipo de organización, se genera calculando el valor de las ventas totales anuales

+ Desventajas

Todavía no tenemos la información suficiente para poder calcular ese valor
<br/>
<br/>


### 8. Doble validación de contraseña
Agregar una validación extra al constructor de la clase “Usuario” además del que esta el “BuilderUsuario”

+ Ventajas

Permite que se valide la contraseña si se genera un usuario directamente con el constructor en vez de con el Builder. Algo que puede pasar no intencionalmente.

+ Desventajas

El mismo código se ejecuta 2 veces en caso de que se creé un usuario con el Builder.

**De todas formas, esta validación no deberia ser necesaria porque externamente no va a haber acceso a ese metodo. "Confiar en el adentro, desconfiar del afuera" - Diseño del manejo de errores.**
<br/>
<br/>

**Otras consideraciones:**

* En *validadorPasswords* utilizamos el patrón Singleton, ya que, al no tener un estado mutable y solo realizar operaciones de chequeo, no trae mayores complicaciones y ahorra tiempo computacional, al evitar tener que releer el archivo de contraseñas cada vez que se genera una nueva instancia. 
Sin embargo, no habría problema en generar una nueva instancia de *validadorPasswords* cada vez que se desee crear una nueva contraseña.
También podríamos realizar inyección de dependencias, enviando el validador de passwords como parametro del constructor del builder, al momento de crearlo. 

* En *builderOperacionDeEgreso* y *builderUsuario* utilizamos el patrón builder, ya que vamos a tener diferentes representaciones del objeto a construir y dicho patrón nos permite tener un mayor control sobre el proceso de construcción. 
De todas formas, no es algo imprescindible en este momento, por lo que podríamos optar por usar otras formas de construir estos objetos. 




