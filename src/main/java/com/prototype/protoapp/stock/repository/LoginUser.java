package com.prototype.protoapp.stock.repository;

import java.util.List;

public record LoginUser(String email, String name, String password, List<String> rolelist) {

}
