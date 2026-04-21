package stackModule;

import java.util.Scanner;
import application.Exercise;

public class StackExercise extends Exercise {
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleStack<String> stack;

	public StackExercise(Scanner scanner) {
		super(scanner);
		this.stack = new SimpleLinkedStack<>();
	}

	@Override
	protected void exerciseLogic() {
		switch (currentPhase) {
			case 0:
				menuLogic();
				break;
			case 1:
				pushLogic();
				break;
			case 2:
				popLogic();
				break;
			case 3:
				peekLogic();
				break;
			case 4:
				clearLogic();
				break;
			default:
				System.out.println("Opción no válida, volviendo al menú.");
				currentPhase = 0;
				break;
		}
	}

	private void menuLogic() {
		if (firstTime) {
			System.out.println("¡Bienvenido al ejercicio de Pila (Stack)!");
			System.out.println("--------------------");
			firstTime = false;
		}

		System.out.println("\n--- ESTADO DE LA PILA ---");
		System.out.println("Cantidad de elementos: " + stack.size());
		System.out.println("¿Está vacía?: " + (stack.isEmpty() ? "Sí" : "No"));

		System.out.println("\nElija una opción:");
		System.out.println("1. Push (agregar elemento)");
		System.out.println("2. Pop (remover último elemento)");
		System.out.println("3. Peek (ver último elemento)");
		System.out.println("4. Clear (vaciar la pila)");
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

	// Pregunta al usuario si desea repetir la operación
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

	private void pushLogic() {
		System.out.println("\nEscribe el elemento que deseas agregar a la pila:");
		String elemento = scanner.nextLine();
		stack.push(elemento);
		System.out.println("¡Elemento agregado!");
		System.out.println("Cantidad de elementos: " + stack.size());
		if (!preguntarRepetir()) {
			currentPhase = 0;
		}
	}

	private void popLogic() {
		if (stack.isEmpty()) {
			System.out.println("La pila está vacía, no hay elementos para remover.");
			currentPhase = 0;
			return;
		}
		String elemento = stack.pop();
		System.out.println("Elemento removido: " + elemento);
		System.out.println("Cantidad de elementos: " + stack.size());
		if (!preguntarRepetir()) {
			currentPhase = 0;
		}
	}

	private void peekLogic() {
		if (stack.isEmpty()) {
			System.out.println("La pila está vacía, no hay elementos para ver.");
		} else {
			System.out.println("Elemento en el tope de la pila: " + stack.peek());
		}
		// Peek siempre vuelve al menú (repetirlo muestra lo mismo)
		currentPhase = 0;
	}

	private void clearLogic() {
		if (stack.isEmpty()) {
			System.out.println("La pila ya está vacía.");
		} else {
			stack.clear();
			System.out.println("Pila vaciada por completo.");
		}
		currentPhase = 0;
	}
}
