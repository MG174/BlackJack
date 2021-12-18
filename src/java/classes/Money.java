package classes;

public class Money {

    String name = "Maciek";
    int money_amount = 0;

    public Money() {
    }

    public int money_minus(int amount) {
        money_amount -= amount;

        return money_amount;
    }

    public int money_plus(int amount) {
        money_amount += amount;

        return money_amount;
    }

    public String getName() {
        return name;
    }

    public int getMoney_amount() {
        return money_amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney_amount(int money_amount) {
        this.money_amount = money_amount;
    }

}
