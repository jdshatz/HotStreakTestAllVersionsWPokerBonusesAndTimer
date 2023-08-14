package demo;

//EDIT THIS

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MultiLineGameAllVersions {
    private final MultiLinePlayer player;
    private int numberOfLines = 2; //Take as parameter when testing/playing a game
    private int cardNumber = -1;
    private Line[] lines;
    private int linesStillGoing;
    protected String[] guesses;
    private int streaksOfFour = 0;
    private int credits;
    private int flushes = 0;
    private HashMap<Integer, Integer> payouts;
    private boolean tiesWin;
    private final HotStreakGui gui;
    private boolean waiting = true;
    private boolean guessIsMade = false;
    private boolean guessIsHigher = false;
    private String gameString;
    private final Payouts setUpPayouts;

    private HashMap<Integer, Integer> columnPairs;
    private HashMap<Integer, Integer> exactPairs;
    private HashMap<Integer, Integer> finalCards;
    private HashMap<Integer, Integer> twoFlush;

    private final ColumnPairPayouts columnPairPayouts;
    private final ExactPairPayouts exactPairPayouts;
    private final FinalCard2APayouts finalCard2APayouts;
    private final TwoFlushPayouts twoFlushPayouts;

    private boolean canReplace = false;
    private boolean activatePokerBonus = false;
    private boolean onlyStartingCardReplace = false;
    private boolean replaceCall = false;

    public boolean isEndsWithAce() {
        return endsWithAce;
    }

    public void setEndsWithAce(boolean endsWithAce) {
        this.endsWithAce = endsWithAce;
    }

    public boolean isExactPair() {
        return exactPair;
    }

    public void setExactPair(boolean exactPair) {
        this.exactPair = exactPair;
    }

    public boolean isColumnPair() {
        return columnPair;
    }

    public void setColumnPair(boolean columnPair) {
        this.columnPair = columnPair;
    }

    private boolean endsWithAce = false;
    private boolean exactPair = false;
    private boolean columnPair = false;

    public MultiLineGameAllVersions(MultiLinePlayer player) throws IOException {
        this.player = player;
        this.credits = 1;
        this.tiesWin = false;
        this.gameString = "Basic Ties Lose";
        this.setUpPayouts = new Payouts();
        this.columnPairPayouts = new ColumnPairPayouts();
        this.exactPairPayouts = new ExactPairPayouts();
        this.finalCard2APayouts = new FinalCard2APayouts();
        this.twoFlushPayouts = new TwoFlushPayouts();
        this.payouts = setPayouts(2);
        this.columnPairs = setColumnPairs(2);
        this.exactPairs = setExactPairs(2);
        this.finalCards = setFinalCards(2);
        this.twoFlush = setTwoFlush(2);
        this.gui = new HotStreakGui();
        gui.tiesLoseBasic.setEnabled(false);
    }

    public String getGameString() {
        return gameString;
    }

    public void setGameString(String gameString) {
        this.gameString = gameString;
    }

    public void setTiesWin(boolean tiesWin) {
        this.tiesWin = tiesWin;
    }

    private HashMap<Integer, Integer> setPayouts(int lines) {
        return setUpPayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setColumnPairs(int lines) {
        return columnPairPayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setExactPairs(int lines) {
        return exactPairPayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setFinalCards(int lines) {
        return finalCard2APayouts.setNewPayouts(getGameString(), lines);
    }

    public HashMap<Integer, Integer> setTwoFlush (int lines) {
        return twoFlushPayouts.setNewPayouts(getGameString(), lines);
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getNumberOfLinesStillGoing() {
        return linesStillGoing;
    }

    public void setNumberOfLinesStillGoing(int numberOfLines) {
        this.linesStillGoing = numberOfLines;
    }


    public void setUpLines() {
        this.lines = new Line[numberOfLines];
        this.guesses = new String[numberOfLines];
        for (int index = 0; index < this.numberOfLines; index++) {
            lines[index] = new Line(); //Instantiate lines in array
            lines[index].setLineNumber(index);
        }
        this.payouts = setPayouts(numberOfLines);
        this.columnPairs = setColumnPairs(numberOfLines);
        this.exactPairs = setExactPairs(numberOfLines);
        this.finalCards = setFinalCards(numberOfLines);
        this.twoFlush = setTwoFlush(numberOfLines);
    }

    public int getStreaksOfFour() {
        return streaksOfFour;
    }

    public int getFlushes() {
        return flushes;
    }

    public void setFlushes(int flushes) {this.flushes = flushes;}

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGuessIsMade(boolean guessIsMade) {
        this.guessIsMade = guessIsMade;
    }

    public void setGuessIsHigher(boolean guessIsHigher) {
        this.guessIsHigher = guessIsHigher;
    }

    public boolean isCanReplace() {
        return canReplace;
    }

    public void setCanReplace(boolean canReplace) {
        this.canReplace = canReplace;
    }

    public boolean isActivatePokerBonus() {
        return activatePokerBonus;
    }

    public void setActivatePokerBonus(boolean activatePokerBonus) {
        this.activatePokerBonus = activatePokerBonus;
    }

    public void setOnlyStartingCardReplace(boolean canReplace) {
        this.onlyStartingCardReplace = canReplace;
    }

    public void setReplaceCall(boolean replace) {
        this.replaceCall = replace;
    }

    public HashMap<Integer, Integer> getPayouts() {
        return payouts;
    }

    public HashMap<Integer, Integer> getColumnPairs() {
        return columnPairs;
    }

    public HashMap<Integer, Integer> getExactPairs() {
        return exactPairs;
    }

    public HashMap<Integer, Integer> getFinalCards() {
        return finalCards;
    }

    public HashMap<Integer, Integer> getTwoFlush() {
        return twoFlush;
    }

    public boolean atLeastOneLineIsStillGoing() {
        for (Line line : lines) {
            if (line.isStillGoing())
                return true;
        }
        return false;
    }

    public void askForGuess(int cardNumber) throws IOException, InterruptedException {
        int autowins = 0;
        int stillGoing = 0;
        for (Line line : lines) {
            if (line.isAutoWin())
                autowins++;
            else if (line.isStillGoing())
                stillGoing++;
        }
        System.out.println("Autowins " + autowins);
        if (stillGoing == 0 && autowins > 0) {
            System.out.println("No need to guess this time. You AUTOWIN!");
            for (Line line : lines) {
                if (line.isStillGoing()) {
                    gui.setAutowin(line.getLineNumber(), cardNumber + 1);
                    TimeUnit.MILLISECONDS.sleep(250);
                }
            }
            havePlayerGuessDownColumn(cardNumber, "autowin");
        } else {
            if (autowins > 0) {
                for (Line line : lines) {
                    if (line.isAutoWin()) {
                        gui.setAutowin(line.getLineNumber(), cardNumber + 1);
                    }
                }
            }
            System.out.println("Higher or lower?");
            while (!guessIsMade)
                gui.board.revalidate();
            if (replaceCall) {
                System.out.println("REPLACE");
                if (cardNumber == 0) {
                    Card newNewCard = lines[0].drawFirstCard();
                    for (int index = 1; index < this.numberOfLines; index++) {
                        boolean removed = lines[index].removeFirstCard(newNewCard);
                        if (!removed) {
                            System.out.println("CARD NOT REMOVED!");
                            System.out.println("We'll have to restart the game.");
                            return;
                        }
                    }
                    gui.drawFirstCard(newNewCard);
                } else if (canReplace) {
                    havePlayerGuessDownColumn(cardNumber, "replace");
                }
                System.out.println();
                System.out.println();
                printLines();
                System.out.println();
                System.out.println();
                setReplaceCall(false);
                setGuessIsMade(false);
                askForGuess(cardNumber);
            } else if (guessIsHigher) {
                havePlayerGuessDownColumn(cardNumber, "higher");
                setGuessIsHigher(false);
            } else
                havePlayerGuessDownColumn(cardNumber, "lower");
            setGuessIsMade(false);
        }
        if (onlyStartingCardReplace) {
            setCanReplace(false);
            gui.replaceButton.setVisible(false);
            gui.replaceButton.setVisible(false);
        }
    }

    public void guessFirstCards() throws IOException, InterruptedException {
        Line thisLine = lines[0];
        Card thisCard = thisLine.getCurrentCard();
        if (thisCard.isTwo() || thisCard.isAce()) {
            System.out.println("AUTOWIN for the column.");
            for (Line line : lines)
                line.setOnAutoWin(true);
            for (int index = 1; index < numberOfLines; index++)
                guesses[index] = "AUTOWIN";
            havePlayerGuessDownColumn(0, "autowin");
        } else {
            askForGuess(0);
        }
    }

    public void guessCorrect(Line line, Card thisCard, Card nextCard) {
        if (cardNumber < 3 && (line.getNextCard().isAce() || line.getNextCard().isTwo()))
            gui.setAutowin(line.getLineNumber(), cardNumber + 2);
        printLineWithCard(line, thisCard, nextCard);
        continueLine(line);
    }

    public void guessWrong(Line line, Card thisCard, Card nextCard) throws IOException {
        line.setLost(true);
        printLineWithCard(line, thisCard, nextCard);
        gui.setInactive(line, line.getLineNumber(), cardNumber + 1);
        endLine(line);
    }

    public void guessLower(Line line) throws IOException {
        Card thisCard = line.getCurrentCard();
        Card nextCard = line.getNextCard();
        if ((thisCard.isGreaterThan(nextCard)) || (thisCard.sameValueAs(nextCard) && tiesWin))
            guessCorrect(line, thisCard, nextCard);
        else
            guessWrong(line, thisCard, nextCard);
    }

    public void guessHigher(Line line) throws IOException {
        Card thisCard = line.getCurrentCard();
        Card nextCard = line.getNextCard();
        if (thisCard.isLessThan(nextCard) || (thisCard.sameValueAs(nextCard) && tiesWin))
            guessCorrect(line, thisCard, nextCard);
        else
            guessWrong(line, thisCard, nextCard);
    }

    public void handleAutoWin(Line line) {
        this.continueLine(line);
    }

    public void havePlayerGuessDownColumn(int cardNumber, String higherOrLower) throws IOException, InterruptedException {
        for (int lineNumber = 0; lineNumber < numberOfLines; lineNumber++) {
            Line line = lines[lineNumber];
            if (line.isStillGoing()) {
                Card newCard = line.drawNextCard();
                if (cardNumber == 0 && (line.getCurrentCard().isAce() || line.getCurrentCard().isTwo())) {
                    System.out.println("Autowin first column");
                    gui.setAutowin(line.getLineNumber(), cardNumber + 1);
                    TimeUnit.MILLISECONDS.sleep(250);
                    if (onlyStartingCardReplace && canReplace) {
                        setCanReplace(false);
                        gui.replaceButton.setVisible(false);
                        gui.replaceButton.setVisible(false);
                    }
                }
                if (higherOrLower.equals("replace")) {
                    if (!(line.isAutoWin())) {
                        line.setCurrentCard(newCard);
                        gui.dealCard(newCard, lineNumber, cardNumber);
                    }
                    if (newCard.isAce() || newCard.isTwo()) {
                        line.setToAutoWin();
                        gui.setAutowin(lineNumber, cardNumber + 1);
                    }
                } else {
                    gui.dealCard(newCard, lineNumber, cardNumber + 1);
                    if (line.isAutoWin()) {
                        printLineWithCard(line, line.getCurrentCard(), line.getNextCard());
                        this.handleAutoWin(line);
                    } else {
                        if (higherOrLower.equals("higher"))
                            guessHigher(line);
                        else if (higherOrLower.equals("lower"))
                            guessLower(line);
                    }
                }
            }
        }
        System.out.println();
        System.out.println("----------------");
        System.out.println();
    }

    public void continueLine(Line line) {
        line.continueToNextCard();
        if (line.getStreak() > 3) {
            System.out.println("You win on this line.");
            endLine(line);
        }
        if (line.isOnAutoWin())
            line.setOnAutoWin(false);
    }

    public void endLine(Line line) {
        line.setStillGoing(false);
    }

    public void addCredits(MultiLinePlayer p) {
        System.out.println();
        int prize;
        if (getFlushes() == 2 && isActivatePokerBonus()) {
            prize = this.getTwoFlush().get(2) * this.getCredits();
        }
        else if (isExactPair() && isActivatePokerBonus())
            prize = this.getExactPairs().get(this.getStreaksOfFour()) * this.getCredits();
        else if (isColumnPair() && isActivatePokerBonus())
            prize = this.getColumnPairs().get(this.getStreaksOfFour()) * this.getCredits();
        else if (isEndsWithAce() && getStreaksOfFour() == 1 && isActivatePokerBonus())
            prize = this.getFinalCards().get(this.getStreaksOfFour()) * this.getCredits();
        else
            prize = this.getCredits() * this.getPayouts().get(this.getStreaksOfFour());
        p.addCredits(prize);
        gui.resultLabel1.setFont(new Font("Century Schoolbook", Font.ITALIC, 35));
        gui.resultLabel1.setVisible(true);
        if (this.getStreaksOfFour() == 0) {
            System.out.println("You are a total loser.");
            gui.resultLabel1.setBackground(Color.red.brighter());
            gui.resultLabel1.setText("GAME OVER");
        } else {
            gui.resultLabel1.setBackground(Color.green.brighter());
            gui.resultLabel2.setBackground(Color.green.brighter());
            if (this.getStreaksOfFour() == 1) {
                System.out.printf("You completed 1 streak, earning %d credits.%n", prize);
            } else {
                System.out.printf("You completed %d streaks, earning %d credits.%n", getStreaksOfFour(), prize);
            }
            if (isActivatePokerBonus() && (flushes > 0 || isExactPair() || isColumnPair() || (isEndsWithAce() && getStreaksOfFour() == 1))) {
                gui.resultLabel0.setFont(new Font("Century Schoolbook", Font.ITALIC, 35));
                gui.resultLabel0.setBackground(Color.yellow);
                gui.resultLabel0.setForeground(Color.black);
                gui.resultLabel0.setVisible(true);
                if (flushes > 1) {
                    gui.resultLabel0.setText(" + " + flushes + "-FLUSH BONUS!");
                }
                else if (isExactPair())
                    gui.resultLabel0.setText(" + FINAL EXACT PAIR BONUS!");
                else if (isColumnPair())
                    gui.resultLabel0.setText(" + FINAL PAIR BONUS!");
                else if (isEndsWithAce())
                    gui.resultLabel0.setText(" + FINAL A/2 BONUS!");
            }
            else {
                gui.resultLabel0.setVisible(false);
            }
            gui.resultLabel1.setText("YOU WIN");
            gui.resultLabel2.setVisible(true);
            gui.resultLabel2.setFont(new Font("Century Schoolbook", Font.ITALIC, 35));
            gui.resultLabel2.setText(NumberFormat.getIntegerInstance().format(prize) + " CREDITS!");
            setFlushes(0);
            setExactPair(false);
            setColumnPair(false);
            setEndsWithAce(false);
        }
        System.out.printf("You end this game with %d credits.%n", p.getCredits());
        gui.creditButton.setText("Credits: " + p.getCredits());
    }

    public void printLine(Line line) {
        StringBuilder sb = new StringBuilder();
        for (Card card : line.getThisDeck().getDrawnCards()) {
            sb.append(String.format("%s%s ", card.getRank(), card.getSuit()));
        }
        sb.append(String.format("%s%s ", line.getCurrentCard().getRank(), line.getCurrentCard().getSuit()));
        if (line.isAutoWin())
            sb.append("AUTOWIN!");
        if (!line.isStillGoing())
            sb.append("X");
        System.out.println(sb);
    }

    public void printLineWithCard(Line line, Card thisCard, Card nextCard) {
        StringBuilder sb = new StringBuilder();
        for (Card card : line.getThisDeck().getDrawnCards()) {
            sb.append(String.format("%s%s ", card.getRank(), card.getSuit()));
        }
        sb.append(String.format("%s%s ", thisCard.getRank(), thisCard.getSuit()));
        if (line.isAutoWin())
            sb.append("AUTOWIN!");
        else {
            sb.append(String.format("%s%s ", nextCard.getRank(), nextCard.getSuit()));
            if (line.isLost())
                sb.append("LOSE.");
        }
        System.out.println(sb);
        System.out.println();
    }


    public void printLines() {
        System.out.println();
        System.out.flush();
        for (int index = 0; index < this.numberOfLines; index++) {
            printLine(lines[index]);
        }
        System.out.println();
    }

    public void play(MultiLinePlayer p) throws IOException, InterruptedException {
        while (true) {
            while (waiting)
                gui.board.revalidate();
            Stopwatch stopwatch = new Stopwatch();
            System.out.flush();
            System.out.println();
            System.out.println("Welcome to your HOT STREAK game!");
            cardNumber = 0;
            try {
                Card firstCard = lines[0].drawFirstCard();
                for (int index = 1; index < this.numberOfLines; index++) {
                    boolean removed = lines[index].removeFirstCard(firstCard);
                    if (!removed) {
                        System.out.println("CARD NOT REMOVED!");
                        System.out.println("We'll have to restart the game.");
                        return;
                    }
                }
                System.out.printf("You had %d credits and paid %d credits total for %d lines,%n", p.getCredits(), this.getCredits() * numberOfLines, this.getNumberOfLines());
                p.addCredits(this.getCredits() * numberOfLines * (-1));
                System.out.println("so you start the game with " + p.getCredits() + " credits.");
                gui.creditButton.setText("Credits: " + p.getCredits());
                System.out.println();
                gui.drawFirstCard(firstCard);
                boolean stillGoing = atLeastOneLineIsStillGoing();
                while (stillGoing & cardNumber < 5) {
                    guesses = new String[numberOfLines];
                    System.out.println();
                    printLines();
                    System.out.println();
                    if (cardNumber == 0) {
                        guessFirstCards();
                    } else {
                        askForGuess(cardNumber);
                    }
                    if (atLeastOneLineIsStillGoing()) {
                        cardNumber++;
                    } else
                        break;
                }
                gui.stopWatchLabel.setText("Time: " + stopwatch.elapsedTime() + " seconds");
                System.out.println("Stopwatch ends.  Elapsed time: " + stopwatch.elapsedTime());
                for (Line thisLine : lines) {
                    System.out.println("final card is " + thisLine.getCurrentCard().printCard());
                    if (thisLine.getStreak() > p.getHotStreak())
                        p.setHotStreak(thisLine.getStreak());
                    if (thisLine.getStreak() == 4){
                        streaksOfFour++;
                        if (thisLine.getCurrentCard().isAce() || thisLine.getCurrentCard().isTwo())
                            setEndsWithAce(true);
                        if (thisLine.isFlush())
                            flushes++;
                    }
                    p.setStreak(thisLine.getStreak());
                }
                System.out.println();
                System.out.println("Game ends with the following streaks:");
                System.out.println();
                for (int index = 0; index < numberOfLines; index++) {
                    player.setStreak(lines[index].getStreak());
                    System.out.printf("Line %d - %d%n", index + 1, lines[index].getStreak());
                }
                System.out.println();
                System.out.println();
                System.out.printf("Streaks of 4 achieved: %d%n", getStreaksOfFour());
                if (streaksOfFour == 2){ //TODO - if streaksOfFour > 2?
                    if (lines[0].getCurrentCard().isEqualTo(lines[1].getCurrentCard()))
                        setExactPair(true);
                    else if (lines[0].getCurrentCard().sameValueAs(lines[1].getCurrentCard()))
                        setColumnPair(true);
                }
                addCredits(p);
                System.out.println("add credits");
                streaksOfFour = 0;
                gui.setWaitingTrue();
            } catch (NullPointerException e) {
                System.out.println("NULL POINTER ERROR");
                return;
            }
        }
    }

    public static class Stopwatch {

        private final long start;

        /**
         * Initializes a new stopwatch.
         */
        public Stopwatch() {
            start = System.currentTimeMillis();
        }


        /**
         * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
         *
         * @return elapsed CPU time (in seconds) since the stopwatch was created
         */
        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }
    }

    public class HotStreakGui extends JFrame implements ActionListener {

        private final JButton[][] cardPanel;
        private final GetCard backCard = new GetCard(new Card(0, "B", 'C'));
        private final GetCard inactiveCard = new GetCard(new Card(0, "I", 'B'));
        private final GetCard autowinCard = new GetCard(new Card(0, "W", 'I'));
        private final ImageIcon backImage = scaleImage(backCard.getActiveCardImage());
        private final ImageIcon inactiveBackImage = scaleImage(inactiveCard.getInactiveCardImage());
        private final ImageIcon autowinImage = scaleImage(autowinCard.getActiveCardImage());
        private final JPanel board;
        private final JButton higher = new JButton("Higher");
        private final JButton lower = new JButton("Lower");
        private final JButton deal = new JButton("Deal");
        private final JButton creditsMore = new JButton("+");
        private final JButton creditButton = new JButton("Credits: " + player.getCredits());
        private final JButton creditsLess = new JButton("-");
        private final JButton anteButton = new JButton("Bet: " + getCredits() + "");
        private final JButton tiesLoseBasic = new JButton("Basic Ties Lose");
        private final JButton tiesWinBasic = new JButton("Basic Ties Win");
        private final JButton tiesLoseStart = new JButton("Starting Card Replacement Ties Lose");
        private final JButton tiesWinStart = new JButton("Starting Card Replacement Ties Win");
        private final JButton tiesLoseAny = new JButton("Replace Any Column Ties Lose");
        private final JButton tiesWinAny = new JButton("Replace Any Column Ties Win");
        private final JButton tiesLoseBonus = new JButton("2-Line Basic Ties Lose WITH BONUSES");
        private final JButton tiesLoseStartBonus = new JButton("<html>2-Line Starting Card Replacement<br/>Ties Lose WITH BONUSES</html>");
        private final JButton linesLess = new JButton("-");
        private final JButton linesButton = new JButton("Lines: " + getNumberOfLines());
        private final JButton linesMore = new JButton("+");
        private final JLabel streaksListing;
        private final JPanel streaksPanel;
        private final JLabel currentStreaksLabel = new JLabel("", SwingConstants.CENTER);
        private final JLabel stopWatchLabel = new JLabel("", SwingConstants.CENTER);
        private final JPanel resultPanel;
        private final JLabel resultLabel0;
        private final JLabel resultLabel1;
        private final JLabel resultLabel2;
        private final JPanel replacePanel;
        //private final JPanel gameStringPanel;
        private final JLabel gameStringLabel;
        private final JButton replaceButton;

        private ImageIcon convertImage(ImageIcon icon) {
            Image image = icon.getImage(); // transform it
            Image newImg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        }

        private ImageIcon scaleImage(String imageString) throws IOException {
            System.out.println(imageString);
            System.out.println("is card to scale");
            Image image = ImageIO.read(getClass().getResourceAsStream(imageString));
            return new ImageIcon(image);
        }

        private ImageIcon scaleCard(Card card, boolean active) throws IOException {
            GetCard cardImage = new GetCard(card);
            if (active)
                return convertImage(new ImageIcon(ImageIO.read(getClass().getResourceAsStream((cardImage.getActiveCardImage())))));
            else
                return convertImage(new ImageIcon(ImageIO.read(getClass().getResourceAsStream((cardImage.getInactiveCardImage())))));
        }

        public void drawFirstCard(Card card) throws IOException {
            for (int x = 0; x < numberOfLines; x++) {
                cardPanel[x][0].setIcon(scaleCard(card, true));
                revalidate();
            }
        }

        public void dealCard(Card card, int line, int cardNumber) throws IOException {
            cardPanel[line][cardNumber].setIcon(scaleCard(card, true));
            revalidate();
        }

        public void setCardToInactive(int lineNumber, int cardNumber, Card card) throws IOException {
            cardPanel[lineNumber][cardNumber].setIcon(scaleCard(card, false));
        }

        public void setAutowin(int lineNumber, int cardNumber) {
            cardPanel[lineNumber][cardNumber].setIcon(autowinImage);
        }

        public void setInactive(Line line, int lineNumber, int cardNumber) throws IOException {
            int cardNumber1 = 0;
            if (line.getThisDeck().getDrawnCards().size() > 0) {
                for (Card card : line.getThisDeck().getDrawnCards()) {
                    setCardToInactive(lineNumber, cardNumber1, card);
                    cardNumber1++;
                }
            }
            setCardToInactive(lineNumber, cardNumber1, line.getCurrentCard());
            cardNumber1++;
            setCardToInactive(lineNumber, cardNumber1, line.getNextCard());
            cardNumber1++;
            for (int card = cardNumber + 1; card < 5; card++) {
                cardPanel[lineNumber][card].setIcon(inactiveBackImage);
            }
            setNumberOfLinesStillGoing(getNumberOfLinesStillGoing() - 1);
            currentStreaksLabel.setText(getNumberOfLinesStillGoing() + "");
            revalidate();
        }

        public void setAnteLess() {
            setCredits(getCredits() - 1);
            anteButton.setText("Bet: " + getCredits() + "");
        }

        public void setAnteMore() {
            setCredits(getCredits() + 1);
            anteButton.setText("Bet: " + getCredits() + "");
        }

        public void setLinesLess() {
            setNumberOfLines(getNumberOfLines() - 1);
            payouts = setPayouts(getNumberOfLines());
            changePayTable();
            linesButton.setText("Lines: " + getNumberOfLines());
        }

        public void setLinesMore() {
            setNumberOfLines(getNumberOfLines() + 1);
            payouts = setPayouts(getNumberOfLines());
            changePayTable();
            linesButton.setText("Lines: " + getNumberOfLines());
        }

        public void changePayTable() {
            JPanel payTableTable = new JPanel();
            payTableTable.setLayout(new GridLayout(payouts.size(), 2));
            StringBuilder streaks = new StringBuilder();
            for (int streak = 1; streak < payouts.size(); streak++) {
                int creditsToEarn = payouts.get(streak) * getCredits();
                if (streak == 1) {
                    streaks.append("1 STREAK: ");
                    streaks.append(creditsToEarn).append("<br/>");
                    if (isActivatePokerBonus()){
                        streaks.append("&nbsp;&nbsp;").append("W/Final A/2 Bonus:");
                        streaks.append("&nbsp;").append(finalCards.get(streak) * getCredits()).append("<br/>");
                        streaks.append("&nbsp;&nbsp;").append("<br/");
                    }
                } else {
                    streaks.append(streak).append(" STREAKS: ").append(creditsToEarn).append("<br/>");
                    if (isActivatePokerBonus()){
                        streaks.append("&nbsp;&nbsp;").append("W/Final Pair Bonus: ").append(columnPairs.get(streak) * getCredits()).append("<br/>");
                        streaks.append("&nbsp;&nbsp;").append("W/Final Exact Pair Bonus: ").append(
                                exactPairs.get(streak) * getCredits()).append("&nbsp;<br/>");
                        streaks.append("&nbsp;&nbsp;").append("W/2-Flush Bonus: ").append(
                                NumberFormat.getIntegerInstance().format(
                                        (long) twoFlush.get(streak) * getCredits())).append("<br/>");
                    }
                }
            }
            if (isActivatePokerBonus())
                streaksListing.setFont(new Font("Verdana", Font.PLAIN, 15));
            else
                streaksListing.setFont(new Font("Verdana", Font.BOLD, 25));
            streaksListing.setText("<html>" + streaks + "</html>");
            streaksPanel.add(streaksListing, BorderLayout.NORTH);
        }

        public void setWaitingFalse() {
            waiting = false;
            higher.setEnabled(true);
            lower.setEnabled(true);
            deal.setEnabled(false);
            deal.setText(getGameString());
            creditsMore.setEnabled(false);
            creditsLess.setEnabled(false);
            anteButton.setEnabled(false);
            linesLess.setEnabled(false);
            linesButton.setEnabled(false);
            linesMore.setEnabled(false);
            tiesLoseBasic.setEnabled(false);
            tiesWinBasic.setEnabled(false);
            tiesLoseStart.setEnabled(false);
            tiesWinStart.setEnabled(false);
            tiesLoseAny.setEnabled(false);
            tiesWinAny.setEnabled(false);
            tiesLoseBonus.setEnabled(false);
            tiesLoseStartBonus.setEnabled(false);
            resultLabel0.setText(null);
            resultLabel0.setVisible(false);
            resultLabel0.setOpaque(true);
            resultLabel0.setBackground(Color.green);
            resultLabel1.setText(null);
            resultLabel1.setVisible(false);
            resultLabel1.setOpaque(true);
            resultLabel1.setBackground(Color.green);
            resultLabel2.setText(null);
            resultLabel2.setVisible(false);
            resultLabel2.setOpaque(true);
            resultLabel2.setBackground(Color.green);
            if (isCanReplace()) {
               // replaceButton.setVisible(true);
                replaceButton.setVisible(true);
            }
        }

        public void setWaitingTrue() {
            waiting = true;
            higher.setEnabled(false);
            lower.setEnabled(false);
            deal.setEnabled(true);
            deal.setText("Deal");
            creditsMore.setEnabled(true);
            creditsLess.setEnabled(true);
            anteButton.setEnabled(true);
            replaceButton.setVisible(false);
            switch (getGameString()) {
                case "Basic Ties Lose" -> {
                    tiesLoseBasic.setEnabled(false);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(true);
                    linesButton.setEnabled(true);
                    linesMore.setEnabled(true);
                    setCanReplace(false);
                }
                case "Basic Ties Win" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(false);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(true);
                    linesButton.setEnabled(true);
                    linesMore.setEnabled(true);
                    setCanReplace(false);
                }
                case "Ties Lose Starting Cards" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(false);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(true);
                    linesButton.setEnabled(true);
                    linesMore.setEnabled(true);
                    setCanReplace(true);
                }
                case "Ties Win Starting Cards" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(false);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(true);
                    linesButton.setEnabled(true);
                    linesMore.setEnabled(true);
                    setCanReplace(true);
                }
                case "Ties Lose Any Column" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(false);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(true);
                    linesButton.setEnabled(true);
                    linesMore.setEnabled(true);
                    setCanReplace(true);
                }
                case "Ties Win Any Column" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(false);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(true);
                    linesButton.setEnabled(true);
                    linesMore.setEnabled(true);
                    setCanReplace(true);
                }
                case "Ties Lose Bonus" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(false);
                    tiesLoseStartBonus.setEnabled(true);
                    linesLess.setEnabled(false);
                    linesButton.setEnabled(false);
                    linesMore.setEnabled(false);
                    setCanReplace(false);
                }
                case "Ties Lose Starting Bonus" -> {
                    tiesLoseBasic.setEnabled(true);
                    tiesWinBasic.setEnabled(true);
                    tiesLoseStart.setEnabled(true);
                    tiesWinStart.setEnabled(true);
                    tiesLoseAny.setEnabled(true);
                    tiesWinAny.setEnabled(true);
                    tiesLoseBonus.setEnabled(true);
                    tiesLoseStartBonus.setEnabled(false);
                    linesLess.setEnabled(false);
                    linesButton.setEnabled(false);
                    linesMore.setEnabled(false);
                    setCanReplace(true);
                }
            }
        }

        public HotStreakGui() throws IOException {
            getContentPane().repaint();
            System.out.println("Should open");
            setTitle("HotStreak");
            setSize(2000, 2000);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(true);

            setLayout(new BorderLayout());

            JPanel leftSideBoard = new JPanel();
            leftSideBoard.setLayout(new GridLayout(3, 1));

            JPanel rulesPanel = new JPanel();
            rulesPanel.setLayout(new GridLayout(3, 1));
            JLabel patent = new JLabel("U.S. Patent No. 11,610,456", SwingConstants.LEFT);
            patent.setFont(new Font("Verdana", Font.PLAIN, 10));
            rulesPanel.add(patent, BorderLayout.NORTH);
            JLabel rules = new JLabel("RULES", SwingConstants.CENTER);
            rules.setFont(new Font("Verdana", Font.BOLD, 40));
            rulesPanel.add(rules, BorderLayout.NORTH);
            JLabel version = new JLabel("<html><br/>- As & 2s Auto-Win <br/>- Ties Lose<br/></html>", SwingConstants.CENTER);
            version.setFont(new Font("Verdana", Font.BOLD, 25));
            rulesPanel.add(version, BorderLayout.NORTH);

            replacePanel = new JPanel();
            replacePanel.setLayout(new GridLayout(2, 1, 0, 3));
            replaceButton = new JButton("REPLACE COLUMN"); //REPLACEMENT
            replaceButton.setBackground(Color.yellow);
            replaceButton.setSize(new Dimension(50, 50));

            replaceButton.setFont(new Font("Verdana", Font.BOLD, 25));
            replacePanel.add(replaceButton, BorderLayout.NORTH);
            replacePanel.setMaximumSize(new Dimension(60, 60));
            replaceButton.setVisible(false);

         //   gameStringPanel = new JPanel();
            gameStringLabel = new JLabel("<html><body>Current Game Setting: <br/> Basic Ties Lose</body></html", SwingConstants.CENTER);
            gameStringLabel.setFont(new Font("Verdana", Font.BOLD, 20));
            replacePanel.add(gameStringLabel, BorderLayout.SOUTH );
            //gameStringPanel.add(gameStringLabel);


            JPanel optionsPanel = new JPanel();
            JLabel jLabel2 = new JLabel("", SwingConstants.CENTER); //REPLACEMENT
            jLabel2.setFont(new Font("Verdana", Font.BOLD, 60));
            optionsPanel.add(jLabel2);
            optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

            optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            optionsPanel.setSize(15,55);

            tiesLoseBasic.addActionListener(e -> {
                setGameString("Basic Ties Lose");
                version.setText("<html><br/>- As & 2s Auto-Win <br/>- Ties Lose<br/></html>");
                version.setFont(new Font("Verdana", Font.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setEnabled(false);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/> Basic Ties Lose</body></html");
                setTiesWin(false);
                setCanReplace(false);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setEnabled(true);
                linesButton.setEnabled(true);
                linesMore.setEnabled(true);
            });

            tiesWinBasic.addActionListener(e -> {
                setTiesWin(true);
                setGameString("Basic Ties Win");
                version.setText("<html><br/>- As & 2s Auto-Win <br/>- Ties Win<br/></html>");
                version.setFont(new Font("Verdana", Font.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(false);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/> Basic Ties Win</body></html");
                setCanReplace(false);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setEnabled(true);
                linesButton.setEnabled(true);
                linesMore.setEnabled(true);
            });

            tiesLoseStart.addActionListener(e -> {
                setGameString("Ties Lose Starting Cards");
                version.setText("<html><br/>- As & 2s Auto-Win <br/>- Ties Lose<br/></html>");
                version.setFont(new Font("Verdana", Font.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(false);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/>Starting Card Replacement<br/>Ties Lose</body></html");
                setTiesWin(false);
                setCanReplace(true);
                setOnlyStartingCardReplace(true);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setEnabled(true);
                linesButton.setEnabled(true);
                linesMore.setEnabled(true);
            });

            tiesWinStart.addActionListener(e -> {
                setTiesWin(true);
                setGameString("Ties Win Starting Cards");
                version.setText("<html><br/>- As & 2s Auto-Win <br/>- Ties Win<br/></html>");
                version.setFont(new Font("Verdana", Font.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(false);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/>Starting Card Replacement<br/>Ties Win</body></html");
                setCanReplace(true);
                setOnlyStartingCardReplace(true);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setEnabled(true);
                linesButton.setEnabled(true);
                linesMore.setEnabled(true);
            });

            tiesLoseAny.addActionListener(e -> {
                setGameString("Ties Lose Any Column");
                version.setText("<html><br/>- As & 2s Auto-Win <br/>- Ties Lose<br/></html>");
                version.setFont(new Font("Verdana", Font.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(false);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/>Replace Any Column<br/>Ties Lose</body></html");
                setTiesWin(false);
                setCanReplace(true);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setEnabled(true);
                linesButton.setEnabled(true);
                linesMore.setEnabled(true);
            });

            tiesWinAny.addActionListener(e -> {
                setTiesWin(true);
                setGameString("Ties Win Any Column");
                version.setText("<html><br/>- As & 2s Auto-Win <br/>- Ties Win<br/></html>");
                version.setFont(new Font("Verdana", Font.BOLD, 25));
                payouts = setPayouts(getNumberOfLines());
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(false);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/>Replace Any Column<br/>Ties Win</body></html");
                setCanReplace(true);
                setOnlyStartingCardReplace(false);
                setActivatePokerBonus(false);
                changePayTable();
                linesLess.setEnabled(true);
                linesButton.setEnabled(true);
                linesMore.setEnabled(true);
            });

            tiesLoseBonus.addActionListener(e -> {
                setGameString("Ties Lose Bonus");
                version.setText("<html>- As & 2s Auto-Win <br/>- Ties Lose<br/>- Poker Bonuses<br/>Activated</html>");
                version.setFont(new Font("Verdana", Font.BOLD, 20));
                while (getNumberOfLines() > 2)
                    setLinesLess();
                payouts = setPayouts(getNumberOfLines());
                columnPairs = setColumnPairs(getNumberOfLines());
                exactPairs = setExactPairs(getNumberOfLines());
                finalCards = setFinalCards(getNumberOfLines());
                twoFlush = setTwoFlush(getNumberOfLines());
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(false);
                tiesLoseStartBonus.setEnabled(true);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/>2-Line Basic Ties Lose<br/>WITH BONUSES</body></html");
                setTiesWin(false);
                setActivatePokerBonus(true);
                setCanReplace(false);
                setOnlyStartingCardReplace(false);
                changePayTable();
                linesLess.setEnabled(false);
                linesButton.setEnabled(false);
                linesMore.setEnabled(false);
            });

            tiesLoseStartBonus.addActionListener(e -> {
                setGameString("Ties Lose Starting Bonus");
                version.setText("<html>- As & 2s Auto-Win <br/>- Ties Lose<br/>- Poker Bonuses<br/>Activated</html>");
                version.setFont(new Font("Verdana", Font.BOLD, 20));
                while (getNumberOfLines() > 2)
                    setLinesLess();
                payouts = setPayouts(getNumberOfLines());
                columnPairs = setColumnPairs(getNumberOfLines());
                exactPairs = setExactPairs(getNumberOfLines());
                finalCards = setFinalCards(getNumberOfLines());
                twoFlush = setTwoFlush(getNumberOfLines());
                setActivatePokerBonus(true);
                tiesLoseBasic.setEnabled(true);
                tiesWinBasic.setEnabled(true);
                tiesLoseStart.setEnabled(true);
                tiesWinStart.setEnabled(true);
                tiesLoseAny.setEnabled(true);
                tiesWinAny.setEnabled(true);
                tiesLoseBonus.setEnabled(true);
                tiesLoseStartBonus.setEnabled(false);
                gameStringLabel.setText("<html><body>Current Game Setting: <br/>2-Line Starting Card<br/>Replacement Ties Lose<br/>WITH BONUSES</body></html");
                setTiesWin(false);
                setActivatePokerBonus(true);
                setCanReplace(true);
                setOnlyStartingCardReplace(true);
                changePayTable();
                linesLess.setEnabled(false);
                linesButton.setEnabled(false);
                linesMore.setEnabled(false);
            });

            optionsPanel.add(tiesLoseBasic);
            optionsPanel.add(tiesWinBasic);
            optionsPanel.add(tiesLoseStart);
            optionsPanel.add(tiesWinStart);
            optionsPanel.add(tiesLoseAny);
            optionsPanel.add(tiesWinAny);
            optionsPanel.add(tiesLoseBonus);
            optionsPanel.add(tiesLoseStartBonus);
            tiesLoseStartBonus.setHorizontalAlignment(SwingConstants.LEFT);


            leftSideBoard.add(rulesPanel);
            leftSideBoard.add(replacePanel);
            leftSideBoard.add(optionsPanel);

            add(leftSideBoard, BorderLayout.WEST);

            this.board = new JPanel();

            board.setLayout(new GridLayout(5, 5));

            cardPanel = new JButton[5][5];


            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    cardPanel[x][y] = new JButton(backImage);
                    board.add(cardPanel[x][y]);
                }
            }

            add(board, BorderLayout.CENTER);

            JPanel rightSideBoard = new JPanel();
            rightSideBoard.setLayout(new GridLayout(2, 1));

            streaksPanel = new JPanel();
            streaksPanel.setLayout(new GridLayout(2, 1));

            JLabel payouts = new JLabel("PAYOUTS", SwingConstants.CENTER);
            payouts.setFont(new Font("Verdana", Font.BOLD, 40));
            streaksPanel.add(payouts, BorderLayout.NORTH);

            streaksListing = new JLabel();
            streaksListing.setFont(new Font("Verdana", Font.BOLD, 30));
            changePayTable();

            JPanel currentStreaksPanel = new JPanel();
            JLabel currentLabel = new JLabel("<html>CURRENT<br/>STREAKS<br/>", SwingConstants.CENTER);
            currentLabel.setFont(new Font("Verdana", Font.BOLD, 40));
            currentStreaksPanel.add(currentLabel);
            currentStreaksPanel.setLayout(new GridBagLayout());

            GridBagConstraints c = new GridBagConstraints();
            c.gridy = 0;

            rightSideBoard.add(streaksPanel);
            rightSideBoard.add(currentStreaksPanel);


            resultPanel = new JPanel();
            resultPanel.setLayout(new GridLayout(4, 1));
            resultPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            resultLabel0 = new JLabel("", SwingConstants.CENTER);
            resultLabel0.setFont(new Font("Verdana", Font.BOLD, 60));
            resultPanel.add(resultLabel0, BorderLayout.NORTH);
            resultLabel0.setBackground(Color.green);
            resultLabel0.setForeground(Color.white);
            resultLabel1 = new JLabel("", SwingConstants.CENTER);
            resultLabel1.setFont(new Font("Verdana", Font.BOLD, 60));
            resultPanel.add(resultLabel1, BorderLayout.NORTH);
            resultLabel1.setBackground(Color.green);
            resultLabel1.setForeground(Color.white);
            resultLabel2 = new JLabel("", SwingConstants.CENTER);
            resultLabel2.setFont(new Font("Verdana", Font.BOLD, 60));
            resultPanel.add(resultLabel2, BorderLayout.SOUTH);
            resultLabel2.setBackground(Color.green);
            resultLabel2.setForeground(Color.white);
            c.gridy = 1;

            currentStreaksPanel.add(currentStreaksLabel, c);
            currentStreaksPanel.add(stopWatchLabel, c);
            c.gridy = 2;
            c.anchor = GridBagConstraints.PAGE_END;
            currentStreaksPanel.add(resultPanel, c);
            c.gridy = 3;
            stopWatchLabel.setBorder(new EmptyBorder(30, 10, 10, 10));
            stopWatchLabel.setFont(new Font("Verdana", Font.BOLD, 15));

            currentStreaksPanel.add(stopWatchLabel, c);

            add(rightSideBoard, BorderLayout.EAST);

            JPanel options = new JPanel();
            options.setLayout(new GridLayout(1, 3));

            higher.addActionListener(e -> {
                setGuessIsHigher(true);
                setGuessIsMade(true);
            });
            higher.setEnabled(false);

            replaceButton.addActionListener(e -> {
                if (!waiting) {
                    setReplaceCall(true);
                    setGuessIsMade(true);
                    replaceButton.setVisible(false);
                    replaceButton.setVisible(false);
                }
            });

            options.add(higher);

            lower.addActionListener(e -> setGuessIsMade(true));
            lower.setEnabled(false);

            options.add(lower);

            options.add(deal);

            creditsLess.addActionListener(e -> {
                if (getCredits() > 1) {
                    setAnteLess();
                    changePayTable();
                }
            });

            options.add(creditsLess);
            options.add(anteButton);

            creditsMore.addActionListener(e -> {
                if (getCredits() < 5) {
                    setAnteMore();
                    changePayTable();
                }
            });

            options.add(creditsMore);

            linesLess.addActionListener(e -> {
                if (getNumberOfLines() > 2) {
                    setLinesLess();
                }
            });

            options.add(linesLess);

            options.add(linesButton);


            linesMore.addActionListener(e -> {
                if (getNumberOfLines() < 5) {
                    setLinesMore();
                }
            });

            options.add(linesMore);

            options.add(creditButton);

            add(options, BorderLayout.SOUTH);

            deal.addActionListener(e -> {
                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        cardPanel[x][y].setIcon(backImage);
                    }
                }
                player.setNumberOfLines(getNumberOfLines());
                setNumberOfLinesStillGoing(getNumberOfLines());
                setUpLines();
                setWaitingFalse();

                currentStreaksLabel.setText(getNumberOfLinesStillGoing() + "");
                currentStreaksLabel.setFont(new Font("Verdana", Font.BOLD, 40));
            });
            revalidate();
        }

        public void actionPerformed(ActionEvent event) {
            event.getActionCommand();
            revalidate();
        }
    }
}