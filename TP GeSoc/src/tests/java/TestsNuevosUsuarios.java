//package model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
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
			  builder.setPassword("qiehgyfiiyrt");
			  Assert.assertEquals("qiehgyfiiyrt",builder.getPassword());
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
		     builder.setPassword("qiehgyfiiyrt");
		     builder.setTipo(TipoUsuario.ADMIN);
		     builder.setUsername("Jorge");
		     usuario=builder.crearUsuario();
		     usuario.cambiarContrasenia("qiehgyfiiyrt");
		  }
		  
		  @Test (expected = contraseniaComunException.class)
		  public void setearContraseniaComun() {
			  builder = new BuilderUsuario();
			  builder.setPassword("password");
		  }


}
