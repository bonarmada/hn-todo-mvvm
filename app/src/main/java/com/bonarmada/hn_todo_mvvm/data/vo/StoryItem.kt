package com.bonarmada.hn_todo_mvvm.data.vo

import com.bonarmada.hn_todo_mvvm.R
import com.bonarmada.hn_todo_mvvm.data.model.Story
import com.bonarmada.hn_todo_mvvm.databinding.ItemStoryBinding
import com.xwray.groupie.databinding.BindableItem

data class StoryItem constructor(private val story: Story) : BindableItem<ItemStoryBinding>() {

    override fun getLayout() = R.layout.item_story

    override fun bind(viewBinding: ItemStoryBinding, position: Int) {
        viewBinding.item = story
    }
}