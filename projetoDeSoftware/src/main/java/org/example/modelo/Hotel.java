package org.example.modelo;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "hotel")
@SequenceGenerator(name = "Sequencia",sequenceName = "SEQ_HOTEL",allocationSize = 1)
public class Hotel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_")
    private Long id;

    public String printHotel(){
        return "id: " + this.id.toString()+"\n"+"nome:"+this.nome+"\n"+"endereço: "+this.endereco+"\n"+"cep: "+this.cep+"\n"+"estrelas:"+String.valueOf(this.estrelas)+"\n"+"diaria"+String.valueOf(this.diaria);
    }

    public  Hotel(){

    }

    public Hotel(Long id_, String nome, String endereco, String cep, int estrelas, double diaria) {
        this.id = id_;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.estrelas = estrelas;
        this.diaria = diaria;
    }
    public Hotel(String nome, String endereco, String cep, int estrelas, double diaria) {
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.estrelas = estrelas;
        this.diaria = diaria;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    @Column(name = "nome")
    String nome;
    @Column(name = "endereco")
    String endereco;
    @Column(name = "cep")
    String cep;
    @Column(name = "estrelas")
    int estrelas;
    @Column(name = "diaria")
    double diaria;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
