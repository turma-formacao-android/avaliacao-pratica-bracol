package com.example.administrador.agenda.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Telefone implements Parcelable {
    private Long id;
    private String name;
    private Agenda agenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telefone telefone = (Telefone) o;

        if (id != null ? !id.equals(telefone.id) : telefone.id != null) return false;
        if (name != null ? !name.equals(telefone.name) : telefone.name != null) return false;
        return !(agenda != null ? !agenda.equals(telefone.agenda) : telefone.agenda != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (agenda != null ? agenda.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", agenda=" + agenda +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.agenda, 0);
    }

    public Telefone() {
    }

    protected Telefone(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
    }

    public static final Creator<Telefone> CREATOR = new Creator<Telefone>() {
        public Telefone createFromParcel(Parcel source) {
            return new Telefone(source);
        }

        public Telefone[] newArray(int size) {
            return new Telefone[size];
        }
    };
}
