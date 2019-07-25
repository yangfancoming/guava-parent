package com.google.common.goat.eventbus.item05;

/**
 * Created by 64274 on 2019/7/25.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/7/25---20:33
 */
public class Fruit {

    private final String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Fruit{" + "name='" + name + '\'' + '}';
    }
}
