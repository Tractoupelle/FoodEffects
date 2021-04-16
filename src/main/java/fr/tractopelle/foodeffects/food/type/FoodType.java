package fr.tractopelle.foodeffects.food.type;

public enum FoodType {

    FISH,
    COOKED_FISH,
    CAKE,
    COOKIE,
    MELON,
    COOKED_BEEF,
    PUMPKIN_PIE,
    BEEF,
    CHICKEN,
    COOKED_CHICKEN,
    ROTTEN_FLESH,
    CARROT,
    BAKED_POTATO,
    POISONOUS_POTATO,
    POTATO,
    RABBIT,
    COOKED_MUTTON,
    MUTTON,
    COOKED_RABBIT;

    public static boolean isFoodFromString(String food) {

        for (FoodType me : FoodType.values()) {
            if (me.name().equalsIgnoreCase(food)) {
                return true;
            }
        }

        return false;

    }
}
