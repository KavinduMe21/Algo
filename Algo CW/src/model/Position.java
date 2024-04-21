package model;
/** *****************************************************************************
 *  Name:    Kavindu Mendis
 *  UOW ID:   W1902232
 *  IIT ID: 20212152
 *
 *  Description:  will help to define and store node object data of position
 *
 **************************************************************************** */
public class Position {
    private int x;
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
}
