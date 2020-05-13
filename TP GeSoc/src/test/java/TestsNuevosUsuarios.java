//package model;

import exceptions.*;
import model.*;

import org.junit.Assert;


import org.junit.Before;
import org.junit.Test;

public class TestsNuevosUsuarios {
	
		  private Usuario usuario;
		  private BuilderUsuario builder;

		  @Before
		  public void init() {
		  }
		 
		  @Test
		  public void setearContraseniaNormalEnBuilder() {
			  builder = new BuilderUsuario();
			  builder.setPassword("qiehgWfiiyrt26");
			  Assert.assertEquals("qiehgWfiiyrt26",builder.getPassword());
		  }
		  
		  @Test
		  public void setearUsernameNormalEnBuilder() {
			  builder = new BuilderUsuario();
			  builder.setUsername("Jorge");
			  Assert.assertEquals("Jorge",builder.getUsername());
		  }
		  
		  @Test
		  public void setearTipoUsuarioEnBuilder() {
			  builder = new BuilderUsuario();
			  builder.setTipo(TipoUsuario.ADMIN);
			  Assert.assertEquals(TipoUsuario.ADMIN,builder.getTipo());
		  }
		  
		  
		  @Test(expected = longitudDeContraseniaBajaException.class)
		  public void setearContraseniaCorta() {
			  builder = new BuilderUsuario();
			  builder.setPassword("Holi");
		  }

		  @Test(expected = contraseniaUsadaPreviamenteException.class)
		  public void setearContraseniaYaUsada() {
			 builder = new BuilderUsuario();
		     builder.setPassword("qiehgyWfiiyrt2");
		     builder.setTipo(TipoUsuario.ADMIN);
		     builder.setUsername("Jorge");
		     usuario=builder.crearUsuario();
		     usuario.cambiarContrasenia("qiehgyWfiiyrt2");
		  }
		  
		  @Test (expected = contraseniaComunException.class)
		  public void setearContraseniaComun() {
			  builder = new BuilderUsuario();
			  builder.setPassword("dickhead");
		  }
		 
		  
		  @Test (expected = contraseniaComunException.class)
		  public void setearContraseniaComun2() {
			  builder = new BuilderUsuario();
			  builder.setPassword("password");
		  }
		  
		  @Test (expected = contraseniaSinMayusculaException.class)
		  public void setearContraseniaSinMayuscula() {
			  builder = new BuilderUsuario();
			  builder.setPassword("qwrpoqwirpqworiqpwori");
		  }
		  
		  @Test (expected = contraseniaSinNumeroException.class)
		  public void setearContraseniaSinNumero() {
			  builder = new BuilderUsuario();
			  builder.setPassword("QWRasdqwRWQsafG");
		  }
		  
		  @Test (expected = contraseniaSinMinusculaException.class)
		  public void setearContraseniaSinMinuscula() {
			  builder = new BuilderUsuario();
			  builder.setPassword("GASLKASJGLQPR25126");
		  }
		  
}
