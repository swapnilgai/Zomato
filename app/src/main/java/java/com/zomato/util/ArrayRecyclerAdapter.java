package java.com.zomato.util;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author swapnil
 */
public abstract class ArrayRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {

  private final Object mLock = new Object();
  List<T> mDataSet = new ArrayList<>();
  private boolean mNotifyOnChange = true;

  public ArrayRecyclerAdapter() {
    super();
  }

  @Override public int getItemCount() {
    return mDataSet.size();
  }

  public T getItem(int position) {
    return mDataSet.get(position);
  }

  /**
   * Adds the specified object at the end of the array.
   *
   * @param object The object to add at the end of the array.
   */
  public void add(T object) {
    synchronized (mLock) {
      mDataSet.add(object);
    }

    if (mNotifyOnChange) notifyItemInserted(mDataSet.size() - 1);
  }

  /**
   * Adds the specified Collection at the end of the array.
   *
   * @param collection The Collection to add at the end of the array.
   */
  public void addAll(Collection<? extends T> collection) {
    if (collection != null && collection.size() > 0) {
      synchronized (mLock) {
        mDataSet.addAll(collection);
      }
      if (mNotifyOnChange) {
        notifyItemRangeInserted(mDataSet.size() - collection.size(), collection.size());
      }
    }
  }

  /**
   * Replaces the specified Collection with the existing collection.
   *
   * @param collection The Collection to add.
   */
  public void replaceAll(Collection<? extends T> collection) {
    if (collection != null && collection.size() > 0) {
      synchronized (mLock) {
        mDataSet.clear();
        mDataSet.addAll(collection);
      }
      if (mNotifyOnChange) notifyDataSetChanged();
    }
  }

  /**
   * Adds the specified items at the end of the array.
   *
   * @param items The items to add at the end of the array.
   */
  public void addAll(T... items) {
    if (items.length > 0) {
      synchronized (mLock) {
        Collections.addAll(mDataSet, items);
      }
      if (mNotifyOnChange) notifyItemRangeInserted(mDataSet.size() - items.length, items.length);
    }
  }

  /**
   * Inserts the specified object at the specified index in the array.
   *
   * @param object The object to insert into the array.
   * @param index The index at which the object must be inserted.
   */
  public void insert(T object, int index) {
    synchronized (mLock) {
      mDataSet.add(index, object);
    }
    if (mNotifyOnChange) notifyItemInserted(index);
  }

  /**
   * Removes the specified object from the array.
   *
   * @param object The object to remove.
   */
  public void remove(T object) {
    int position = mDataSet.indexOf(object);

    if (position >= 0) {
      synchronized (mLock) {
        mDataSet.remove(object);
      }

      if (mNotifyOnChange) notifyItemRemoved(position);
    }
  }

  /**
   * Remove all elements from the list.
   */
  public void clear() {
    synchronized (mLock) {
      mDataSet.clear();
    }
    if (mNotifyOnChange) notifyDataSetChanged();
  }

  /**
   * Sorts the content of this adapter using the specified comparator.
   *
   * @param comparator The comparator used to sort the objects contained
   * in this adapter.
   */
  public void sort(Comparator<? super T> comparator) {
    synchronized (mLock) {
      Collections.sort(mDataSet, comparator);
    }
    if (mNotifyOnChange) notifyDataSetChanged();
  }

  /**
   * Control whether methods that change the list ({@link #add},
   *
   * @param notifyOnChange if true, modifications to the list will
   * automatically call the appropriate notify* method.
   */
  public void setNotifyOnChange(boolean notifyOnChange) {
    mNotifyOnChange = notifyOnChange;
  }

  public List<T> getDataSet() {
    return mDataSet;
  }
}