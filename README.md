# Ejercicio-Concesionaria-Spring-CodoACodo
Ejercicio del desarrollo de una Api de una concesionaria para el programa Codo A Codo Spring

## Consigna

Una concesionaria de autos desea realizar una api REST que le permita realizar la carga de determinados autos usados. Para ello se deberan desarrollar los siguentes endpoints: 
	1- v1/api/vehicles || POST || agrega un nuevo vehiculo
	2- v1/api/vehicles || GET  || retorna una lista de vehiculos usados
	3- v1/api/vehicles/dates?since="to=" || GET || retorna un listado de vehiculos según fecha de fabricación.
	4-v1/api/vehicles/{id} || GET || muestra toda la info relacionada con el vehiculo.

Dado que la concesionaria es un cliente muy exigente, se sugiere utilizar la arquitectura multicapa y buenas prácticas relacionadas a esta arquitectura.

Ejemplo de payload de carga: 

{
	"brand": "Chevrolet",
	"model": "Corsa",
	"manufacturingDate": "2000-11-20",
	"numberOfKilometers": 115000,
	"doors" : "5",
	"price" : "90000",
	"currency" : "AR",
	"services" : [
		{
			"date": "2003-05-20",
			"kilometers": "60000",
			"descriptions": "cambio de filtro de aire"
		}
	
	],
	"countOfOwners": "2" 
}
