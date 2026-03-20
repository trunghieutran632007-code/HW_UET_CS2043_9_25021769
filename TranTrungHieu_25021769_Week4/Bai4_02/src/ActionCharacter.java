package Bai4_02.src;

public class ActionCharacter {
    public void fight() {
        System.out.println("Dam boc...");
    }

}

class Hero extends ActionCharacter implements CanFly, CanSwim, CanFight {
    @Override
    public void fly() {
        System.out.println("Hero is flying");
    }

    @Override
    public void swim() {
        System.out.println("Hero is swimming");
    }


}
