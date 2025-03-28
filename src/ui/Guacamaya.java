package ui;

import java.util.Scanner;

public class Guacamaya {

    // Scanner global para todo el programa
    public static Scanner escaner;
    private static boolean datos;
    // Arreglos de precios y unidades para todo el programa
    public static double[] precio;
    public static int[] unidades;
    public static  int referencias;
    

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {
        datos = false;
        escaner = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {
            int cantidad, opcion, cantTotal, refeConLimite, unidades;
            double PrecioTotal= 0;
            double VentasTotal = 0;
            double limite, precio; 
            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            opcion = escaner.nextInt();

          

              if(opcion > 1 && !datos){
                System.out.println("debes realizar la primera opcion"); 
                continue; 
                    }

            switch (opcion) {
                case 1:
                    
                    solicitarDatos();
                    

                    break;
                case 2:
                    cantTotal=calcularTotalUnidadesVendidas();
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+ cantTotal);
                    break;
                case 3:
                     PrecioTotal = calcularPrecioPromedio();
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+PrecioTotal);
                    break;
                case 4:
                     VentasTotal = calcularVentasTotales();
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+VentasTotal);
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    limite = escaner.nextDouble();
                    refeConLimite = consultarReferenciasSobreLimite(limite);
                    System.out.println("\nDe las "+ referencias +" referencias vendidas en el dia, "+refeConLimite+" superaron el limite minimo de ventas de "+ limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    escaner.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }



    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {
        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        referencias = escaner.nextInt();
        precio = new double[referencias];
        unidades = new int[referencias];
    }

    
      /**
     * solicita el precio y unidades vendidas de la cantidad de referencias ingresadas 
     * @param  
     */
    public static void solicitarDatos(){
       for (int i = 0; i < referencias; i++) { 
            System.out.println("Digite el precio: ");
            precio[i] = escaner.nextDouble();
            System.out.println("digite las unidades vendidas:");
            unidades[i] = escaner.nextInt();
            escaner.nextLine();       
        } 
        datos=true;    
    }

    /**
     * el metodo calcula el total de las unidades vendidas y las guarda en la variable cantTotal
     * @param 
     * @return cantTotal
     */
    public static int calcularTotalUnidadesVendidas(){
       int cantTotal = 0; 
       for (int i = 0; i < referencias; i++) {
        cantTotal += unidades[i];   
       }
        return cantTotal; 


    }
    /**
     * el metodo calcula el precio promedio de las referecias vendidas
     * @param
     * @return PrecioTotal 
     */
    public static double calcularPrecioPromedio(){
        double PrecioTotal = 0;
        for (int i = 0; i < referencias; i++) {
            PrecioTotal += precio[i]; 
        }
        PrecioTotal/= referencias;
        return PrecioTotal;
    }

    public static double calcularVentasTotales(){
        double VentasTotal =0; 
        for (int i = 0; i < referencias; i++) {
            VentasTotal += precio[i] * unidades[i];  
        }
       return VentasTotal;   
    }


    public static int consultarReferenciasSobreLimite(double limite){
        int refeConLimite = 0;
        for (int i = 0; i < unidades.length; i++) {
            if (precio[i] * unidades[i] > limite) {  
                refeConLimite++; 
            }
        }   
        return refeConLimite;
    }  

}
