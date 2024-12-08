package edu.grinnell.csc207.util;

/**
 * An interior node in a binary tree.
 *
 * <p>This node has two children: left and right. Implements the BitTreeNode interface.
 *
 * @author Sunjae Kim
 */
public class BitTreeInteriorNode implements BitTreeNode {

  /** Left child of this node. */
  private BitTreeNode left;

  /** Right child of this node. */
  private BitTreeNode right;

  // +--------------+----------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Make a new interior node with left and right children.
   *
   * @param leftChild The left child.
   * @param rightChild The right child.
   */
  public BitTreeInteriorNode(BitTreeNode leftChild, BitTreeNode rightChild) {
    this.left = leftChild;
    this.right = rightChild;
  } // BitTreeInteriorNode(BitTreeNode, BitTreeNode)

  /** Make a new interior node with no children. */
  public BitTreeInteriorNode() {
    this(null, null);
  } // BitTreeInteriorNode()

  // +---------+-----------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Checks if this is a leaf node.
   *
   * @return Always false (this is not a leaf).
   */
  @Override
  public boolean checkIfLeaf() {
    return false;
  } // checkIfLeaf()

  /**
   * Get the left child.
   *
   * @return The left child.
   */
  public BitTreeNode fetchLeftChild() {
    return this.left;
  } // fetchLeftChild()

  /**
   * Set the left child.
   *
   * @param leftChild The new left child.
   */
  public void updateLeftChild(BitTreeNode leftChild) {
    this.left = leftChild;
  } // updateLeftChild(BitTreeNode)

  /**
   * Get the right child.
   *
   * @return The right child.
   */
  public BitTreeNode fetchRightChild() {
    return this.right;
  } // fetchRightChild()

  /**
   * Set the right child.
   *
   * @param rightChild The new right child.
   */
  public void updateRightChild(BitTreeNode rightChild) {
    this.right = rightChild;
  } // updateRightChild(BitTreeNode)
} // class BitTreeInteriorNode
