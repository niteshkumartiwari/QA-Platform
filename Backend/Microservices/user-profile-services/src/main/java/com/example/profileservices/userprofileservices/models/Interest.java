package com.example.profileservices.userprofileservices.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "interest",schema = "public",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Interest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Short isDeleted;

    @OneToMany(mappedBy = "interest",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<QuestionInterest>  questionInterests;

    public Interest() {
        this.isDeleted=0;
    }

    public Interest(Long id, String name, Short isDeleted) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
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

    public Short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Short isDeleted) {
        this.isDeleted = isDeleted;
    }
}
