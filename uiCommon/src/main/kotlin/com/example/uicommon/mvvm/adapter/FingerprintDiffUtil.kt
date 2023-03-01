package com.example.uicommon.mvvm.adapter

import androidx.recyclerview.widget.DiffUtil

class FingerprintDiffUtil(
    private val fingerprints: List<ItemFingerprint<*, *>>,
) : DiffUtil.ItemCallback<ListItem>() {

    override fun areItemsTheSame(oldListItem: ListItem, newListItem: ListItem): Boolean {
        if (oldListItem::class != newListItem::class) return false

        return getItemCallback(oldListItem).areItemsTheSame(oldListItem, newListItem)
    }

    override fun areContentsTheSame(oldListItem: ListItem, newListItem: ListItem): Boolean {
        if (oldListItem::class != newListItem::class) return false

        return getItemCallback(oldListItem).areContentsTheSame(oldListItem, newListItem)
    }

    private fun getItemCallback(
        listItem: ListItem
    ): DiffUtil.ItemCallback<ListItem> = fingerprints.find { it.isRelativeItem(listItem) }
        ?.getDiffUtil()
        ?.let { it as DiffUtil.ItemCallback<ListItem> }
        ?: throw IllegalStateException("DiffUtil not found for $listItem")

}