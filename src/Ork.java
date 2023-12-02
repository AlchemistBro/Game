public class Ork extends Unit {
    private int criticalChancePercentage=15;
    private float criticalMultiply=1.5f;
    private boolean checkForCriticalHit() {
        int randomNumber = (int) (Math.random() * 100) + 1;
        return randomNumber <= criticalChancePercentage;
    }
    public Ork() {
        this.health = 120;
        this.power = 15;
        this.defence=10;
    }

    @Override
    public String toString() {
        return "Ork{" + ", health=" + health +'}';
    }

    @Override
    public void attack(Unit damageDealer,Unit target) {
        if (checkForCriticalHit()){
            target.takeDamage((int) (power*criticalMultiply),damageDealer);
        }
        else super.attack(target,damageDealer);
    }

    @Override
    public void takeDamage(int damage, Unit damageDealer) {
        if (this.isBuffed)
            damageDealer.takeDamage(damage/2,this);
        super.takeDamage(damage-defence,damageDealer);
    }
}
