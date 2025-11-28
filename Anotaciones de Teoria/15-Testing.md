# Testing con JUnit y Mockito

## Pruebas unitarias

Las pruebas unitarias verifican que una **unidad individual** de código (generalmente un método o clase) funcione correctamente de forma **aislada**. La clave es "aislada": no queremos que dependencias externas (bases de datos, APIs, otros servicios) afecten nuestras pruebas.

## Mockito

Mockito es una biblioteca que te permite crear **objetos simulados (mocks)** de las dependencias de tu clase. Estos mocks imitan el comportamiento de objetos reales pero están bajo tu control total, permitiéndote definir exactamente qué deben devolver.

## El método when()

`when()` es el método principal de Mockito para **definir el comportamiento** de un mock. Permite decir: "cuando se llame a este método con estos parámetros, devuelve este resultado".

```java
when(objetoMock.metodo(parametros)).thenReturn(valorDeRetorno);
```

### Ejemplo Práctico Completo

Si tenemos un servicio de calculadora que depende de un repositorio:

```java
// Clase que queremos probar
public class CalculadoraService {
    private RepositorioNumeros repositorio; // dependecia necesaria para la clase

    public CalculadoraService(RepositorioNumeros repositorio) {
        this.repositorio = repositorio;
    }

    public int sumarDesdeRepositorio(int id1, int id2) {
        int num1 = repositorio.obtenerNumero(id1);
        int num2 = repositorio.obtenerNumero(id2);
        return num1 + num2;
    }
}

// Prueba unitaria
@ExtendWith(MockitoExtension.class) // implementar funcionalidades de la libreria Mockito
class CalculadoraServiceTest {

    @Mock  // Crea un mock automáticamente
    private RepositorioNumeros repositorioMock;

    @InjectMocks  // Inyecta los mocks en la clase a probar
    private CalculadoraService calculadoraService;

    @Test
    void deberiaSumarDosNumeros() {
        // ARRANGE: Configuramos el comportamiento del mock
        when(repositorioMock.obtenerNumero(1)).thenReturn(10);
        when(repositorioMock.obtenerNumero(2)).thenReturn(20);

        // ACT: Ejecutamos el método que queremos probar
        int resultado = calculadoraService.sumarDesdeRepositorio(1, 2);

        // ASSERT: Verificamos el resultado
        assertEquals(30, resultado);
    }
}

```

## Variantes de when

### 1. Devolver un valor

```java
when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
```

### 2. Lanzar una excepción

```java
when(usuarioRepo.findById(999L)).thenThrow(new UsuarioNoEncontradoException());
```

### 3. Lógica personalizada

```java
when(calculadora.sumar(anyInt(), anyInt())).thenAnswer(invocation -> {
    int a = invocation.getArgument(0);
    int b = invocation.getArgument(1);
    return a + b;
});
```

### 4. Múltiples llamadas

```java
// Primera llamada devuelve 1, segunda devuelve 2, tercera devuelve 3
when(contador.siguiente()).thenReturn(1, 2, 3);
```

## Matchers de Argumentos

Cuando no importa el valor exacto del parámetro:

```java
// Cualquier entero
when(repo.buscar(anyInt())).thenReturn(usuario);

// Cualquier String
when(servicio.procesar(anyString())).thenReturn("ok");

// Cualquier objeto de tipo Usuario
when(repo.guardar(any(Usuario.class))).thenReturn(true);

// Valor específico
when(repo.buscar(eq(5))).thenReturn(usuario);

```

## Verificación

Además de definir comportamiento, puedes verificar que los métodos fueron llamados:

```java
@Test
void deberiaLlamarAlRepositorio() {
    calculadoraService.sumarDesdeRepositorio(1, 2);

    // Verifica que el método fue llamado exactamente 2 veces
    verify(repositorioMock, times(2)).obtenerNumero(anyInt());

    // Verifica con parámetros específicos
    verify(repositorioMock).obtenerNumero(1);
    verify(repositorioMock).obtenerNumero(2);
}

```

## Patrón AAA (Arrange-Act-Assert)

Las pruebas unitarias siguen este patrón:

1. **Arrange (Preparar)**: Configurar el escenario con `when()`
2. **Act (Actuar)**: Ejecutar el método que queremos probar
3. **Assert (Afirmar)**: Verificamos que el resultado es el esperado

## ¿Por Qué Usar Mocks?

Sin mocks, la prueba dependería de:

- Conexión a base de datos real
- APIs externas funcionando
- Datos específicos existentes
- Configuraciones complejas

Con mocks, las pruebas son:

- **Rápidas**: No hay I/O real
- **Aisladas**: Solo probamos la lógica
- **Predecibles**: Controlamos todas las respuestas
- **Independientes**: No necesita infraestructura externa