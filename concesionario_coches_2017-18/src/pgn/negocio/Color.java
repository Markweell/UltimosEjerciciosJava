package pgn.negocio;

public enum Color {
	PLATA,ROJO,AZUL;
	
	public static String[] toArray() {
		String[] colores = new String[Color.values().length];
		int i=0;
		for(Color color : Color.values()) {
			colores[i++]=color.toString();
		}
		return colores;
	}
}
