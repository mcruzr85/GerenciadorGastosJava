package Exceptions;

public class MinorQueZeroException extends RuntimeException{
    private double valor;
    public MinorQueZeroException(){

    }
    public MinorQueZeroException(double valor, String message){
        super(message);
        this.valor = valor;
    }
    public MinorQueZeroException(String message){
       super(message);
    }
    public MinorQueZeroException(String message, Throwable cause){
        super(message, cause);
    }
    public MinorQueZeroException(Throwable cause){
        super(cause);
    }

    public double getValor() {
        return valor;
    }

    public String relatorio(){
        return "El valor que causó la excepcion fue: " + getValor();
    }
}
