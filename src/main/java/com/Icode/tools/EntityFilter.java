package com.Icode.tools;

import com.Icode.entity.Entity;

public class EntityFilter implements ClassFilter{

	public boolean accept(Class clazz) {
		return clazz.getSuperclass().equals(Entity.class);
	}

}
