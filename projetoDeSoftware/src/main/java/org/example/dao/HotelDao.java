package org.example.dao;

import org.example.excecao.HotelNaoEncontradoException;
import org.example.modelo.Hotel;

import java.util.List;

public interface HotelDao {
    long inclui(Hotel umHotel)throws HotelNaoEncontradoException;
    void altera(Hotel umHotel) throws HotelNaoEncontradoException;
    void exclui(long idHotel) throws HotelNaoEncontradoException;

    Hotel recuperarUmHotel(long id) throws HotelNaoEncontradoException;

    List<Hotel> recuperaHoteis();
}
