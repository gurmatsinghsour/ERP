package com.degenCoders.loliSimpErp.Entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "department_id")
    private Long departmentId;
    
    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;
    
    @Column(name = "code", unique = true, nullable = false, length = 10)
    private String code;

    public Long getDepartmentId() {
        return this.departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
