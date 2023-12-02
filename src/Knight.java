public class Knight extends Unit {
    private int parryChance=20;
    private boolean checkForParry(Unit damageDealer) {
        if (!(damageDealer instanceof Wizard)) {
            int randomNumber = (int) (Math.random() * 100) + 1;
            return randomNumber <= parryChance;
        }
        else return false;
    }
    public Knight() {
        this.health = 100;
        this.power = 20;

    }



    @Override
    public String toString() {
        return "Knight{" + "health=" + health+'}';
    }

    @Override
    public void buff() {
        this.parryChance=50;
    }

    @Override
    public void takeDamage(int damage,Unit damageDealer) {
        if (checkForParry(damageDealer))
            attack(this,damageDealer);

        else
            super.takeDamage(damage,damageDealer);
    }

}

