package org.example.solve1;

public class User {

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @Min(18)
    @Max(120)
    private int age;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Valid
    private Address address;

    public User() {
    }

    public User(String name, int age, String email, Address address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
