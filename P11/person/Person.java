package person;

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object rhs) {
        if (this == rhs) return true;
        if (rhs == null) return false;
        if (this.getClass() != rhs.getClass()) return false;
        Person person = (Person) rhs;
        
        if (name.equals(person.name) == false || phone.equals(person.phone) == false)
            return false;
        else
            return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31*hash + (phone == null ? 0 : phone.hashCode());
        return hash;
    }

    protected String name;
    protected String phone;
}