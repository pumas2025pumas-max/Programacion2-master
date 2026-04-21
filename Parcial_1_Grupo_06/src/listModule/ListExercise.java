package listModule;
import java.util.Scanner;
import application.Exercise;

public class ListExercise extends Exercise {
    private int currentPhase = 0;
    private boolean firstTime = true;
    private SimpleList list;
    public ListExercise(Scanner scanner) {
        super(scanner);
        this.list = new SimpleLinkedList();
        
       
    }

    @Override
    protected void exerciseLogic() {
        switch (currentPhase) {
            case 0:
                menuLogic();
                break;
            case 1:
                addLogic();
                break;
            case 2:
                removeByIndexLogic();
                break;
            case 3:
                removeByReferenceLogic();
                break;
            case 4:
                clearListLogic();
                break;
            default:
                System.out.println("Opción no válida, volviendo al menú.");
                currentPhase = 0;
                break;
        }
    }

    private void menuLogic() {
        if (firstTime) {
            System.out.println("¡Bienvenido a tu primer ingreso!");
            System.out.println("--------------------");
            firstTime = false;
        }

        System.out.println("\n--- ESTADO ACTUAL DE LA LISTA ---");
        
        
        System.out.print("Elementos: [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + (i < list.size() - 1 ? ", " : ""));
        }
        System.out.println("]");

        System.out.println("Cantidad de elementos: " + list.size());
        System.out.println("¿La lista está vacía?: " + (list.isEmpty() ? "Sí" : "No"));
        
        System.out.println("\nElija una opción:");
        System.out.println("1. Agregar un elemento");
        System.out.println("2. Remover por índice");
        System.out.println("3. Remover por palabra (referencia)");
        System.out.println("4. Limpiar toda la lista");
        System.out.println("0. Salir al Menú Principal");

        
        if (scanner.hasNextInt()) {
            currentPhase = scanner.nextInt();
            scanner.nextLine(); 
            
            if (currentPhase == 0) {
                running = false;
            }
        } else {
            System.out.println("Error: Ingrese un número.");
            scanner.nextLine(); 
        }
    }

    // Muestra los elementos actuales de la lista separados por coma
    private void mostrarLista() {
        System.out.print("Elementos: [");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + (i < list.size() - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    // Pregunta al usuario si desea repetir la operación (si/no)
    private boolean preguntarRepetir() {
        while (true) {
            System.out.println("¿Desea repetir la operación? (si/no):");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("si") || respuesta.equals("sí")) {
                return true;
            } else if (respuesta.equals("no")) {
                return false;
            }
            System.out.println("Entrada no válida. Ingrese 'si' o 'no'.");
        }
    }

    private void addLogic() {
        System.out.println("\nEscribe el elemento que deseas agregar:");
        String elementoNuevo = scanner.nextLine();
        list.add(elementoNuevo);
        System.out.println("¡Agregado!");
        mostrarLista();
        if (!preguntarRepetir()) {
            currentPhase = 0;
        }
    }

    private void removeByIndexLogic() {
        if (list.isEmpty()) {
            System.out.println("La lista está vacía, nada que borrar.");
            currentPhase = 0;
            return;
        }
        System.out.println("\nEscribe el índice (posición) a eliminar (0 a " + (list.size() - 1) + "):");
        if (scanner.hasNextInt()) {
            int indice = scanner.nextInt();
            scanner.nextLine(); 

            if (indice >= 0 && indice < list.size()) {
                String borrado = (String) list.remove(indice);
                System.out.println("Has borrado: " + borrado);
                mostrarLista();
                if (!preguntarRepetir()) {
                    currentPhase = 0;
                }
            } else {
                System.out.println("Índice fuera de rango.");
            }
        } else {
            System.out.println("Error: Ingrese un número.");
            scanner.nextLine();
        }
    }

    private void removeByReferenceLogic() {
        if (list.isEmpty()) {
            System.out.println("La lista está vacía.");
            currentPhase = 0;
            return;
        }
        System.out.println("\nEscribe la palabra exacta que necesitas eliminar:");
        String palabra = scanner.nextLine();

        if (list.remove(palabra)) {
            System.out.println("Elemento '" + palabra + "' eliminado con éxito.");
            mostrarLista();
            if (!preguntarRepetir()) {
                currentPhase = 0;
            }
        } else {
            System.out.println("No se encontró la palabra '" + palabra + "' en la lista.");
        }
    }

    private void clearListLogic() {
        if (list.isEmpty()) {
            System.out.println("\nLa lista ya está vacía.");
        } else {
            list.clear();
            System.out.println("\nLista vaciada por completo.");
        }
        mostrarLista();
        currentPhase = 0;
    }
}
