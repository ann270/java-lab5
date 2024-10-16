/*
 * Номер залікової книжки: 19
 * C13 (остача від ділення на 13): 6
 * Завдання: Визначити ієрархію амуніції лицаря. Екіпірувати лицаря. Порахувати
 * вартість амуніції. Провести сортування амуніції за вагою. Знайти елементи
 * амуніції, що відповідає заданому діапазону цін.
 */

// Абстрактний клас для амуніції
abstract class Equipment {
    private String name;
    private double weight; // вага в кілограмах
    private double price; // ціна в одиницях валюти

    public Equipment(String name, double weight, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: вага = %.2f кг, цiна = %.2f", name, weight, price);
    }
}

// Клас для броні
class Armor extends Equipment {
    public Armor(String name, double weight, double price) {
        super(name, weight, price);
    }
}

// Клас для шолома
class Helmet extends Equipment {
    public Helmet(String name, double weight, double price) {
        super(name, weight, price);
    }
}

// Клас для щита
class Shield extends Equipment {
    public Shield(String name, double weight, double price) {
        super(name, weight, price);
    }
}

// Клас для управління амуніцією
class KnightEquipmentManager {
    private Equipment[] equipmentArray;
    private int count;

    public KnightEquipmentManager(int size) {
        equipmentArray = new Equipment[size];
        count = 0;
    }

    public void addEquipment(Equipment equipment) throws Exception {
        if (count < equipmentArray.length) {
            equipmentArray[count++] = equipment;
        } else {
            throw new Exception("Перевищено максимальний розмiр масиву амунiцiї.");
        }
    }

    public double calculateTotalPrice() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += equipmentArray[i].getPrice();
        }
        return total;
    }

    public void sortByWeight() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (equipmentArray[j].getWeight() > equipmentArray[j + 1].getWeight()) {
                    Equipment temp = equipmentArray[j];
                    equipmentArray[j] = equipmentArray[j + 1];
                    equipmentArray[j + 1] = temp;
                }
            }
        }
    }

    public void findEquipmentInPriceRange(double minPrice, double maxPrice) {
        System.out.println("Амунiцiя в дiапазонi цiн вiд " + minPrice + " до " + maxPrice + ":");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (equipmentArray[i].getPrice() >= minPrice && equipmentArray[i].getPrice() <= maxPrice) {
                System.out.println(equipmentArray[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Не знайдено жодної амунiцiї в заданому дiапазонi цiн.");
        }
    }

    public void printEquipment() {
        System.out.println("Вся амунiцiя лицаря:");
        for (int i = 0; i < count; i++) {
            System.out.println(equipmentArray[i]);
        }
    }
}

// Основний клас з виконавчим методом
public class KnightArmor {
    public static void main(String[] args) {
        try {
            KnightEquipmentManager manager = new KnightEquipmentManager(10);

            // Додавання амуніції
            manager.addEquipment(new Armor("Кольчужна броня", 12.5, 150));
            manager.addEquipment(new Helmet("Шолом лицаря", 3.0, 75));
            manager.addEquipment(new Shield("Дерев'яний щит", 5.0, 30));
            manager.addEquipment(new Armor("Латна броня", 15.0, 300));
            manager.addEquipment(new Helmet("Шолом зi сталi", 4.0, 100));

            // Вивід всієї амуніції
            manager.printEquipment();

            // Підрахунок загальної вартості амуніції
            double totalPrice = manager.calculateTotalPrice();
            System.out.printf("Загальна вартiсть амунiцiї: %.2f\n", totalPrice);

            // Сортування амуніції за вагою
            manager.sortByWeight();
            System.out.println("Амунiцiя пiсля сортування за вагою:");
            manager.printEquipment();

            // Пошук амуніції в заданому діапазоні цін
            manager.findEquipmentInPriceRange(50, 200);

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
