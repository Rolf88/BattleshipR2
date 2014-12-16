/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package r2;

/**
 *
 * @author rolf
 */
public class Pos {

    private final int x;
    private final int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.x;
        hash = 19 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pos other = (Pos) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }
    
    
}
