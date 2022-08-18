package com.tyy.studio.fun.uml.bean;

public interface UMLVisibility {

    final static int PUBLIC = 0;

    final static int PROTECTED = 1;

    final static int PRIVATE = 2;

    final static int PACKAGE = 3;

    int getVisibility();

    default String getVisibilityChar() {
        switch (getVisibility()) {
            case PACKAGE :
                return " ";
            case PRIVATE :
                return "-";
            case PROTECTED :
                return "#";
            case PUBLIC :
                return "+";
            default :
                return "+";
        }
    }

}
