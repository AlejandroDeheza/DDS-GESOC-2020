package repositorios;

import java.util.ArrayList;
import java.util.List;

import organizacion.*;

public class RepositorioEntidades {
	public List<EntidadBase> entidadesBase = new ArrayList<>();
	public List<EntidadJuridica> entidadesJuridicas = new ArrayList<>();
	
	private static final RepositorioEntidades INSTANCE = new RepositorioEntidades();
	
	public static final RepositorioEntidades instance() {
		return INSTANCE;
	}

}
