package application;
import java.util.Scanner;


public class TestExercise extends Exercise {
	
	public TestExercise(Scanner scanner) {
		super(scanner);
	}

	@Override
	
	protected void exerciseLogic() {
		System.out.println("¡Bienvenido al TP del grupo 06!");
		System.out.println("Volver al menú principal, marca 0");
		int opcion = scanner.nextInt();
		scanner.nextLine();
		if (opcion == 0) {
			running = false;
		} else {
			System.out.println("Ingresa una opción válida");
		}
	
	}
	
	
	
}
