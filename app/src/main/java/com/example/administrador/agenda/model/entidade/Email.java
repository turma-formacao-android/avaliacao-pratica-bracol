package com.example.administrador.agenda.model.entidade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 01/10/2015.
 */
public class Email implements Parcelable {
    private Long id;
    private Agenda agenda;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email1 = (Email) o;

        if (id != null ? !id.equals(email1.id) : email1.id != null) return false;
        if (agenda != null ? !agenda.equals(email1.agenda) : email1.agenda != null) return false;
        return !(email != null ? !email.equals(email1.email) : email1.email != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (agenda != null ? agenda.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", agenda=" + agenda +
                ", email='" + email + '\'' +
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
        dest.writeString(this.email);
    }

    public Email() {
    }

    protected Email(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
        this.email = in.readString();
    }

    public static final Creator<Email> CREATOR = new Creator<Email>() {
        public Email createFromParcel(Parcel source) {
            return new Email(source);
        }

        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
