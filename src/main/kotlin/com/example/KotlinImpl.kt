package org.example.com.example

class KotlinImpl: KotlinInterface<Entity> {

    override fun findById(id: EntityId): Entity {
        return Entity(id, "Some name")
    }
}