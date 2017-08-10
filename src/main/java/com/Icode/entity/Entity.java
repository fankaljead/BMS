package com.Icode.entity;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Icode on 2017/7/29.
 * descript:
 */

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;
    // 将传入的map设置为该类的属性值
    public void setProperties(Map<String, Object> properties) {
        // 获取当前类的所有字段
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            // 如果没有属性没有get方法，开始下一次循环
            try {
                // 将方法名第一个字母大写
                if (this.getClass().getDeclaredMethod("get" + fieldName.toUpperCase().charAt(0)
                        + fieldName.substring(1)) == null) {
                    continue;
                }
            } catch (NoSuchMethodException e1) {
                continue;
            }
            Object value = properties.get(fieldName.toUpperCase());

            // 如果field不是基本类型，则转化为对象
            if (value != null ) {
                if (!isBaseDataType(field.getType())){
                    try {
                        // 将value转变成字节数组
                        ByteArrayInputStream bi = new ByteArrayInputStream((byte[]) value);
                        ObjectInputStream oi = new ObjectInputStream(bi);
                        value = oi.readObject();
                        bi.close();
                        oi.close();
                    } catch (Exception e) {
                        System.out.println("translation" + e.getMessage());
                        e.printStackTrace();
                    }
                }
                try {
                    field.set(this, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Map<String,Object> getProperties() {
        return toMap();
    }

    public abstract String getTableName();

    public abstract String getPrimaryKey();

    public static <T extends Entity> String getTableName(Class<T> t) {
        try {
            return t.newInstance().getTableName();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends Entity> String getPrimaryKey(Class<T> t) {
        try {
            return t.newInstance().getPrimaryKey();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 判断是否是基本数据类型
    public static boolean isBaseDataType(@SuppressWarnings("rawtypes") Class clazz) {
        // isPrimitive()判定是否为基本类型
        if (clazz.isPrimitive() ||
                (clazz.getGenericSuperclass() != null && clazz.getGenericSuperclass().equals(Number.class))
                || clazz.equals(String.class)
                || clazz.equals(Date.class)) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param clazz
     * @return 获取数据库类型
     */
    public static String getSQlType(Class<?> clazz) {
        String type = clazz.getSimpleName();
        if ("int".equals(type)) {
            return "int";
        }
        if ("short".equals(type)){
            return "tinyint";
        }
        if ("float".equals(type)) {
            return "float";
        }

        if ("double".equals(type)) {
            return "double";
        }

        if ("String".equals(type)) {
            return "varchar(50)";
        }

        if ("Date".equals(type)) {
            return "datetime";
        }
        return "blob(0)";
    }

    public Map<String, Object> toMap() {
        Map<String, Object> temp = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            // 没有get方法的属性，不转为map
            try {
                if (this.getClass().getDeclaredMethod("get" + fieldName.toUpperCase().charAt(0) + fieldName.substring(1)) == null) {
                    continue;
                }
            } catch (NoSuchMethodException e1) {
                continue;
            }
            Object value = null;
            try {
                value = field.get(this);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (value != null) {
                temp.put(fieldName, value);
            }
        }
        return temp;
    }

    private void writeObject(ObjectOutputStream os) throws IOException {
        os.defaultWriteObject();//java对象序列化默认操作
        os.writeObject(toMap());
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();//java对象反序列化默认操作
        Map<String, Object> temp = (Map<String, Object>) is.readObject();
        setProperties(temp);
    }
}

