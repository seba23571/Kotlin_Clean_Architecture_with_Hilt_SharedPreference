# Kotlin_Clean_Architecture_with_Hilt
 
# Ide Version : Android Studio Dolphin | 2021.3.1 Canary 6

# Sebas developed.



# Descripciòn.
project for search user from api rest jsonplaceholder
proyecto Android usando las api rest desde https://jsonplaceholder.typicode.com/ 

es un buscador que permite atraves de un recycler view buscar por nombre de usuarios y sus correspondientes publicaciones.


Usando **Clean Architecture** fusionado con el patrón **MVVM** con ViewModel y LiveData ,**HILT** COROUTINES para peticiones asincronicas con RETROFIT, ROOM DATABASE SQLITE Y SHAREPREFERENCE.Y COMPONENTES DE NAVEGACION.
usa un auto refresh cada 6 horas en el hipotetico caso que se produzcan actualizaciones en el backend para que se actualice los datos de las tablas ROOM DATABASE SQLITE.


Uso de **ViewBinding** para conectar todas las vistas de la UI del proyecto.

Se usan **componentes de navegación** que le da  un detalle de deslizamiento mas visual al intercambio de diferentes Fragment.


 # user post searches

 # ![AppRecortadaV5](https://user-images.githubusercontent.com/1193887/155828557-ed6c57f7-7d16-4045-9336-9612b515894d.gif)
