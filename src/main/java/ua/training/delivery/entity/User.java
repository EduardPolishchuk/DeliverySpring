package ua.training.delivery.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User {
    private Role role;
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String login;
    private BigDecimal balance;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && role == user.role && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && password.equals(user.password)
                && email.equals(user.email) && login.equals(user.login)
                && Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, id, firstName, lastName, password, email, login, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", balance=" + balance +
                '}';
    }



    public static class UserBuilder {
        private final User newUser;

        private UserBuilder() {
            this.newUser = new User();
        }

        public UserBuilder id(long id) {
            newUser.setId(id);
            return this;
        }

        public UserBuilder login(String login) {
            newUser.setLogin(login);
            return this;
        }

        public UserBuilder email(String email) {
            newUser.setEmail(email);
            return this;
        }

        public UserBuilder password(String password) {
            newUser.setPassword(password);
            return this;
        }

        public UserBuilder balance(BigDecimal balance) {
            newUser.setBalance(balance);
            return this;
        }

        public UserBuilder firstName(String firstName) {
            newUser.setFirstName(firstName);
            return this;
        }

        public UserBuilder lastName(String lastName) {
            newUser.setLastName(lastName);
            return this;
        }

        public UserBuilder role(Role role) {
            newUser.setRole(role);
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
