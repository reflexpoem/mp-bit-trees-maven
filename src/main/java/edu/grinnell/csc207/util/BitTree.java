package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * A tree for mapping fixed-length binary keys to values.
 *
 * @author Sunjae Kim
 * @author Samuel Rebelsky
 */
public class BitTree {

  /** Maximum depth of the tree. */
  private int treeDepth; // Max depth of the tree

  /** Root node of the tree. */
  private BitTreeNode rootNode; // Root of the tree

  /**
   * Constructs a new tree with the given depth.
   *
   * @param depth the maximum depth of the tree
   */
  public BitTree(int depth) {
    this.rootNode = null;
    this.treeDepth = depth;
  } // constructor BitTree(int)

  /**
   * Validates that a binary string has the expected length.
   *
   * @param binaryString the string to check
   * @throws IndexOutOfBoundsException if the string length is invalid
   */
  private void set(String binaryString) {
    if (binaryString.length() != treeDepth) {
      throw new IndexOutOfBoundsException(
          "Binary string must be "
              + treeDepth
              + " bits long, but got "
              + binaryString.length()
              + " bits.");
    } // if
  } // validateBinaryString(String)

  /**
   * Writes all key-value pairs to the output.
   *
   * @param writer the output destination
   * @param node the current node
   * @param path the binary path to this node
   */
  private void get(PrintWriter writer, BitTreeNode node, String path) {
    if (node == null) {
      return;
    } // if

    if (node instanceof BitTreeLeaf) {
      writer.println(path + "," + ((BitTreeLeaf) node).fetchData());
    } else if (node instanceof BitTreeInteriorNode) {
      BitTreeInteriorNode internalNode = (BitTreeInteriorNode) node;
      get(writer, internalNode.fetchLeftChild(), path + "0");
      get(writer, internalNode.fetchRightChild(), path + "1");
    } // else if
  } // traverseAndOutput(PrintWriter, BitTreeNode, String)

  /**
   * Follows a binary path to retrieve a value.
   *
   * @param node the current subtree root
   * @param path the binary path to follow
   * @return the value at the leaf
   * @throws IndexOutOfBoundsException if the path does not exist
   */
  private String getValueByPath(BitTreeNode node, String path) {
    if (node == null) {
      throw new IndexOutOfBoundsException("Path does not exist in the tree.");
    } // if

    if (path.isEmpty()) {
      if (node instanceof BitTreeLeaf) {
        return ((BitTreeLeaf) node).fetchData();
      } else {
        throw new IndexOutOfBoundsException("Expected a leaf node at the end of the path.");
      } // else
    } // if

    char direction = path.charAt(0);
    if (node instanceof BitTreeInteriorNode) {
      BitTreeInteriorNode internalNode = (BitTreeInteriorNode) node;
      switch (direction) {
        case '0':
          return getValueByPath(internalNode.fetchLeftChild(), path.substring(1));
        case '1':
          return getValueByPath(internalNode.fetchRightChild(), path.substring(1));
        default:
          throw new IndexOutOfBoundsException("Invalid character in path: " + direction);
      } // switch
    } else {
      throw new IndexOutOfBoundsException("Unexpected leaf node in the middle of the path.");
    } // else
  } // getValueByPath(BitTreeNode, String)

  /**
   * Adds or updates a value at the given binary path.
   *
   * @param node the current subtree root
   * @param path the binary path
   * @param value the value to set
   * @return the updated subtree root
   */
  private BitTreeNode insertOrUpdate(BitTreeNode node, String path, String value) {
    if (path.isEmpty()) {
      return new BitTreeLeaf(value);
    } // if

    if (node == null) {
      node = new BitTreeInteriorNode();
    } // if

    char direction = path.charAt(0);
    if (node instanceof BitTreeInteriorNode) {
      BitTreeInteriorNode internalNode = (BitTreeInteriorNode) node;
      switch (direction) {
        case '0':
          internalNode.updateLeftChild(
              insertOrUpdate(internalNode.fetchLeftChild(), path.substring(1), value));
          break;
        case '1':
          internalNode.updateRightChild(
              insertOrUpdate(internalNode.fetchRightChild(), path.substring(1), value));
          break;
        default:
          throw new IndexOutOfBoundsException("Invalid character in path: " + direction);
      } // switch
    } // if
    return node;
  } // insertOrUpdate(BitTreeNode, String, String)

  /**
   * Sets a value at the specified binary path.
   *
   * @param binaryPath the binary key
   * @param value the value to associate with the key
   */
  public void set(String binaryPath, String value) {
    set(binaryPath);
    rootNode = insertOrUpdate(rootNode, binaryPath, value);
  } // set(String, String)

  /**
   * Retrieves the value associated with a binary path.
   *
   * @param binaryPath the binary key
   * @return the associated value
   */
  public String get(String binaryPath) {
    set(binaryPath);
    return getValueByPath(rootNode, binaryPath);
  } // get(String)

  /**
   * Outputs all key-value pairs in the tree.
   *
   * @param writer the output destination
   */
  public void dump(PrintWriter writer) {
    get(writer, rootNode, "");
  } // dump(PrintWriter)

  /**
   * Loads key-value pairs from an input stream into the tree.
   *
   * @param inputStream the input stream containing key-value pairs
   */
  public void load(InputStream inputStream) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(",", 2);
        set(parts[0], parts[1]);
      } // while
    } catch (IOException e) {
      // Ignored
    } // try-catch
  } // load(InputStream)
} // class BitTree
