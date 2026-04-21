# 📋 Revisión de TPs - Programación II

## TP 01 — Fundamentos de Java, Herencia ✅ (con observaciones)

| Requisito | Estado | Notas |
|-----------|--------|-------|
| Proyecto `Parcial_1_Grupo_06` | ✅ | Correcto |
| Package `application` | ✅ | Correcto |
| Clase `MainProgram` | ⚠️ | Funciona, pero ver observaciones |
| Clase `Exercise` (abstracta) | ✅ | Correcto |
| Clase `TestExercise` | ❌ | Tiene un bug con `@override` |

### Observaciones TP01:

#### 1. ❌ `override.java` — Anotación custom innecesaria
Crearon una anotación custom `@override` (minúscula). Java ya tiene `@Override` (con mayúscula) incorporada. Este archivo **no debería existir** y `TestExercise` debería usar `@Override` nativo:

```diff
- @override
+ @Override
```

#### 2. ⚠️ `MainProgram` — Falta método `selectExercise(Scanner)`
La consigna pide un método separado `selectExercise(Scanner scanner)` que ofrezca elegir ejercicio. En el código actual, toda esa lógica está metida dentro de `run()`. Funciona, pero no cumple exactamente con la estructura pedida.

#### 3. ⚠️ `TestExercise` — Lógica invertida
```java
if (opcion != 0) {
    System.out.println("Ingresa una opción válida");
    running = false; // ← sale del ejercicio cuando la opción NO es 0
}
```
Debería salir cuando `opcion == 0` (como dice la consigna). Actualmente si el usuario pone 0, no pasa nada y sigue en loop.

#### 4. ⚠️ `Exercise.run()` — No resetea `running`
Cuando el usuario sale de un ejercicio (`running = false`), si intenta volver a ejecutarlo desde el menú principal, no va a entrar al `while(running)` porque `running` sigue en `false`. Falta un reset:
```diff
  public void run() {
+     running = true;
      while (running) {
          exerciseLogic();
      }
  }
```

---

## TP 02 — TDA List (con listas nativas de Java) ⚠️

| Requisito | Estado | Notas |
|-----------|--------|-------|
| Package `listModule` | ✅ | |
| Clase `ListExercise` | ✅ | |
| Campos: currentPhase, firstTime, list | ✅ | |
| menuLogic con estado de lista | ✅ | |
| addLogic | ✅ | |
| removeByIndexLogic | ✅ | |
| removeByReferenceLogic | ✅ | |
| clearListLogic | ✅ | |
| **Ofrecer repetir operación (sí/no)** | ❌ | **No implementado** |
| Mostrar elementos después de modificar | ❌ | Solo se muestra al volver al menú |

### Observaciones TP02:

#### 1. ❌ Falta opción de repetir operación
La consigna dice: *"Ofrezca repetir la operación (si/no). Si el usuario no escribe una entrada válida, se repite la pregunta."* Esto **no está implementado** en ninguna de las funciones (`addLogic`, `removeByIndexLogic`, etc.). Actualmente simplemente vuelve al menú con `currentPhase = 0`.

#### 2. ❌ Falta mostrar la lista después de cada operación
La consigna dice que después de agregar/borrar se deben *"mostrar los elementos de la lista luego de la modificación"*. Actualmente solo se ve el estado al volver al menú.

> [!NOTE]
> El código actual ya usa `SimpleLinkedList` (del TP03) en vez de las listas nativas de Java. Esto es correcto si ya completaron el TP03, ya que la consigna del TP03 pide reemplazarlas.

---

## TP 03 — Implementación del TDA List 🔴 (tiene bugs críticos)

| Requisito | Estado | Notas |
|-----------|--------|-------|
| Interfaz `SimpleList<E>` | ✅ | Todos los métodos definidos |
| `SimpleArrayList<E>` | ❌ | **3 bugs críticos** |
| `SimpleLinkedList<E>` | ❌ | **1 bug crítico** |
| Reemplazar listas nativas en ListExercise | ✅ | Ya usa SimpleLinkedList |

### 🔴 Bugs Críticos en `SimpleArrayList`

