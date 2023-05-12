package ru.hse.project.RasterTest.State;


import ru.hse.project.RasterTest.State.Math.Triangle3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс для хранения модели к локальной системе координат. Требует триангуляцию для дальнейшей работы
 */
public class Model {
    /**
     * Поле, хранящее объект в триангулированном виде
     */
    private List<Triangle3D> triangulation;

    /**
     * Дефолтный конструктор, инициализация пустой коллекцией
     */
    public Model() {
        triangulation = new ArrayList<>();
    }

    /**
     * Дип-копи-конструктор
     * @param other другая модель
     */
    public Model(Model other) {
        this();
        Collections.copy(this.getTriangulation(), other.getTriangulation());
    }

    public void setTriangulation(List<Triangle3D> triangulation) {
        this.triangulation = triangulation;
    }

    public List<Triangle3D> getTriangulation() {
        return triangulation;
    }
}
