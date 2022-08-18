package com.tyy.studio.fun.uml.bean;

public class AbsClassItem implements UMLVisibility {

    /**
     * 0 public 1 protected 2 private 3 package
     */
    protected int visibility = 0;

    /**
     * 功能描述
     */
    protected String description;

    /**
     * 中文名称
     */
    protected String showName;

    /**
     * 类名或属性名或方法名
     */
    protected String name;

    @Override
    public int getVisibility() {
        return visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

}
