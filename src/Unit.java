//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Unit {
    protected String name;
    protected int health;
    protected int defence;
    protected int power;
    protected boolean isBuffed=false;
    public int getHealth() {
        return this.health;
    }

    public int getDefence() {
        return this.defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void attack(Unit damageDealer,Unit target) {
        target.takeDamage(this.power,damageDealer);
    }

    public String toString() {
        return "Unit{name='" + this.name + "', surname='" +  "', health=" + this.health + ", defence=" + this.defence + ", power=" + this.power + "}";
    }
    public void heal(int amount) {
        if (amount > 0) {
            this.health += amount;
        }
    }
    public void buff(){
        isBuffed=true;
    }
    public void takeDamage(int damage,Unit damageDealer) {
        this.health -= damage;
    }

    public void print() {
        System.out.println(this);
    }
    public void print(String str) {
        System.out.println(str + String.valueOf(this));
    }
}
