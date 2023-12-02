public class Wizard extends Unit {
    private int mana = 100;
    public static int maxHealth = 80;


    @Override
    public String toString() {
        return "Wizard{" +
                "health=" + health +
                ", mana=" + mana +
                '}';
    }

    public Wizard() {
        this.health = 80;
        this.power = 30;
    }
    public void restoreMana() {
        mana+=10;
        if (mana > 100) {
            mana = 100;
        }
    }

    @Override
    public void attack(Unit damageDealer, Unit target) {
        if (this.isBuffed) {
            int restoredHealth = Math.min(this.power-target.defence,target.getHealth());
            this.heal(restoredHealth);
            if (this.health>maxHealth)
                this.health=maxHealth;
        }
        target.takeDamage(this.power, damageDealer);
        mana-=30;
    }

}

