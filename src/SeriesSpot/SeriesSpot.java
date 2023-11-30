package SeriesSpot;

import javax.swing.SwingUtilities;

public class SeriesSpot {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MovieInfoGUI gui = new MovieInfoGUI();
                gui.display();
            }
        });
    }
}


