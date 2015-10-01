package com.example.administrador.agenda.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Agenda implements Parcelable {

    private Long _id;
    private String name;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private List<Telefone> listaTelefone;
    private List<Email> listaEmail;
    private List<Rede> listaRede;

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

    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }

    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    public List<Email> getListaEmail() {
        return listaEmail;
    }

    public void setListaEmail(List<Email> listaEmail) {
        this.listaEmail = listaEmail;
    }

    public List<Rede> getListaRede() {
        return listaRede;
    }

    public void setListaRede(List<Rede> listaRede) {
        this.listaRede = listaRede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agenda agenda = (Agenda) o;

        if (_id != null ? !_id.equals(agenda._id) : agenda._id != null) return false;
        if (name != null ? !name.equals(agenda.name) : agenda.name != null) return false;
        if (cep != null ? !cep.equals(agenda.cep) : agenda.cep != null) return false;
        if (rua != null ? !rua.equals(agenda.rua) : agenda.rua != null) return false;
        if (bairro != null ? !bairro.equals(agenda.bairro) : agenda.bairro != null) return false;
        if (cidade != null ? !cidade.equals(agenda.cidade) : agenda.cidade != null) return false;
        if (listaTelefone != null ? !listaTelefone.equals(agenda.listaTelefone) : agenda.listaTelefone != null)
            return false;
        if (listaEmail != null ? !listaEmail.equals(agenda.listaEmail) : agenda.listaEmail != null)
            return false;
        return !(listaRede != null ? !listaRede.equals(agenda.listaRede) : agenda.listaRede != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (listaTelefone != null ? listaTelefone.hashCode() : 0);
        result = 31 * result + (listaEmail != null ? listaEmail.hashCode() : 0);
        result = 31 * result + (listaRede != null ? listaRede.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", listaTelefone=" + listaTelefone +
                ", listaEmail=" + listaEmail +
                ", listaRede=" + listaRede +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.name);
        dest.writeString(this.cep);
        dest.writeString(this.rua);
        dest.writeString(this.bairro);
        dest.writeString(this.cidade);
        dest.writeList(this.listaTelefone);
        dest.writeList(this.listaEmail);
        dest.writeList(this.listaRede);
    }

    public Agenda() {
    }

    protected Agenda(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.cep = in.readString();
        this.rua = in.readString();
        this.bairro = in.readString();
        this.cidade = in.readString();
        this.listaTelefone = new ArrayList<Telefone>();
        in.readList(this.listaTelefone, List.class.getClassLoader());
        this.listaEmail = new ArrayList<Email>();
        in.readList(this.listaEmail, List.class.getClassLoader());
        this.listaRede = new ArrayList<Rede>();
        in.readList(this.listaRede, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Agenda> CREATOR = new Parcelable.Creator<Agenda>() {
        public Agenda createFromParcel(Parcel source) {
            return new Agenda(source);
        }

        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };
}
