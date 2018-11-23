package pgn.negocio;

public enum Modelo {
	
	 	SERIE1(Marca.BMW),
	    SERIE2(Marca.BMW),
	    SERIE3(Marca.BMW),
	    SERIE4(Marca.BMW),
	    SERIE5(Marca.BMW),
	    CORDOBA(Marca.SEAT),
	    IBIZA(Marca.SEAT),
	    TOLEDO(Marca.SEAT);
	    
	    private Marca marca;
	    
	    private Modelo(Marca marca) {
	        this.marca = marca;
	    }
	    
	    public Marca getMarca(){
	        return marca;

	    }
	    
	    public static String[] toArray(){
	        String[] modelos = new String[Modelo.values().length];
	        int i = 0;
	        for(Modelo modelo : Modelo.values())
	            modelos[i++] = modelo.toString();
	        return modelos;
	    }
}
