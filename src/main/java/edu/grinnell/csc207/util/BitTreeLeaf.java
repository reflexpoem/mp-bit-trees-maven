package edu.grinnell.csc207.util;

/**
 * A leaf in a binary tree.
 *
 * <p>A leaf stores some data and has no children. Implements the BitTreeNode interface.
 *
 * @author Sunjae Kim
 */
public class BitTreeLeaf implements BitTreeNode {

  /** The data stored in the leaf. */
  private String data;

  // +--------------+----------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Make a new leaf with the given data.
   *
   * @param content The data to store in the leaf.
   */
  public BitTreeLeaf(String content) {
    this.data = content;
  } // BitTreeLeaf(String)

  // +---------+-----------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the data stored in this leaf.
   *
   * @return The data in the leaf.
   */
  public String fetchData() {
    return this.data;
  } // fetchData()

  /**
   * Check if this node is a leaf.
   *
   * @return Always true because this is a leaf.
   */
  @Override
  public boolean checkIfLeaf() {
    return true;
  } // checkIfLeaf()
} // class BitTreeLeaf
