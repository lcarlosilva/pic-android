package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.model.persistence.MemoryClientRepository;
import com.example.administrador.myapplication.model.persistence.SQLiteClientRepositoy;

import java.util.List;

public class Client implements Parcelable {

    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String phone;

    public Client() {
    }

    public Client(Parcel parcel) {
        super();
        readToParcel(parcel);
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (age != null ? !age.equals(client.age) : client.age != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null)
            return false;
        return !(phone != null ? !phone.equals(client.phone) : client.phone != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    public void save() {
        SQLiteClientRepositoy.getInstance().save(this);
    }

    public static List<Client> getAll() {
        return SQLiteClientRepositoy.getInstance().getAll();
    }

    public static void delete(Client client) {
        SQLiteClientRepositoy.getInstance().delete(client);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name == null ? "" : name);
        dest.writeInt(age == null ? -1 : age.intValue());
        dest.writeString(phone == null ? "" : phone);
        dest.writeString(address == null ? "" : address);
    }

    private void readToParcel(Parcel parcel) {
        id = parcel.readLong();
        name = parcel.readString();
        age = parcel.readInt();
        if (age == -1) age = null;
        phone = parcel.readString();
        address = parcel.readString();
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {

        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };

}
