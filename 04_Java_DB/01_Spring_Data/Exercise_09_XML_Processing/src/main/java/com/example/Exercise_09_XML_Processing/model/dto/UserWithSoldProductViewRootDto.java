package com.example.Exercise_09_XML_Processing.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductViewRootDto {
    @XmlElement(name = "user")
    private List<UserWithSoldProductViewDto> users;

    public List<UserWithSoldProductViewDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductViewDto> users) {
        this.users = users;
    }
}