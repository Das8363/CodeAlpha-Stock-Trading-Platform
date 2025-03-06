import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " : $" + price;
    }
}

class Portfolio {
    private Map<String, Integer> holdings = new HashMap<>();
    private double balance;

    public Portfolio(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        if (holdings.containsKey(stock.getSymbol()) && holdings.get(stock.getSymbol()) >= quantity) {
            double revenue = stock.getPrice() * quantity;
            balance += revenue;
            holdings.put(stock.getSymbol(), holdings.get(stock.getSymbol()) - quantity);
            System.out.println("Sold " + quantity + " shares of " + stock.getSymbol());
        } else {
            System.out.println("Insufficient shares to sell!");
        }
    }

    public void showPortfolio() {
        System.out.println("Balance: $" + balance);
        System.out.println("Holdings:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " shares");
        }
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stock apple = new Stock("AAPL", 150.0);
        Stock google = new Stock("GOOG", 2800.0);
        Portfolio portfolio = new Portfolio(10000.0);

        while (true) {
            System.out.println("\n1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Stock Symbol (AAPL/GOOG): ");
                    String symbol = scanner.next();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    if (symbol.equalsIgnoreCase("AAPL")) {
                        portfolio.buyStock(apple, qty);
                    } else if (symbol.equalsIgnoreCase("GOOG")) {
                        portfolio.buyStock(google, qty);
                    } else {
                        System.out.println("Invalid Stock Symbol");
                    }
                    break;
                case 2:
                    System.out.print("Enter Stock Symbol (AAPL/GOOG): ");
                    symbol = scanner.next();
                    System.out.print("Enter Quantity: ");
                    qty = scanner.nextInt();
                    if (symbol.equalsIgnoreCase("AAPL")) {
                        portfolio.sellStock(apple, qty);
                    } else if (symbol.equalsIgnoreCase("GOOG")) {
                        portfolio.sellStock(google, qty);
                    } else {
                        System.out.println("Invalid Stock Symbol");
                    }
                    break;
                case 3:
                    portfolio.showPortfolio();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}

