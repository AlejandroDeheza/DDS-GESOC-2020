import static org.mockito.Mockito.*;
import org.junit.*;

import exceptions.contraseniaComunException;
import validacionesContrasenias.ValidadorDePassComun;
import validacionesContrasenias.ValidarTodo;

public class TestsValidadorPassComunesConMock {
	
	ValidadorDePassComun impostor;
	
	@Before
	public void before() {
		impostor = mock(ValidadorDePassComun.class);
	}
	
	@After
	public void after() {
		reset(impostor);
	}

	@Test(expected = contraseniaComunException.class)
	public void validarContraseniaComunConMock() {
		doThrow(new contraseniaComunException()).when(impostor).validar("contraseña");
		impostor.validar("contraseña");
	}
	
	@Test
	public void validarTodoConMock() {
		doNothing().when(impostor).validar("qiehgyWfiiyrt2");
		ValidarTodo validador = new ValidarTodo(null);
		validador.setValidadorDePassComun(impostor);
		validador.validar("qiehgyWfiiyrt2");
		verify(impostor).validar("qiehgyWfiiyrt2");
	}

}
