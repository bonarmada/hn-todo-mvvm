package com.bonarmada.hn_todo_mvvm.ui.main

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.bonarmada.hn_todo_mvvm.R
import com.bonarmada.hn_todo_mvvm.data.model.Story

class StoryAdapter : PagedListAdapter<Story, StoryViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder = StoryViewHolder(parent)

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean =
                    oldItem == newItem
        }
    }
}

class StoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)) {
    private val tvId = itemView.findViewById<TextView>(R.id.id)
    private val tvTitle = itemView.findViewById<TextView>(R.id.title)
    private val tvBy= itemView.findViewById<TextView>(R.id.by)

    var story : Story? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(cheese : Story?) {
        this.story = story
        tvId.text = story?.id.toString()
        tvTitle.text = story?.title.toString()
        tvBy.text = story?.by.toString()
    }
}