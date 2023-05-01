package org.example.excecao;

public class HotelNaoEncontradoException extends Exception {
    private final static long serialVersionUID = 1;

    private int codigo;

    public HotelNaoEncontradoException(String msg){
            super(msg);
    }

    public HotelNaoEncontradoException(int codigo,String msg){
        super(msg);
        this.codigo=codigo;
    }
    public int getCodigo(){
        return codigo;
    }
}
