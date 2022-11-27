package ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces;


public interface IAlteringModifier extends IModifier {

    /**
     * Modifier with edition allowed should have only one main field to be set with setValue
     * @param value new value for main field
     */
    void setValue(Object value);
}
