package org.example.dao.impl;

import org.example.dao.HotelDao;
import org.example.excecao.HotelNaoEncontradoException;
import org.example.modelo.Hotel;
import org.example.util.FabricaDeEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import java.util.List;

public class jpaHotelDao implements HotelDao {

    @Override
    public long inclui(Hotel umHotel) throws HotelNaoEncontradoException {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(umHotel);
            tx.commit();
            return umHotel.getId();

        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void altera(Hotel umHotel) throws HotelNaoEncontradoException {
        EntityManager em = null;
        EntityTransaction tx = null;
        Hotel hotel = null;
        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            hotel = em.find(Hotel.class, umHotel.getId(), LockModeType.PESSIMISTIC_WRITE);

            if (hotel == null) {
                tx.rollback();
                throw new HotelNaoEncontradoException("Hotel não encontrado.");
            }
            // O merge entre nada e tudo é tudo. Ao tentar alterar um produto deletado ele será re-inserido
            // no banco de dados.
            em.merge(umHotel);

            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (RuntimeException ex) {
                }
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void exclui(long idHotel) throws HotelNaoEncontradoException {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            tx = em.getTransaction();
            tx.begin();

            Hotel hotel = em.find(Hotel.class, idHotel, LockModeType.PESSIMISTIC_WRITE);

            if (hotel == null) {
                tx.rollback();
                throw new HotelNaoEncontradoException("Hotel não encontrado.");
            }

            em.remove(hotel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Hotel recuperarUmHotel(long id) throws HotelNaoEncontradoException {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();
            Hotel umHotel = em.find(Hotel.class, id);

            // Características no método find():
            // 1. É genérico: não requer um cast.
            // 2. Retorna null caso a linha não seja encontrada no banco.

            if (umHotel == null) {
                throw new HotelNaoEncontradoException("Hotel não encontrado.");
            }
            return umHotel;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hotel> recuperaHoteis() {
        EntityManager em = null;

        try {
            em = FabricaDeEntityManager.criarEntityManager();

            List produtos = em
                    .createQuery("select p from Hotel p order by p.id")
                    .getResultList();

            // Retorna um List vazio caso a tabela correspondente esteja vazia.

            return produtos;
        } finally {
            em.close();
        }
    }
}
