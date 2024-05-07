package Utils;

import java.util.Random;

/*
 * Utils.Direction - абстракция направления в системе координат "север-юг-восток-запад";
 * позволяет сравнивать направления и порождать новые направления относительно
 * текущего
 */
public class Direction {

    // определяем направление как угол в градусах от 0 до 360
    private int _angle = 90;

    private Direction(int angle) {
        // Приводим заданный угол к допустимому диапазону
        angle = angle % 360;
        if (angle < 0) angle += 360;

        this._angle = angle;
    }

    // ------------------ Возможные направления ---------------------

    public static Direction north() {
        return new Direction(90);
    }

    public static Direction south() {
        return new Direction(270);
    }

    public static Direction east() {
        return new Direction(0);
    }

    public static Direction west() {
        return new Direction(180);
    }

    public Direction getRandomDirection() {
        Random random = new Random();
        int randomIndex = random.nextInt(4); // 4 possible directions
        switch (randomIndex) {
            case 0:
                return north();
            case 1:
                return south();
            case 2:
                return east();
            case 3:
                return west();
            default:
                throw new IllegalStateException("Unexpected value: " + randomIndex);
        }
    }


    // ------------------ Новые направления ---------------------

    @Override
    public Direction clone() {
        return new Direction(this._angle);
    }

    public Direction clockwise() {
        return new Direction(this._angle - 90);
    }

    public Direction anticlockwise() {
        return new Direction(this._angle + 90);
    }

    public Direction opposite() {
        return new Direction(this._angle + 180);
    }

    public Direction rightword() {
        return clockwise();
    }

    public Direction leftword() {
        return anticlockwise();
    }

    // ------------------ Сравнить направления ---------------------

    @Override
    public boolean equals(Object other) {

        if (other instanceof Direction) {
            // Типы совместимы, можно провести преобразование
            Direction otherDirect = (Direction) other;
            // Возвращаем результат сравнения углов
            return _angle == otherDirect._angle;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this._angle;
    }

    public boolean isOpposite(Direction other) {
        return this.opposite().equals(other);
    }


}