package org.sdl.mintyn_bank_test.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Authority() {

    }

    public Authority(String authorityName) {
        this.name = authorityName;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
