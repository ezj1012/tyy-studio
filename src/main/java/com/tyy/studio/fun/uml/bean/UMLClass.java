package com.tyy.studio.fun.uml.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author XiongJian
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UMLClass extends AbsClassItem {

    private List<UMLClassAttribute> attributes = new ArrayList<UMLClassAttribute>();

    private List<UMLClassOperation> operations = new ArrayList<UMLClassOperation>();

    public void addAttribute(UMLClassAttribute attribute) {
        this.attributes.add(attribute);
    }

    public boolean removeAttribute(UMLClassAttribute attribute) {
        return this.attributes.remove(attribute);
    }

    public void addOperation(UMLClassOperation operation) {
        this.operations.add(operation);
    }

    public boolean removeOperation(UMLClassOperation operation) {
        return this.operations.remove(operation);
    }

}
