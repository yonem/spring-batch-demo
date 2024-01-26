package jp.ne.yonem.common.entity;

import lombok.Data;

@Data
public class Worker {

    public Worker() {
    }

    public Worker(String name, int isValid) {
        this.name = name;
        this.isValid = isValid;
    }

    private Long id;
    private String name;
    private int isValid;
}
