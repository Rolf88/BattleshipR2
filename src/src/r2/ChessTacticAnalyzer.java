package r2;

public class ChessTacticAnalyzer {

    private int blacks = 0;
    private int whites = 0;

    public void register(int x, int y) {
        boolean isWhite = (y + x) % 2 == 0;

        if (isWhite) {
            whites++;
        } else {
            blacks++;
        }
    }

    private double getTotal() {
        return whites + blacks;
    }

    public String analyze() {
        double totals = getTotal();

        double blackies = blacks / totals;
        double whities = whites / totals;

        if (blackies > whities) {
            return "BLACK";
        }

        if (blackies < whities) {
            return "WHITE";
        }

        return "UNDEFINED";
    }
}
