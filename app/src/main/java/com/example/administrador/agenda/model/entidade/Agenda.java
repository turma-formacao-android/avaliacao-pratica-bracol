package com.example.administrador.agenda.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Agenda implements Parcelable {

    //@JsonIgnore
    private String tipoDeLogradouro;

    //@JsonIgnore
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    private Long _id;
    private String name;

    //@JsonProperty("cep")
    private String cep;

    //@JsonProperty("logradouro")
    private String rua;

    //@JsonProperty("bairro")
    private String bairro;

    //@JsonProperty("cidade")
    private String cidade;

    public String getTipoDeLogradouro() {
        return tipoDeLogradouro;
    }

    public void setTipoDeLogradouro(String tipoDeLogradouro) {
        this.tipoDeLogradouro = tipoDeLogradouro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agenda agenda = (Agenda) o;

        if (tipoDeLogradouro != null ? !tipoDeLogradouro.equals(agenda.tipoDeLogradouro) : agenda.tipoDeLogradouro != null)
            return false;
        if (estado != null ? !estado.equals(agenda.estado) : agenda.estado != null) return false;
        if (_id != null ? !_id.equals(agenda._id) : agenda._id != null) return false;
        if (name != null ? !name.equals(agenda.name) : agenda.name != null) return false;
        if (cep != null ? !cep.equals(agenda.cep) : agenda.cep != null) return false;
        if (rua != null ? !rua.equals(agenda.rua) : agenda.rua != null) return false;
        if (bairro != null ? !bairro.equals(agenda.bairro) : agenda.bairro != null) return false;
        return !(cidade != null ? !cidade.equals(agenda.cidade) : agenda.cidade != null);

    }

    @Override
    public int hashCode() {
        int result = tipoDeLogradouro != null ? tipoDeLogradouro.hashCode() : 0;
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "tipoDeLogradouro='" + tipoDeLogradouro + '\'' +
                ", estado='" + estado + '\'' +
                ", _id=" + _id +
                ", name='" + name + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tipoDeLogradouro);
        dest.writeString(this.estado);
        dest.writeValue(this._id);
        dest.writeString(this.name);
        dest.writeString(this.cep);
        dest.writeString(this.rua);
        dest.writeString(this.bairro);
        dest.writeString(this.cidade);
    }

    public Agenda() {
    }

    protected Agenda(Parcel in) {
        this.tipoDeLogradouro = in.readString();
        this.estado = in.readString();
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.cep = in.readString();
        this.rua = in.readString();
        this.bairro = in.readString();
        this.cidade = in.readString();
    }

    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        public Agenda createFromParcel(Parcel source) {
            return new Agenda(source);
        }

        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };
}
