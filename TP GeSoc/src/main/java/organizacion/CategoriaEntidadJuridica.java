package organizacion;

public enum CategoriaEntidadJuridica {
	OSC, MICRO_EMPRESA, PEQUENIA_EMPRESA, EMPRESA_MEDIANA_TRAMO_1, EMPRESA_MEDIANA_TRAMO_2;

	public String getDisplayName() {
		return this.toString();
	}
}
	