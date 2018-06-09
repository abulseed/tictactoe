package utils;

/**
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/15uf">Section 1.5</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * 
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class WeightedQuickUnionUF {
  private int[] parent; // parent[i] = parent of i
  private int[] size; // size[i] = number of sites in subtree rooted at i
  private int count; // number of components

  /**
   * Initializes an empty union–find data structure with {@code n} sites { code 0
   *  through {@code n-1}. Each site is initially in its own component.
   *
   * @param n the number of sites
   * @throws IllegalArgumentException if {@code n < 0}
   */
  private WeightedQuickUnionUF() {
    int gridSize = Integer.parseInt(Props.readProp(Fields.SIZE_OF_PLAYGROUND));
    int n = (gridSize * gridSize) + 12;
    System.out.println("groups inited......." + n);
    count = n;
    parent = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  private static class WeightedQuickUnionUFHolder {
    private static final WeightedQuickUnionUF INSTANCE = new WeightedQuickUnionUF();
  }

  public static WeightedQuickUnionUF getInstance() throws NumberFormatException {
    return WeightedQuickUnionUFHolder.INSTANCE;
  }

  /**
   * Returns the number of components.
   *
   * @return the number of components (between {@code 1} and {@code n})
   */
  public int count() {
    return count;
  }

  /**
   * Returns the component identifier for the component containing site {@code p}.
   *
   * @param p the integer representing one object
   * @return the component identifier for the component containing site {@code p}
   * @throws IndexOutOfBoundsException unless {@code 0 <= p < n}
   */
  public int find(int p) {
    validate(p);
    while (p != parent[p])
      p = parent[p];
    return p;
  }

  // validate that p is a valid index
  private void validate(int p) {
    int n = parent.length;
    if (p < 0 || p >= n) {
      throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
    }
  }

  /**
   * Returns true if the the two sites are in the same component.
   *
   * @param p the integer representing one site
   * @param q the integer representing the other site
   * @return {@code true} if the two sites {@code p} and {@code q} are in the same
   *         component; {@code false} otherwise
   * @throws IndexOutOfBoundsException unless both {@code 0 <= p < n} and
   *                                   {@code 0 <= q < n}
   */
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  /**
   * Merges the component containing site {@code p} with the the component
   * containing site {@code q}.
   *
   * @param p the integer representing one site
   * @param q the integer representing the other site
   * @throws IndexOutOfBoundsException unless both {@code 0 <= p < n} and
   *                                   {@code 0 <= q < n}
   */
  public void union(int p, int q) {
    System.out.println("UNION" + p + "+" + q);
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ)
      return;

    // make smaller root point to larger one
    if (size[rootP] < size[rootQ]) {
      parent[rootP] = rootQ;
      size[rootQ] += size[rootP];
    } else {
      parent[rootQ] = rootP;
      size[rootP] += size[rootQ];
    }
    count--;
  }
}

/******************************************************************************
 * Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
