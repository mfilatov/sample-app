package com.sample.app.model.db;


public class Contact {
    private String fullName;

    private String phoneNumber;

    public Contact() {
    }

    public Contact(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!fullName.equals(contact.fullName)) return false;
        return !(phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber != null);

    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }
}
