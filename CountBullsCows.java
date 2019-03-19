/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cowsandbullsgamestring;

/**
 *
 * @author bhanu
 */
public class CountBullsCows {
    public int countBull = 0;
    public int countCow = 0;

    public CountBullsCows(int b, int c) {
        countBull = b;
        countCow = c;
    }

    @Override
    public String toString() {
        return countBull + " " + countCow;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CountBullsCows other = (CountBullsCows) obj;
        if (countBull != other.countBull)
            return false;
        if (countCow != other.countCow)
            return false;
        return true;
    }
}

