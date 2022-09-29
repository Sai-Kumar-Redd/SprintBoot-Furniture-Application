package com.furniture.model;


import java.util.List;

import lombok.Value;

@Value
public class UserInfo {
    private String id, username, email;
    private List<String> roles;
    private Cart cart;

}