package com.bonarmada.hn_todo_mvvm.util


import android.arch.paging.AsyncPagedListDiffer
import android.arch.paging.PagedList
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.support.v7.util.ListUpdateCallback

import com.xwray.groupie.Group
import com.xwray.groupie.GroupDataObserver
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

class PagedListGroup<T : Item<ViewHolder>> : Group, GroupDataObserver {
    private var parentObserver: GroupDataObserver? = null
    private var placeHolder: Item<ViewHolder>? = null

    private val listUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {
            parentObserver!!.onItemRangeInserted(this@PagedListGroup, position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            parentObserver!!.onItemRangeRemoved(this@PagedListGroup, position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            parentObserver!!.onItemMoved(this@PagedListGroup, fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int, payload: Any) {
            parentObserver!!.onItemRangeChanged(this@PagedListGroup, position, count)
        }
    }

    private val differ = AsyncPagedListDiffer(
            listUpdateCallback,
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                    return newItem.isSameAs(oldItem)
                }

                override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                    return newItem == oldItem
                }
            }).build()
    )


    fun submitList(newPagedList: PagedList<T>) {
        differ.submitList(newPagedList)
    }

    override fun getItemCount(): Int {
        return differ.itemCount
    }

    override fun getItem(position: Int): Item<ViewHolder> {
        val item = differ.getItem(position)
        if (item != null) {
            // TODO find more efficiency registration timing, and removing observer
            item.registerGroupDataObserver(this)
            return item
        }
        return placeHolder!!
    }

    override fun getPosition(item: Item<*>): Int {
        val currentList = differ.currentList ?: return -1

        return currentList.indexOf(item)
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        parentObserver = groupDataObserver
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        parentObserver = null
    }

    override fun onChanged(group: Group) {
        parentObserver!!.onChanged(this)
    }

    override fun onItemInserted(group: Group, position: Int) {
        throw UnsupportedOperationException()
    }

    override fun onItemChanged(group: Group, position: Int) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver!!.onItemChanged(this, index)
        }
    }

    override fun onItemRangeChanged(group: Group, positionStart: Int, itemCount: Int, payload: Any?) {
    }


    override fun onItemChanged(group: Group, position: Int, payload: Any) {
        val index = getItemPosition(group)
        if (index >= 0) {
            parentObserver!!.onItemChanged(this, index, payload)
        }
    }

    override fun onItemRemoved(group: Group, position: Int) {
        throw UnsupportedOperationException()
    }

    override fun onItemRangeChanged(group: Group, positionStart: Int, itemCount: Int) {
        throw UnsupportedOperationException()
    }

    override fun onItemRangeInserted(group: Group, positionStart: Int, itemCount: Int) {
        throw UnsupportedOperationException()
    }

    override fun onItemRangeRemoved(group: Group, positionStart: Int, itemCount: Int) {
        throw UnsupportedOperationException()
    }

    override fun onItemMoved(group: Group, fromPosition: Int, toPosition: Int) {
        throw UnsupportedOperationException()
    }

    private fun getItemPosition(group: Group): Int {
        val currentList = differ.currentList ?: return -1

        return currentList.indexOf(group)
    }
}