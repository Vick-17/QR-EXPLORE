package com.projectspring.api.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.projectspring.api.generic.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class User extends BaseEntity {

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;


  @ManyToMany
  @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_role"), inverseJoinColumns = @JoinColumn(name = "id_user"))
  private Collection<Role> roles = new ArrayList<>(); // ⚠️ Évite le NullPointerException

  @ManyToMany
  @JoinTable(name = "recording", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_place"))
  private Set<Place> recording = new HashSet<>();
<<<<<<< HEAD:api/src/main/java/com/projectspring/api/Entities/User.java

  @ManyToMany
  @JoinTable(name = "toLater", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_place"))
  private Set<Place> toLater = new HashSet<>();
=======
>>>>>>> origin/feat-CREATE-QRCODE:api/src/main/java/com/projectspring/api/entities/User.java

}
