package person;

import java.util.Objects;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Person {
    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person(BufferedReader in) throws IOException {
        name = in.readLine();
        phone = in.readLine();
    }

    public void save(BufferedWriter out) throws IOException {
        out.write(name + '\n');        
        out.write(phone + '\n');
    }

    public String name() {return name;}
    public String phone() {return phone;}

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object rhs) {
        if(this == rhs) return true;
        if(rhs == null) return false;
        if (this.getClass() != rhs.getClass()) return false;
        Person p = (Person) rhs;
        return name.equals(p.name) && phone.equals(p.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    protected String name;
    protected String phone;
}