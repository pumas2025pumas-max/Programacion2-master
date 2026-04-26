package queueModule;

import java.util.Scanner;
import application.Exercise;

public class QueueExercise extends Exercise {
	private int currentPhase = 0;
	private boolean firstTime = true;
	private SimpleQueue<String> queue;

	public QueueExercise(Scanner scanner) {
		super(scanner);
		this.queue = new SimpleLinkedQueue<>();
	}

	@Override
	protected void exerciseLogic() {
		switch (currentPhase) {
			case 0:
				menuLogic();
				break;
			case 1:
				enqueueLogic();
				break;
			case 2:
				dequeueLogic();
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
			System.out.println("¡Bienvenido al ejercicio de Cola (Queue)!");
			System.out.println("--------------------");
			firstTime = false;
		}

		System.out.println("\n--- ESTADO DE LA COLA ---");
		System.out.println("Cantidad de elementos: " + queue.size());
		System.out.println("¿Está vacía?: " + (queue.isEmpty() ? "Sí" : "No"));

		System.out.println("\nElija una opción:");
		System.out.println("1. Enqueue (agregar elemento)");
		System.out.println("2. Dequeue (remover primer elemento)");
		System.out.println("3. Peek (ver primer elemento)");
		System.out.println("4. Clear (vaciar la cola)");
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

	private void enqueueLogic() {
		System.out.println("\nEscribe el elemento que deseas agregar a la cola:");
		String elemento = scanner.nextLine();
		queue.enqueue(elemento);
		System.out.println("¡Elemento agregado!");
		System.out.println("Cantidad de elementos: " + queue.size());
		if (!preguntarRepetir()) {
			currentPhase = 0;
		}
	}

	private void dequeueLogic() {
		if (queue.isEmpty()) {
			System.out.println("La cola está vacía, no hay elementos para remover.");
			currentPhase = 0;
			return;
		}
		String elemento = queue.dequeue();
		System.out.println("Elemento removido: " + elemento);
		System.out.println("Cantidad de elementos: " + queue.size());
		if (!preguntarRepetir()) {
			currentPhase = 0;
		}
	}

	private void peekLogic() {
		if (queue.isEmpty()) {
			System.out.println("La cola está vacía, no hay elementos para ver.");
		} else {
			System.out.println("Primer elemento de la cola: " + queue.peek());
		}
		// Peek siempre vuelve al menú (repetirlo muestra lo mismo)
		currentPhase = 0;
	}

	private void clearLogic() {
		if (queue.isEmpty()) {
			System.out.println("La cola ya está vacía.");
		} else {
			queue.clear();
			System.out.println("Cola vaciada por completo.");
		}
		currentPhase = 0;
	}
}

//Este es FIFO 
