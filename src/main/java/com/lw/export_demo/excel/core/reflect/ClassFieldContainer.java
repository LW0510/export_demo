package com.lw.export_demo.excel.core.reflect;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * created by liuwei
 * date 2019/06/02
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClassFieldContainer {

    Class<?> clazz;

    List<Field> declaredFields = new ArrayList<>();

    Map<String, Field> fieldMap = new HashMap<>();

    ClassFieldContainer parent;

    public Field getFieldByName(String fieldName) {
        return this.getFieldByName(fieldName, this);
    }

    public List<Field> getFieldsByAnnotation(Class<? extends Annotation> annotationClass) {
        Objects.requireNonNull(annotationClass);
        List<Field> annotationFields = new ArrayList<>();
        this.getFieldsByAnnotation(this, annotationClass, annotationFields);
        return annotationFields;
    }

    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        this.getFieldsByContainer(this, fields);
        return fields;
    }

    private void getFieldsByContainer(ClassFieldContainer classFieldContainer, List<Field> fields) {
        fields.addAll(classFieldContainer.getDeclaredFields());
        ClassFieldContainer parentContainer = classFieldContainer.getParent();
        if (Objects.isNull(parentContainer)) {
            return;
        }
        this.getFieldsByContainer(parentContainer, fields);
    }

    private void getFieldsByAnnotation(ClassFieldContainer classFieldContainer, Class<? extends Annotation> annotationClass, List<Field> annotationFieldContainer) {
        List<Field> annotationFields = classFieldContainer.declaredFields.stream().filter(field -> field.isAnnotationPresent(annotationClass)).collect(Collectors.toList());
        annotationFieldContainer.addAll(annotationFields);
        ClassFieldContainer parentContainer = classFieldContainer.getParent();
        if (Objects.isNull(parentContainer)) {
            return;
        }
        this.getFieldsByAnnotation(parentContainer, annotationClass, annotationFieldContainer);
    }

    private Field getFieldByName(String fieldName, ClassFieldContainer container) {
        Field field = container.getFieldMap().get(fieldName);
        if (Objects.nonNull(field)) {
            return field;
        }
        ClassFieldContainer parentContainer = container.getParent();
        if (Objects.isNull(parentContainer)) {
            return null;
        }
        return getFieldByName(fieldName, parentContainer);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public List<Field> getDeclaredFields() {
        return declaredFields;
    }

//    public void setDeclaredFields(List<Field> declaredFields) {
//        this.declaredFields = declaredFields;
//    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

//    public void setFieldMap(Map<String, Field> fieldMap) {
//        this.fieldMap = fieldMap;
//    }

    public ClassFieldContainer getParent() {
        return parent;
    }

    public void setParent(ClassFieldContainer parent) {
        this.parent = parent;
    }
}