#### Bug 1: `add(int index, E element)` — Línea 43: Punto y coma espurio
```java
if (size == array.length); {  // ← ¡EL PUNTO Y COMA MATA EL IF!
    resize();
}
```
Ese `;` después del `if` hace que el `resize()` se ejecute **SIEMPRE**, sin importar la condición. El bloque `{}` es independiente del `if`.

**Fix:**
```diff
- if (size == array.length); {
+ if (size == array.length) {
```

#### Bug 2: `remove(int index)` — Línea 59: Loop infinito / crash
```java
for (int i = size; i > index; i ++) {  // ← ¡i++ en vez de i--!
    array[i] = array[i+1];             // ← ¡accede fuera del array!
}
```
Este loop **nunca termina** (o causa `ArrayIndexOutOfBoundsException`). `i` empieza en `size`, va incrementando, y la condición `i > index` siempre es true → **loop infinito + crash**.

**Fix:**
```diff
- for (int i = size; i > index; i ++) {
-     array[i] = array[i+1];
+ for (int i = index; i < size - 1; i++) {
+     array[i] = array[i + 1];
  }
```

#### Bug 3: `get(int index)` y `set(int index, E)` — Off-by-one
```java
if(index < 0 || index > size) throw new IndexOutOfBoundsException();
//                    ^^^^^^ debería ser >=
```
Con `index > size`, se permite acceder a `array[size]`, que está **fuera del rango válido** (los índices van de 0 a size-1).

**Fix:**
```diff
- if(index < 0 || index > size) throw new IndexOutOfBoundsException();
+ if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
```
> [!CAUTION]
> Este mismo error está en **ambos** métodos: `get()` (línea 97) y `set()` (línea 104).

---

### 🔴 Bug Crítico en `SimpleLinkedList`

#### Bug en `add(int index, E element)` — Línea 64: Enlace incorrecto
```java
Node<E> b = getNode(index);
Node<E> a = b.prev;
newNode.prev = a;
newNode.next = b;
b.prev = newNode;
a.prev = newNode;  // ← ¡ERROR! Debería ser a.next
```
Se está seteando `a.prev = newNode` en vez de `a.next = newNode`. Esto **rompe la cadena de enlaces** hacia adelante. El nodo `a` pierde su enlace `next` correcto y su `prev` apunta al nodo nuevo (que no es su anterior).

**Fix:**
```diff
- a.prev = newNode;
+ a.next = newNode;
```

---

## TP 04 — Stack y Queue ❌ (No implementado)

La consigna pide crear:

| Componente | Package | Descripción |
|------------|---------|-------------|
| `SimpleStack<E>` | `stackModule` | Interfaz con push, pop, peek, clear, size, isEmpty |
| `SimpleArrayStack<E>` | `stackModule` | Implementación con array |
| `SimpleLinkedStack<E>` | `stackModule` | Implementación con nodos enlazados |
| `StackExercise` | `stackModule` | Ejercicio interactivo para probar Stack |
| `SimpleQueue<E>` | `queueModule` | Interfaz con enqueue, dequeue, peek, clear, size, isEmpty |
| `SimpleArrayQueue<E>` | `queueModule` | Implementación con array |
| `SimpleLinkedQueue<E>` | `queueModule` | Implementación con nodos enlazados |
| `QueueExercise` | `queueModule` | Ejercicio interactivo para probar Queue |

### Notas del TP04:
- StackExercise y QueueExercise heredan de Exercise
- Pop, Dequeue y Peek deben verificar que la estructura **no esté vacía** antes de operar
- Peek debería volver al menú principal (no repetir, ya que muestra siempre lo mismo)
- Clear no debería llamar a clear si ya está vacía

---

## 📊 Resumen General

| TP | Estado | Bugs Críticos | Bugs Menores |
|----|--------|---------------|--------------|
| TP01 | ⚠️ Casi bien | 0 | 3-4 |
| TP02 | ⚠️ Incompleto | 0 | 2 (falta repetir operación y mostrar lista) |
| TP03 | 🔴 Con bugs | **4 bugs críticos** | 0 |
| TP04 | ❌ No hecho | — | — |

> [!IMPORTANT]
> Los 4 bugs críticos del TP03 causan que `SimpleArrayList.remove()` sea **inutilizable** (crash/loop infinito), que `add(int,E)` haga resize siempre, y que la inserción en medio de `SimpleLinkedList` **rompa la lista**. Estos deben ser corregidos antes de entregar.
