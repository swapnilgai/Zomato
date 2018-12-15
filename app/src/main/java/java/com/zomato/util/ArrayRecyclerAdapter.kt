package java.com.zomato.util

import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

/**
 * @author swapnil
 */
abstract class ArrayRecyclerAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

  private val mLock = Any()
  internal var mDataSet: MutableList<T> = ArrayList()
  private var mNotifyOnChange = true

  val dataSet: List<T>
    get() = mDataSet

  fun getItem(position: Int): T {
    return mDataSet[position]
  }

  override fun getItemCount(): Int {
    return mDataSet.size
  }

  /**
   * Adds the specified object at the end of the array.
   *
   * @param object The object to add at the end of the array.
   */
  fun add(`object`: T) {
    synchronized(mLock) {
      mDataSet.add(`object`)
    }

    if (mNotifyOnChange) notifyItemInserted(mDataSet.size - 1)
  }

  /**
   * Adds the specified Collection at the end of the array.
   *
   * @param collection The Collection to add at the end of the array.
   */
  fun addAll(collection: Collection<T>?) {
    if (collection != null && collection.size > 0) {
      synchronized(mLock) {
        mDataSet.addAll(collection)
      }
      if (mNotifyOnChange) {
        notifyItemRangeInserted(mDataSet.size - collection.size, collection.size)
      }
    }
  }

  /**
   * Replaces the specified Collection with the existing collection.
   *
   * @param collection The Collection to add.
   */
  fun replaceAll(collection: Collection<T>?) {
    if (collection != null && collection.size > 0) {
      synchronized(mLock) {
        mDataSet.clear()
        mDataSet.addAll(collection)
      }
      if (mNotifyOnChange) notifyDataSetChanged()
    }
  }

  /**
   * Adds the specified items at the end of the array.
   *
   * @param items The items to add at the end of the array.
   */
  fun addAll(vararg items: T) {
    if (items.size > 0) {
      synchronized(mLock) {
        Collections.addAll(mDataSet, *items)
      }
      if (mNotifyOnChange) notifyItemRangeInserted(mDataSet.size - items.size, items.size)
    }
  }

  /**
   * Inserts the specified object at the specified index in the array.
   *
   * @param object The object to insert into the array.
   * @param index The index at which the object must be inserted.
   */
  fun insert(`object`: T, index: Int) {
    synchronized(mLock) {
      mDataSet.add(index, `object`)
    }
    if (mNotifyOnChange) notifyItemInserted(index)
  }

  /**
   * Removes the specified object from the array.
   *
   * @param object The object to remove.
   */
  fun remove(`object`: T) {
    val position = mDataSet.indexOf(`object`)

    if (position >= 0) {
      synchronized(mLock) {
        mDataSet.remove(`object`)
      }

      if (mNotifyOnChange) notifyItemRemoved(position)
    }
  }

  /**
   * Remove all elements from the list.
   */
  fun clear() {
    synchronized(mLock) {
      mDataSet.clear()
    }
    if (mNotifyOnChange) notifyDataSetChanged()
  }

  /**
   * Sorts the content of this adapter using the specified comparator.
   *
   * @param comparator The comparator used to sort the objects contained
   * in this adapter.
   */
  fun sort(comparator: Comparator<in T>) {
    synchronized(mLock) {
      Collections.sort(mDataSet, comparator)
    }
    if (mNotifyOnChange) notifyDataSetChanged()
  }

  /**
   * Control whether methods that change the list ([.add],
   *
   * @param notifyOnChange if true, modifications to the list will
   * automatically call the appropriate notify* method.
   */
  fun setNotifyOnChange(notifyOnChange: Boolean) {
    mNotifyOnChange = notifyOnChange
  }
}