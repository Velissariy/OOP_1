package units;

import javax.lang.model.element.AnnotationValueVisitor;
import java.util.*;

    // Базовый класс персонажа
public abstract class Person implements InGameInterface{
    public int damage, hp, max_hp, initiative, moveDistance;
    public boolean isAlive;
    public String state;
    Coordinates coordinates;

    public Person(int x, int y,int hp,int max_hp,int damage,int moveDistance,
                  int initiative, boolean isAlive){
        this.damage = damage;
        this.hp = hp;
        this.max_hp = max_hp;
        this.state = "Stand";
        coordinates = new Coordinates(x, y);
        this.initiative = initiative;
        this.isAlive = isAlive;
        this.moveDistance = moveDistance;
    }
    public ArrayList<Integer> getCoords() {
            return coordinates.xy;
    }

    @Override
    public String toString() {
        return this.getInfo().split(" ")[0];
    }

    public void move(Coordinates targetPosition, ArrayList<Person> team) {
        if (!coordinates.containsByPos(coordinates.newPosition(targetPosition, team), team)) {
            for (int i = 0; i < moveDistance; i++) {
                coordinates = coordinates.newPosition(targetPosition, team);
            }
        }
    }
    public Person nearest(ArrayList<Person> units) {
        double minDistance = Double.MAX_VALUE;
        Person nearestEnemy = units.get(0);
        for (int i = 0; i < units.size(); i++) {
            if (coordinates.countDistance(units.get(i).coordinates)
                    < minDistance && units.get(i).isAlive) {
                nearestEnemy = units.get(i);
                minDistance = coordinates.countDistance(units.get(i).coordinates);
            }
        }
        return nearestEnemy;
    }

        public void getDamage(int damage) {
            hp -= damage;
            if (hp <= 0) {
                hp = 0;
                isAlive = false;
                state = "Dead";
            }
            if (hp > max_hp) hp = max_hp;
        }
}







