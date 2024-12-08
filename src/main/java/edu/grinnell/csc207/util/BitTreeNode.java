package edu.grinnell.csc207.util;

/**
 * A node in a binary tree.
 *
 * <p>Defines a basic method to check if the node is a leaf. Interior and leaf nodes will implement
 * this interface.
 *
 * @author Sunjae Kim
 */
public interface BitTreeNode {

  /**
   * Check if this node is a leaf.
   *
   * @return true if it is a leaf, false if not.
   */
  boolean checkIfLeaf(); // method checkIfLeaf()
} // interface BitTreeNode
