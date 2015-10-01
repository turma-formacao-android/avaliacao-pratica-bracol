package com.example.administrador.agenda.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Rede implements Parcelable {

    private Long id;
    private Agenda agenda;
    private String rede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public String getRede() {
        return rede;
    }

    public void setRede(String rede) {
        this.rede = rede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rede rede1 = (Rede) o;

        if (id != null ? !id.equals(rede1.id) : rede1.id != null) return false;
        if (agenda != null ? !agenda.equals(rede1.agenda) : rede1.agenda != null) return false;
        return !(rede != null ? !rede.equals(rede1.rede) : rede1.rede != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (agenda != null ? agenda.hashCode() : 0);
        result = 31 * result + (rede != null ? rede.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rede{" +
                "id=" + id +
                ", agenda=" + agenda +
                ", rede='" + rede + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeParcelable(this.agenda, 0);
        dest.writeString(this.rede);
    }

    public Rede() {
    }

    protected Rede(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
        this.rede = in.readString();
    }

    public static final Creator<Rede> CREATOR = new Creator<Rede>() {
        public Rede createFromParcel(Parcel source) {
            return new Rede(source);
        }

        public Rede[] newArray(int size) {
            return new Rede[size];
        }
    };
}
