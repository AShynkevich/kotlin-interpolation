package com.example;

import org.example.com.example.Entity;
import org.example.com.example.KotlinInterface;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class JavaImpl implements KotlinInterface<Entity> {

    @Override
    public Entity findById(@NotNull UUID id) {
        return new Entity(id, "Some name");
    }
}
