package com.dixon.krewrpg.entity;

import java.util.Comparator;

public class EntityUpComparator implements Comparator<Entity> {
	@Override
	public int compare(Entity e1, Entity e2) {
        return e2.compareTo(e1);
    }
}
