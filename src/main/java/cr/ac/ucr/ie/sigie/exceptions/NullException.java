package cr.ac.ucr.ie.sigie.exceptions;

public class NullException extends RuntimeException{
	private String message="";

	public NullException(String message) {
		super();
		if (message!=null) {
			this.message = message;

		}else {
			throw new RuntimeException("La Excepcion necesita un mensaje");
		}
	}

	@Override
	public String toString() {
		return message ;
	}
}