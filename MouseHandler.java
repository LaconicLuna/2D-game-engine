import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public boolean ldown, mdown, rdown, click, lrelease,mrelease,rrelease;

    @Override
    public void mouseClicked(MouseEvent e) {
        click = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            ldown = true;
        }
        if (e.getButton() == 2) {
            mdown = true;
        }
        if (e.getButton() == 3) {
            rdown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            ldown = false;
            lrelease = true;
        }
        if (e.getButton() == 2) {
            mdown = false;
            mrelease = true;
        }
        if (e.getButton() == 3) {
            rdown = false;
            rrelease = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("exit");
    }

}
